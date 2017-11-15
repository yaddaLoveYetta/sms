package com.kingdee.eas.hrp.sms.service.app.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.*;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.*;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.app.api.HrpToSmsBusinessService;
import com.kingdee.eas.hrp.sms.service.app.model.ConfirmOrderModel;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.MsgUtils;
import com.kingdee.eas.hrp.sms.util.Result;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.yadda.api.core.ApiMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * hrp调用sms接口业务
 *
 * @author yadda
 */
@Service
public class HrpToSmsBusinessServiceImpl extends BaseService implements HrpToSmsBusinessService {

    private static Logger logger = LoggerFactory.getLogger(HrpToSmsBusinessServiceImpl.class);

    /**
     * 同步基础资料时每次最多上传数量
     */
    private static final int MAX_PER_TIME_UPLOAD_BASE_DATA = 500;
    /**
     * 同步订单时每次最多上传数量
     */
    private static final int MAX_PER_TIME_UPLOAD_ORDER = 10;

    /**
     * 同步资料
     *
     * @param classId 基础资料类别
     * @param data    数据
     */
    @Override
    @Transactional(propagation = Propagation.NEVER)
    @ApiMapping(value = "kingdee.eas.hrp.sms.bz.synchronizeBaseData", useLogin = true)
    public Map<String, Object> synchronizeBaseData(Integer classId, JSONArray data) {

        Map<String, Object> ret = new HashMap<>(4);

        if (data.size() > MAX_PER_TIME_UPLOAD_BASE_DATA) {
            throw new BusinessLogicRunTimeException("你提交的数据太多：每次最多同步" + MAX_PER_TIME_UPLOAD_BASE_DATA + "条数据");
        }

        ITemplateService templateService = Environ.getBean(ITemplateService.class);

        Map<String, Object> formTemplate = templateService.getFormTemplate(classId, 1);

        // 开多线程进行数据同步操作
        int threadCount = getThreadCount(data.size());


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount * 2, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        CompletionService<Result> completionService = new ExecutorCompletionService<Result>(threadPoolExecutor);

        for (int i = 0; i < data.size(); i++) {

            // 提交每一条同步数据任务到线程池
            completionService.submit(new DoSyncBaseData(classId, data.getJSONObject(i), formTemplate));

        }
        //同步成功的记录
        List<Map<String, Object>> list_success = new ArrayList<>();
        //同步失败的记录
        List<Map<String, Object>> list_fail = new ArrayList<>();

        // 检查线程池任务执行结果
        getSyncResult(data, completionService, list_success, list_fail);

        // 关闭线程池
        threadPoolExecutor.shutdown();

        ret.put("success", list_success);
        ret.put("success_count", list_success.size());
        ret.put("fail", list_fail);
        ret.put("fail_count", list_fail.size());

        return ret;

    }

    /**
     * hrp上传订单到sms
     *
     * @param orders 订单列表
     */
    @Override
    @Transactional(propagation = Propagation.NEVER)
    @ApiMapping(value = "kingdee.eas.hrp.sms.bz.synchronizationOrder", useLogin = true)
    public Map<String, Object> synchronizationOrder(JSONArray orders) {

        return doSync(orders, SyncType.ORDER);

    }

    /**
     * hrp确认接单信息
     *
     * @param orders
     */
    @Override
    public void confirmOrder(List<ConfirmOrderModel> orders) {
        /* TODO */
    }

    /**
     * hrp同步入库单到sms
     *
     * @param warehouses
     */
    @Override
    @Transactional(propagation = Propagation.NEVER)
    @ApiMapping(value = "kingdee.eas.hrp.sms.bz.synchronizeInWarehouse", useLogin = true)
    public Map<String, Object> synchronizeInWarehouse(JSONArray warehouses) {

        return doSync(warehouses, SyncType.WAREHOUSE);

    }

    /**
     * hrp同步收货单到sms
     *
     * @param receipts
     */
    @Override
    @Transactional(propagation = Propagation.NEVER)
    @ApiMapping(value = "kingdee.eas.hrp.sms.bz.synchronizeReceipt", useLogin = true)
    public Map<String, Object> synchronizeReceipt(JSONArray receipts) {
        return doSync(receipts, SyncType.RECEIPT);
    }

    /**
     * hrp同步收货单到hrp
     *
     * @param returns
     */
    @Override
    @Transactional(propagation = Propagation.NEVER)
    @ApiMapping(value = "kingdee.eas.hrp.sms.bz.synchronizeReturns", useLogin = true)
    public Map<String, Object> synchronizeReturns(JSONArray returns) {

        return doSync(returns, SyncType.RETURNS);
    }

    /**
     * 基础资料同步处理类
     */
    private class DoSyncBaseData implements Callable<Result> {
        /**
         * 审核字段key
         */
        private static final String KEY_REVIEW = "review";
        /**
         * 同步字段key
         */
        private static final String KEY_SYNC_STATUS = "syncStatus";
        /**
         * 基础资料类别
         */
        private int classId;
        /**
         * 一条基础资料数据
         */
        private JSONObject item;
        /**
         * 基础资料模板
         */
        private Map<String, Object> formTemplate;

        public DoSyncBaseData(int classId, JSONObject item, Map<String, Object> formTemplate) {
            this.classId = classId;
            this.item = item;
            this.formTemplate = formTemplate;
        }


        @Override
        public Result call() throws Exception {

            Result ret = new Result();

            ITemplateService templateService = Environ.getBean(ITemplateService.class);

            // 主表资料描述信息
            FormClass formClass = (FormClass) formTemplate.get("formClass");

            // 主表字段模板
            Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) formTemplate.get("formFields")).get("0");
            // 主表主键key
            String primaryKey = formClass.getPrimaryKey();


            if (!item.containsKey(primaryKey)) {
                // 同步的数据必须包含主键
                ret.setCode(StatusCode.PARAMETER_ERROR);
                ret.setMsg("同步的数据必须包含主键");
                ret.setData(item);
                return ret;
            }

            if (formFields.containsKey(KEY_REVIEW)) {
                // 默认已审核
                item.put("review", "true");
            }
            if (formFields.containsKey(KEY_SYNC_STATUS)) {
                // 默认已同步
                item.put("syncStatus", "true");
            }

            // 主键值
            String interId = item.getString(primaryKey);

            try {
                // 如果已经存在该记录则更新，否则新增
                if (null == templateService.getItemById(classId, interId)) {
                    templateService.addItem(classId, item.toJSONString());
                } else {
                    templateService.editItem(classId, interId, item.toJSONString());
                }
            } catch (Exception e) {
                ret.setCode(StatusCode.BUSINESS_LOGIC_ERROR);
                ret.setMsg(e.getMessage());
            }

            ret.setData(item);


            return ret;
        }
    }

    /**
     * 订单同步处理类
     */
    private class DoSyncOrder implements Callable<Result> {

        ITemplateService templateService = Environ.getBean(ITemplateService.class);
        /**
         * 一条订单数据
         */
        private JSONObject item;

        public DoSyncOrder(JSONObject item) {
            this.item = item;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Result call() throws Exception {

            Result ret = new Result();

            Order order = new Order();
            OrderEntry orderEntry;

            // 查询供应商平台是否已经存在该订单
            Map<String, Object> oldOrder = templateService.getItemById(2019, item.get("id").toString());

            //构建订单数据--表头
            order.setId(item.getString("id"));
            order.setSupplier(item.getString("supplier"));
            order.setPurchasePerson(item.getString("purchasePerson"));
            order.setSaleProxy(item.getByte("saleProxy"));
            order.setIsInTax(item.getByte("isInTax"));
            order.setNumber(item.getString("number"));
            order.setIsQuicken(item.getByte("isQuicken"));
            order.setCurrency(item.getString("currency"));
            order.setTotalAmount(item.getBigDecimal("totalAmount"));
            order.setTotalTax(item.getBigDecimal("totalTax"));
            order.setTotalTaxAmount(item.getBigDecimal("totalTaxAmount"));
            order.setBaseStatus(item.getByte("baseStatus"));

            if (item.getDate("bizDate") != null) {
                order.setBizDate(item.getDate("bizDate"));
            }

            if (item.getString("createTime") != null) {
                order.setCreateTime(item.getDate("createTime"));
            }

            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
            TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

            try {

                OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
                OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);

                // 订单存在则更新，反之新增
                if (oldOrder == null) {
                    orderMapper.insertSelective(order);
                } else {
                    orderMapper.updateByPrimaryKeySelective(order);
                }

                //构建订单数据--表体
                JSONArray orderEntries = JSONArray.parseArray(item.getString("entries"));

                for (int i = 0; i < orderEntries.size(); i++) {

                    JSONObject orderEntryData = orderEntries.getJSONObject(i);
                    orderEntry = new OrderEntry();

                    // 订单明细物料信息
                    orderEntry.setMaterial(orderEntryData.getString("material"));
                    orderEntry.setUnit(orderEntryData.getString("unit"));
                    orderEntry.setParent(orderEntryData.getString("parent"));
                    orderEntry.setPrice(orderEntryData.getBigDecimal("price"));
                    orderEntry.setQty(BigDecimal.valueOf(orderEntryData.getFloatValue("qty")));
                    orderEntry.setDiscountRate(orderEntryData.getDouble("discountRate"));
                    orderEntry.setTaxRate(orderEntryData.getDouble("taxRate"));
                    orderEntry.setTaxPrice(orderEntryData.getBigDecimal("taxPrice"));
                    orderEntry.setActualTaxPrice(orderEntryData.getBigDecimal("actualTaxPrice"));
                    orderEntry.setDiscountAmount(orderEntryData.getBigDecimal("discountAmount"));
                    orderEntry.setTax(orderEntryData.getBigDecimal("tax"));
                    orderEntry.setLocalAmount(orderEntryData.getBigDecimal("localAmount"));
                    orderEntry.setSeq(orderEntryData.getInteger("seq"));
                    orderEntry.setId(orderEntryData.getString("id"));
                    orderEntry.setAmount(orderEntryData.getBigDecimal("amount"));

                    if (orderEntryData.getString("deliveryDate") != null) {
                        orderEntry.setDeliveryDate(orderEntryData.getDate("deliveryDate"));
                    }

                    if (oldOrder == null) {
                        orderEntryMapper.insertSelective(orderEntry);
                    } else {
                        orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
                    }

                }


                SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
                Supplier supplier = supplierMapper.selectByPrimaryKey(order.getSupplier());
                if (supplier.getMobile() != null && !supplier.getMobile().equals("")) {
                    String smsContent = String.format("您有新的订单消息,订单号:%s!请及时处理", order.getNumber());
                    String[] mobiles = supplier.getMobile().split(",");
                    MsgUtils.sendSMS(mobiles, smsContent);
                }


                // 操作成功提交事务
                txManager.commit(status);

                ret.setData(order);
                ret.setMsg("上传成功");

            } catch (Exception e) {
                // 操作失败-回滚事务
                txManager.rollback(status);

                ret.setCode(StatusCode.BUSINESS_LOGIC_ERROR);
                ret.setData(order);
                ret.setMsg(e.getMessage());
            }

            return ret;
        }
    }

    /**
     * 入库单同步处理类
     */
    private class DoSyncWarehouse implements Callable<Result> {


        /**
         * 一条入库单数据
         */
        private JSONObject item;

        public DoSyncWarehouse(JSONObject item) {
            this.item = item;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Result call() throws Exception {

            Result ret = new Result();

            PurInWarehs purInWarehs = new PurInWarehs();
            PurInWarehsEntry purInWarehsEntry;

            purInWarehs.setId(item.getString("id"));
            purInWarehs.setNumber(item.getString("number"));

            if (item.getDate("bizDate") != null) {
                purInWarehs.setBizDate(item.getDate("bizDate"));
            }

            purInWarehs.setBaseStatus(item.getByte("baseStatus"));

            purInWarehs.setSourceBillType(item.getString("sourceBillType"));
            purInWarehs.setSupplier(item.getString("supplier"));

            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
            TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

            PurInWarehsMapper purInWarehsMapper = sqlSession.getMapper(PurInWarehsMapper.class);
            PurInWarehsEntryMapper purInWarehsEntryMapper = sqlSession.getMapper(PurInWarehsEntryMapper.class);

            try {
                // 如果存在，先删除再新增
                if (purInWarehsMapper.selectByPrimaryKey(item.getString("id")) != null) {
                    //删除表头
                    purInWarehsMapper.deleteByPrimaryKey(item.getString("id"));

                    PurInWarehsEntryExample example = new PurInWarehsEntryExample();
                    PurInWarehsEntryExample.Criteria criteria = example.createCriteria();
                    criteria.andParentEqualTo(item.getString("id"));
                    // 删除表体
                    purInWarehsEntryMapper.deleteByExample(example);
                }

                purInWarehsMapper.insertSelective(purInWarehs);

                JSONArray entries = item.getJSONObject("entry").getJSONArray("1");

                for (int i = 0; i < entries.size(); i++) {

                    purInWarehsEntry = new PurInWarehsEntry();

                    JSONObject entry = entries.getJSONObject(i);

                    purInWarehsEntry.setId(entry.getString("id"));
                    purInWarehsEntry.setParent(entry.getString("parent"));
                    purInWarehsEntry.setSeq(entry.getInteger("seq"));
                    purInWarehsEntry.setOrderId(entry.getString("orderId"));
                    purInWarehsEntry.setOrderSeq(entry.getString("orderSeq"));
                    purInWarehsEntry.setMaterial(entry.getString("material"));
                    purInWarehsEntry.setLot(entry.getString("lot"));
                    purInWarehsEntry.setInnercode(entry.getString("innercode"));
                    purInWarehsEntry.setUnit(entry.getString("unit"));
                    purInWarehsEntry.setPrice(entry.getBigDecimal("price"));
                    purInWarehsEntry.setActualQty(entry.getBigDecimal("actualQty"));
                    purInWarehsEntry.setDyManufacturer(entry.getString("dyManufacturer"));
                    purInWarehsEntry.setRegistrationNo(entry.getString("registrationNo"));
                    purInWarehsEntry.setAmount(entry.getBigDecimal("amount"));

                    if (entry.getDate("dyProDate") != null) {
                        purInWarehsEntry.setDyProDate(entry.getDate("dyProDate"));
                    }
                    if (entry.getDate("effectiveDate") != null) {
                        purInWarehsEntry.setEffectiveDate(entry.getDate("effectiveDate"));
                    }

                    purInWarehsEntryMapper.insertSelective(purInWarehsEntry);

                }

                // 操作成功提交事务
                txManager.commit(status);

                ret.setData(item);
                ret.setMsg("上传成功");

            } catch (Exception e) {
                // 操作失败-回滚事务
                txManager.rollback(status);

                ret.setCode(StatusCode.BUSINESS_LOGIC_ERROR);
                ret.setData(item);
                ret.setMsg(e.getMessage());
            }

            return ret;
        }
    }

    /**
     * 收货单同步处理类
     */
    private class DoSyncReceipt implements Callable<Result> {

        /**
         * 一条入库单数据
         */
        private JSONObject item;

        public DoSyncReceipt(JSONObject item) {
            this.item = item;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Result call() throws Exception {

            Result ret = new Result();

            PurReceival purReceival = new PurReceival();
            PurReceivalEntry purReceivalEntry;

            purReceival.setId(item.getString("id"));
            purReceival.setNumber(item.getString("number"));

            if (item.getDate("bizDate") != null) {
                purReceival.setBizDate(item.getDate("bizDate"));
            }
            purReceival.setBaseStatus(item.getByte("baseStatus"));

            purReceival.setSourceBillType(item.getString("sourceBillType"));
            purReceival.setSupplier(item.getString("supplier"));

            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
            TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

            PurReceivalMapper purReceivalMapper = sqlSession.getMapper(PurReceivalMapper.class);
            PurReceivalEntryMapper purReceivalEntryMapper = sqlSession.getMapper(PurReceivalEntryMapper.class);

            try {
                // 如果存在，先删除再新增
                if (purReceivalMapper.selectByPrimaryKey(item.getString("id")) != null) {
                    // 删除表头
                    purReceivalMapper.deleteByPrimaryKey(item.getString("id"));

                    PurReceivalEntryExample example = new PurReceivalEntryExample();
                    PurReceivalEntryExample.Criteria criteria = example.createCriteria();
                    criteria.andParentEqualTo(item.getString("id"));
                    // 删除表体
                    purReceivalEntryMapper.deleteByExample(example);
                }

                purReceivalMapper.insertSelective(purReceival);

                JSONArray entries = item.getJSONObject("entry").getJSONArray("1");

                for (int i = 0; i < entries.size(); i++) {

                    purReceivalEntry = new PurReceivalEntry();
                    JSONObject entry = entries.getJSONObject(i);
                    purReceivalEntry.setId(entry.getString("id"));
                    purReceivalEntry.setParent(entry.getString("parent"));
                    purReceivalEntry.setSeq(entry.getInteger("seq"));
                    purReceivalEntry.setOrderId(entry.getString("orderId"));
                    purReceivalEntry.setOrderSeq(entry.getString("orderSeq"));
                    purReceivalEntry.setMaterial(entry.getString("material"));
                    purReceivalEntry.setLot(entry.getString("lot"));
                    purReceivalEntry.setInnercode(entry.getString("innercode"));
                    purReceivalEntry.setUnit(entry.getString("unit"));
                    purReceivalEntry.setPrice(entry.getBigDecimal("price"));
                    purReceivalEntry.setQty(entry.getBigDecimal("qty"));
                    purReceivalEntry.setActualQty(entry.getBigDecimal("actualQty"));
                    if (entry.getDate("dyProDate") != null) {
                        purReceivalEntry.setDyProDate(entry.getDate("dyProDate"));
                    }
                    purReceivalEntry.setDyManufacturer(entry.getString("dyManufacturer"));
                    purReceivalEntry.setRegistrationNo(entry.getString("registrationNo"));
                    purReceivalEntry.setAmount(entry.getBigDecimal("amount"));
                    if (entry.getDate("effectiveDate") != null) {
                        purReceivalEntry.setEffectiveDate(entry.getDate("effectiveDate"));
                    }
                    purReceivalEntry.setQualifiedQty(entry.getBigDecimal("qualifiedQty"));
                    purReceivalEntry.setUnqualifiedQty(entry.getBigDecimal("unqualifiedQty"));

                    purReceivalEntryMapper.insertSelective(purReceivalEntry);

                }

                // 操作成功提交事务
                txManager.commit(status);

                ret.setData(item);
                ret.setMsg("上传成功");

            } catch (Exception e) {
                // 操作失败-回滚事务
                txManager.rollback(status);

                ret.setCode(StatusCode.BUSINESS_LOGIC_ERROR);
                ret.setData(item);
                ret.setMsg(e.getMessage());
            }

            return ret;
        }
    }


    /**
     * 退货单同步处理类
     */
    private class DoSyncReturn implements Callable<Result> {

        /**
         * 一条入库单数据
         */
        private JSONObject item;

        public DoSyncReturn(JSONObject item) {
            this.item = item;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Result call() throws Exception {

            Result ret = new Result();

            PurReturns purReturns = new PurReturns();
            PurReturnsEntry purReturnsEntry;

            purReturns.setId(item.getString("id"));
            purReturns.setNumber(item.getString("number"));
            if (item.getDate("bizDate") != null) {
                purReturns.setBizDate(item.getDate("bizDate"));
            }
            purReturns.setBaseStatus(item.getByte("baseStatus"));
            purReturns.setSourceBillType(item.getString("sourceBillType"));
            purReturns.setSupplier(item.getString("supplier"));

            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
            TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

            PurReturnsMapper purReturnsMapper = sqlSession.getMapper(PurReturnsMapper.class);
            PurReturnsEntryMapper purReturnsEntryMapper = sqlSession.getMapper(PurReturnsEntryMapper.class);

            try {
                // 如果存在，先删除再新增
                if (purReturnsMapper.selectByPrimaryKey(item.getString("id")) != null) {
                    // 删除表头
                    purReturnsMapper.deleteByPrimaryKey(item.getString("id"));

                    PurReturnsEntryExample example = new PurReturnsEntryExample();
                    PurReturnsEntryExample.Criteria criteria = example.createCriteria();
                    criteria.andParentEqualTo(item.getString("id"));
                    // 删除表体
                    purReturnsEntryMapper.deleteByExample(example);
                }

                purReturnsMapper.insertSelective(purReturns);

                JSONArray entries = item.getJSONObject("entry").getJSONArray("1");

                for (int i = 0; i < entries.size(); i++) {

                    purReturnsEntry = new PurReturnsEntry();
                    JSONObject entry = entries.getJSONObject(i);

                    purReturnsEntry.setId(entry.getString("id"));
                    purReturnsEntry.setOrderId(entry.getString("orderId"));
                    purReturnsEntry.setOrderSeq(entry.getString("orderSeq"));
                    purReturnsEntry.setMaterial(entry.getString("material"));
                    purReturnsEntry.setParent(entry.getString("parent"));
                    purReturnsEntry.setUnit(entry.getString("unit"));
                    purReturnsEntry.setReturnQty(entry.getBigDecimal("returnQty"));

                    purReturnsEntryMapper.insertSelective(purReturnsEntry);

                }

                // 操作成功提交事务
                txManager.commit(status);

                ret.setData(item);
                ret.setMsg("上传成功");

            } catch (Exception e) {
                // 操作失败-回滚事务
                txManager.rollback(status);

                ret.setCode(StatusCode.BUSINESS_LOGIC_ERROR);
                ret.setData(item);
                ret.setMsg(e.getMessage());
            }

            return ret;
        }
    }


    private enum SyncType {

        ORDER, WAREHOUSE, RECEIPT, RETURNS
    }

    /**
     * 同步
     *
     * @param tasks
     * @return
     */
    private Map<String, Object> doSync(JSONArray tasks, SyncType type) {
        Map<String, Object> ret = new HashMap<>(4);

        if (tasks.size() > MAX_PER_TIME_UPLOAD_ORDER) {
            throw new BusinessLogicRunTimeException("你提交的订单太多：每次最多同步" + MAX_PER_TIME_UPLOAD_ORDER + "张订单");
        }

        // 开多线程进行数据同步操作
        int threadCount = getThreadCount(tasks.size());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        CompletionService<Result> completionService = new ExecutorCompletionService<Result>(threadPoolExecutor);

        for (int i = 0; i < tasks.size(); i++) {

            // 提交每一条同步数据任务到线程池

            if (type == SyncType.ORDER) {
                completionService.submit(new DoSyncOrder(tasks.getJSONObject(i)));
            } else if (type == SyncType.WAREHOUSE) {
                completionService.submit(new DoSyncWarehouse(tasks.getJSONObject(i)));
            } else if (type == SyncType.RECEIPT) {
                completionService.submit(new DoSyncReceipt(tasks.getJSONObject(i)));
            }
        }
        //同步成功的记录
        List<Map<String, Object>> success = new ArrayList<>();
        //同步失败的记录
        List<Map<String, Object>> fail = new ArrayList<>();

        // 检查线程池任务执行结果
        getSyncResult(tasks, completionService, success, fail);

        // 关闭线程池
        threadPoolExecutor.shutdown();

        ret.put("success", success);
        ret.put("success_count", success.size());
        ret.put("fail", fail);
        ret.put("fail_count", fail.size());

        return ret;
    }

    /**
     * 获取异步线程执行结果
     *
     * @param items             任务集合
     * @param completionService 执行器
     * @param list_success      成功的结果
     * @param list_fail         失败的结果
     */
    private void getSyncResult(JSONArray items, CompletionService<Result> completionService, List<Map<String, Object>> list_success, List<Map<String, Object>> list_fail) {

        // 检查线程池任务执行结果
        for (int i = 0; i < items.size(); i++) {
            try {

                //线程执行结果
                Result r = completionService.take().get();

                if (r.getCode() == 200) {
                    // 同步成功
                    list_success.add((Map<String, Object>) r.getData());

                } else {
                    // 同步失败
                    Map<String, Object> item = new HashMap<>(2);
                    // 元数据
                    item.put("data", r.getData());
                    // 失败原因
                    item.put("msg", r.getMsg());
                    list_fail.add(item);
                }

            } catch (Exception e) {

                Map<String, Object> item = new HashMap<>(2);
                // 元数据
                item.put("data", items.getJSONObject(i));
                // 失败原因
                item.put("msg", e.getMessage());
                list_fail.add(item);

                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 确定异步线程数量
     *
     * @param taskCount
     * @return
     */
    private int getThreadCount(int taskCount) {

        if (taskCount <= 0) {
            return 1;
        }
        // 平均每个线程的任务数
        double perThreadTasks = 5;
        // 最大线程数量
        int maxThreadCount = 8;

        int threadCount = (int) Math.ceil(taskCount / perThreadTasks);

        return threadCount > maxThreadCount ? maxThreadCount : threadCount;

    }

}
