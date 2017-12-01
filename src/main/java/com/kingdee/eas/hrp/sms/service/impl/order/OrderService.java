package com.kingdee.eas.hrp.sms.service.impl.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.customize.OrderDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ItemMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SupplierMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.service.api.Anomaly.IAnomalyService;
import com.kingdee.eas.hrp.sms.service.api.ISerialNumberService;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.IWebService;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Common;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.MsgUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService extends BaseService implements IOrderService {

    @Resource
    IWebService IWebService;

    @Resource
    ISerialNumberService ISerialNumberService;

    /**
     * 同步订单
     */
    @Override
    @Transactional
    public String order(JSONArray orderjson) {
        Order order = new Order();
        OrderEntry orderEntry = new OrderEntry();
        ITemplateService templateService = Environ.getBean(ITemplateService.class);

        for (int i = 0; i < orderjson.size(); i++) {
            // 录入订单抬头
            JSONObject ob = orderjson.getJSONObject(i);
            Map<String, Object> type = templateService.getItemById(2019, ob.get("id").toString());
            order.setId(ob.getString("id"));
            order.setSupplier(ob.getString("supplier"));
            order.setPurchasePerson(ob.getString("purchasePerson"));
            order.setSaleProxy(ob.getByte("saleProxy"));
            order.setIsInTax(ob.getByte("isInTax"));
            order.setNumber(ob.getString("number"));
            order.setIsQuicken(ob.getByte("isQuicken"));
            order.setCurrency(ob.getString("currency"));
            order.setTotalAmount(ob.getBigDecimal("totalAmount"));
            order.setTotalTax(ob.getBigDecimal("totalTax"));
            order.setTotalTaxAmount(ob.getBigDecimal("totalTaxAmount"));
            order.setBaseStatus(ob.getByte("baseStatus"));
            if (ob.getDate("bizDate") != null) {
                order.setBizDate(ob.getDate("bizDate"));
            }
            if (ob.getString("createTime") != null) {
                order.setCreateTime(ob.getDate("createTime"));
            }
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            if (type == null) {
                orderMapper.insertSelective(order);
            } else {
                orderMapper.updateByPrimaryKeySelective(order);
            }

            JSONArray orderEntryArray = JSONArray.parseArray(ob.getString("entries"));
            for (int j = 0; j < orderEntryArray.size(); j++) {
                // 录入订单物料信息
                JSONObject orderEntryObject = orderEntryArray.getJSONObject(j);
                orderEntry.setMaterial(orderEntryObject.getString("material"));
                orderEntry.setUnit(orderEntryObject.getString("unit"));
                orderEntry.setParent(orderEntryObject.getString("parent"));
                orderEntry.setPrice(orderEntryObject.getBigDecimal("price"));
                orderEntry.setQty(BigDecimal.valueOf(orderEntryObject.getFloatValue("qty")));
                orderEntry.setDiscountRate(orderEntryObject.getDouble("discountRate"));
                orderEntry.setTaxRate(orderEntryObject.getDouble("taxRate"));
                orderEntry.setTaxPrice(orderEntryObject.getBigDecimal("taxPrice"));
                orderEntry.setActualTaxPrice(orderEntryObject.getBigDecimal("actualTaxPrice"));
                orderEntry.setDiscountAmount(orderEntryObject.getBigDecimal("discountAmount"));
                orderEntry.setTax(orderEntryObject.getBigDecimal("tax"));
                orderEntry.setLocalAmount(orderEntryObject.getBigDecimal("localAmount"));
                orderEntry.setSeq(orderEntryObject.getInteger("seq"));
                orderEntry.setId(orderEntryObject.getString("id"));
                orderEntry.setAmount(orderEntryObject.getBigDecimal("amount"));
                orderEntry.setDepartment(orderEntryObject.getString("entryOrgUnit"));// 增加部门同步
                if (orderEntryObject.getString("deliveryDate") != null) {
                    orderEntry.setDeliveryDate(orderEntryObject.getDate("deliveryDate"));
                }
                OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
                if (type == null) {
                    orderEntryMapper.insertSelective(orderEntry);
                } else {
                    orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
                }
            }
            SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
            Supplier supplier = supplierMapper.selectByPrimaryKey(order.getSupplier());
            if (supplier.getMobile() != null && !supplier.getMobile().equals("")) {
                String smsContent = "您有新的订单消息,订单号:" + order.getNumber() + "!请及时处理";
                String[] mobiles = supplier.getMobile().split(",");
                MsgUtils.sendSMS(mobiles, smsContent);
            }

        }

        return "success";
    }

    /**
     * 采购订单接单
     */

    @Override
    @Transactional
    public void tick(String id, String entryStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order.getTickType() != null && order.getTickType().equals("1")) {
            throw new BusinessLogicRunTimeException("HRP已同意接单，不可重复接单");
        }

        // 調用hrp-web-service --发送接单数据至HRP
        JSONObject json = new JSONObject();
        json.put("entry", entryStr);
        json.put("id", id);
        String response = IWebService.webService(json.toString(), "sms2hrpOrderTake");
        JSONObject rps = JSONObject.parseObject(response);
        if (rps == null || rps.equals("")) {
            throw new BusinessLogicRunTimeException("网络错误!接单失败，请稍后再试");
        }
        if (rps.get("code").equals("200")) {
            // 发送成功后开启事务更新本地订单接单状态

            PlatformTransactionManager txManager = Environ.getBean(PlatformTransactionManager.class);

            TransactionTemplate template = new TransactionTemplate(txManager);

            template.execute(new TransactionCallback<Object>() {

                @Override
                public Object doInTransaction(TransactionStatus status) {

                    try {

                        JSONObject entrys = JSONObject.parseObject(entryStr);

                        if (entrys.size() == 0) {
                            // 没有单据分录数据
                            throw new BusinessLogicRunTimeException("数据错误");
                        }

                        // 只处理第一个子表
                        JSONArray entry1 = entrys.getJSONArray("1");

                        for (Iterator<Object> it = entry1.iterator(); it.hasNext(); ) {
                            Object obj = it.next();
                            JSONObject entryItem = (JSONObject) JSON.toJSON(obj);
                            // 分录id
                            String entryId = entryItem.getString("entryId");
                            // 接单数量
                            float confirmQty = entryItem.getFloatValue("confirmQty");

                            String dataStr = entryItem.getString("confirmDate");

                            if (dataStr == null || dataStr.isEmpty()) {
                                throw new BusinessLogicRunTimeException("数据错误,缺少接单日期");
                            }

                            Date confirmDate = sdf.parse(dataStr); // 接单日期

                            if ("".equals(entryId)) {
                                throw new BusinessLogicRunTimeException("数据错误,缺少分录内码");
                            }
                            if (confirmQty <= 0) {
                                throw new BusinessLogicRunTimeException("数据错误,缺少接单数量");
                            }
                            if (null == confirmDate) {
                                throw new BusinessLogicRunTimeException("数据错误,缺少接单日期或格式不正确");
                            }
                            if (confirmDate.before(sdf.parse(sdf.format(new Date())))) {
                                throw new BusinessLogicRunTimeException("数据错误,接单日期不能早于当前日期");
                            }

                            // 更新采购订单接单数据

                            OrderEntryMapper entryMapper = sqlSession.getMapper(OrderEntryMapper.class);

                            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

                            Order order = new Order();
                            order.setConfirmTick(Byte.parseByte("1"));
                            order.setConfirmTickDate(new Date());
                            order.setId(id);

                            OrderEntry orderEntry = new OrderEntry();

                            orderEntry.setId(entryId);
                            orderEntry.setConfirmQty(BigDecimal.valueOf(confirmQty));
                            orderEntry.setConfirmDate(confirmDate);
                            orderEntry.setInvoiceQty(new BigDecimal(0));
                            orderMapper.updateByPrimaryKeySelective(order);
                            entryMapper.updateByPrimaryKeySelective(orderEntry);

                            // 调用短信接口发送短信
                            // MsgUtil.sendSMS("a", "a");
                        }

                    } catch (ParseException e) {
                        status.setRollbackOnly();
                        throw new BusinessLogicRunTimeException("日期格式不正确");
                    } catch (Exception e) {
                        status.setRollbackOnly();
                        throw new BusinessLogicRunTimeException(e);
                    }

                    return "success";
                }
            });
        } else {
            throw new BusinessLogicRunTimeException("接单失败,确认数量或确认交货日期不能为空");
        }
    }

    @Override
    public Map<String, Object> deliver(String items) {

        Map<String, Object> shipOrder = new HashMap<String, Object>();

        ITemplateService templateService = Environ.getBean(ITemplateService.class);

        JSONArray purOrders = new JSONArray();// 采购订单数据

        String[] split = items.split("\\,");

        for (int i = 0; i < split.length; i++) {

            Map<String, Object> purOrder = templateService.getItemById(2019, split[i]);

            if (null != purOrder) {
                purOrders.add(purOrder);
            }

        }

        if (purOrders.size() == 0) {
            throw new BusinessLogicRunTimeException("数据错误，请校验采购订单数据准确性！");
        }

        String supplier = "";
        int saleProxy = 0;

        // 循环采购订单，构建发货单
        // 多张订单合并时，发货单单据头只携带第一张采购订单数据
        for (int i = 0; i < purOrders.size(); i++) {

            JSONObject purOrder = JSON.parseObject(JSON.toJSONString(purOrders.get(i)));// 采购订单主表信息

            String purOrderNo = purOrder.getString("number");

            if (i == 0) {
                supplier = purOrder.getString("supplier");
            } else if (!supplier.equals(purOrder.getString("supplier"))) {
                throw new BusinessLogicRunTimeException("不同供应商的订单不允许合并发货！");
            }

            //需求变动，医院不需要确认接单
         /*   if (purOrder.getIntValue("tickType") == 0) {
                throw new BusinessLogicRunTimeException("采购订单[" + purOrderNo + "]医院未确认接单,不可发货");
            }*/

            if (i == 0) {
                saleProxy = purOrder.getInteger("saleProxy");
            } else if (saleProxy != purOrder.getInteger("saleProxy")) {
                throw new BusinessLogicRunTimeException("采购模式不同的订单不能产生一张发货单");
            }

            if (i == 0) {
                // 第一张订单-构建发货单表头
                shipOrder = generateHead(purOrder);
            }

            generateEntries(shipOrder, purOrder);

        }
        if (shipOrder.containsKey("entry") == false) {
            throw new BusinessLogicRunTimeException("接单数量已发货完毕，不能再发货");
        }
        return shipOrder;
    }

    /**
     * 构建发货单表体数据
     * <p>
     * 往shipOrder中增加表体数据
     *
     * @param shipOrder 构建中的发货单
     * @param purOrder  一张采购订单
     * @return void
     * @Title generateEntries
     * @date 2017-05-20 12:07:05 星期六
     */
    private void generateEntries(Map<String, Object> shipOrder, JSONObject purOrder) {

        // 销售模式 1：非代销 2：代销
        int saleProxy = purOrder.getIntValue("saleProxy");
        // 采购订单子表数据，值处理第一个子表
        JSONArray purOrderEntries = purOrder.getJSONObject("entry").getJSONArray("1");

        for (int j = 0; j < purOrderEntries.size(); j++) {

            // 第一条分录
            JSONObject purOrderEntry = purOrderEntries.getJSONObject(j);

            // 采购订单主表内码
            String purOrderId = purOrderEntry.getString("parent");
            // 采购订单分录行号
            int purOrderEntrySeq = purOrderEntry.getIntValue("seq");

            // 判断该分录对应的源单内码，源单分录是否已处理过数据-是，返回该分录，数量累加上本条采购订单分录数据(代销物料除外)
            Map<String, Object> entry = isInShipOrderEntries(shipOrder, purOrderId, purOrderEntrySeq);

            if (saleProxy == 2) {
                // 代销物料--拆单--拆成一个一个的物料

                List<Map<String, Object>> entryItems = generateEntryItems(purOrderEntry, purOrder);
                addToShipOrderEntry(shipOrder, entryItems);

            }
            if (saleProxy == 1) {
                // 非代销物料--一条采购订单分录对应一条发货单分录
                BigDecimal qty = purOrderEntry.getBigDecimal("confirmQty").subtract(purOrderEntry.getBigDecimal("invoiceQty"));
                if (qty.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                Map<String, Object> entryItem = generateEntryItem(purOrderEntry, purOrder);
                addToShipOrderEntry(shipOrder, entryItem);

            }

        }

    }

    /**
     * 向发货单上增加一条分录数据
     *
     * @param shipOrder 发货单
     * @param entryItem 发货单分录数据
     * @return void
     * @Title addToShipOrderEntry
     * @date 2017-05-20 13:26:03 星期六
     */
    private void addToShipOrderEntry(Map<String, Object> shipOrder, Map<String, Object> entryItem) {

        Object obj = shipOrder.get("entry");

        if (null == obj) {
            // 没有任何分录,没有entry节点
            JSONObject entries = new JSONObject();
            JSONArray entry1Array = new JSONArray();

            entryItem.put("seq", 1); // 添加发货单行号

            entry1Array.add(entryItem);
            entries.put("1", entry1Array);
            shipOrder.put("entry", entries);

            return;
        }

        JSONObject entry = JSON.parseObject(JSON.toJSONString(obj));

        if (null == entry || entry.isEmpty()) {
            // 有entry节点但没有任何分录
            JSONArray entry1Array = new JSONArray();

            entryItem.put("seq", 1); // 添加发货单行号

            entry1Array.add(entryItem);
            entry.put("1", entry1Array);
            shipOrder.put("entry", entry);
            return;
        }

        JSONArray entry1List = entry.getJSONArray("1");

        entryItem.put("seq", entry1List.size() + 1); // 添加发货单行号

        entry1List.add(entryItem);

        JSONObject entries = new JSONObject();

        entries.put("1", entry1List);

        shipOrder.put("entry", entries);

        return;
    }

    /**
     * 向发货单上增加多条条分录数据
     *
     * @param shipOrder  发货单
     * @param entryItems 发货单分录数据
     * @return void
     * @Title addToShipOrderEntry
     * @date 2017-05-20 13:26:03 星期六
     */
    private void addToShipOrderEntry(Map<String, Object> shipOrder, List<Map<String, Object>> entryItems) {

        Object obj = shipOrder.get("entry");

        if (null == obj) {
            // 没有任何分录,没有entry节点
            JSONObject entries = new JSONObject();
            JSONArray entry1Array = new JSONArray();

            for (int i = 0; i < entryItems.size(); i++) {
                Map<String, Object> entryItem = entryItems.get(i);
                entryItem.put("seq", i + 1); // 添加发货单行号
                entry1Array.add(entryItems.get(i));
            }

            entries.put("1", entry1Array);
            shipOrder.put("entry", entries);

            return;
        }

        JSONObject entry = JSON.parseObject(JSON.toJSONString(obj));

        if (null == entry || entry.isEmpty()) {
            // 有entry节点但没有任何分录
            JSONArray entry1Array = new JSONArray();

            for (int i = 0; i < entryItems.size(); i++) {
                Map<String, Object> entryItem = entryItems.get(i);
                entryItem.put("seq", i + 1); // 添加发货单行号
                entry1Array.add(entryItems);
            }

            entry.put("1", entry1Array);
            shipOrder.put("entry", entry);
            return;
        }

        JSONArray entry1List = entry.getJSONArray("1");

        for (int i = 0; i < entryItems.size(); i++) {
            Map<String, Object> entryItem = entryItems.get(i);
            entryItem.put("seq", entry1List.size() + 1); // 添加发货单行号
            entry1List.add(entryItems.get(i));
        }

        JSONObject entries = new JSONObject();

        entries.put("1", entry1List);

        shipOrder.put("entry", entries);

        return;
    }

    /**
     * 根据一条采购订单分录，生成一条发货单分录数据
     *
     * @param purOrderEntry 采购订单的一条分录
     * @return Map<String,Object>
     * @Title generateEntryItem
     * @date 2017-05-20 12:27:43 星期六
     */
    private Map<String, Object> generateEntryItem(JSONObject purOrderEntry, JSONObject purOrder) {

        // 采购订单-->发货单 转换规则

        // entryId --->"" 内码
        // parent --->"" 发货单内码(主表内码)
        // seq --->1,2,3 行号
        // orderId --->parent 采购订单内码
        // orderSeq --->seq 采购订单行号
        // material --->material 物料
        // specification --->specification 规格型号
        // lot --->"" 批次
        // dyBatchNum --->"" 批号
        // code --->"" 个体码
        // price --->price 单价
        // unit --->unit 单位
        // qty --->qty(拆分) 数量
        // dyProDate --->"" 生产日期
        // dyManufacturer --->"" 生产厂家
        // registrationNo --->"" 产品注册号
        // amount --->localAmount 金额
        // effectiveDate --->"" 有效期

        IAnomalyService anomalyService = Environ.getBean(IAnomalyService.class);

        Map<String, Object> entry = new HashMap<String, Object>();
        BigDecimal qty = purOrderEntry.getBigDecimal("confirmQty").subtract(purOrderEntry.getBigDecimal("invoiceQty"));
        ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
        Item item = itemMapper.selectByPrimaryKey(purOrderEntry.getString("material"));
        entry.put("seq", 0);
        entry.put("orderId", purOrderEntry.getString("parent"));
        entry.put("orderNumber", purOrder.getString("number"));
        entry.put("orderSeq", purOrderEntry.getIntValue("seq"));
        entry.put("material", purOrderEntry.getString("material"));
        entry.put("material_DspName", purOrderEntry.getString("material_DspName"));
        entry.put("material_NmbName", purOrderEntry.getString("material_NmbName"));
        entry.put("specification", purOrderEntry.getString("specification"));

        if (item.getIsLotNumber() != null) {
            if (item.getIsLotNumber() == 1 && !item.getIsLotNumber().equals(1)) {
                entry.put("lot", Common.createNo("MM-dd", "-", 3));
            } else {
                entry.put("lot", "");
            }
        }
        entry.put("isLotNumber", item.getIsLotNumber());
        entry.put("dyBatchNum", "");
        if (item.getHighConsumable() != null) {
            if (item.getHighConsumable() == 1 && !item.getHighConsumable().equals(1)) {
                //entry.put("code", Common.createNo("yyMMdd", "", 5));
                entry.put("code", anomalyService.getDeliverOrderCodeEncode());
            }
        } else {
            entry.put("code", "");
        }
        entry.put("price", purOrderEntry.getFloatValue("price"));
        entry.put("unit", purOrderEntry.getString("unit"));
        entry.put("unit_DspName", purOrderEntry.getString("unit_DspName"));
        entry.put("unit_NmbName", purOrderEntry.getString("unit_NmbName"));
        entry.put("actualQty", qty);
        entry.put("qty", qty);
        entry.put("dyProDate", "");
        entry.put("dyManufacturer", "");
        entry.put("registrationNo", "");
        entry.put("amount", purOrderEntry.getBigDecimal("price").multiply(qty));
        entry.put("effectiveDate", "");

        if (item.getDyFactory() != null) {
            // 生产厂家取物料生产厂家
            entry.put("dyManufacturer", item.getDyFactory());
        }
        if (item.getRigsterNum() != null) {
            // 注册号取物料注册号
            entry.put("registrationNo", item.getRigsterNum());
        }

        if (purOrderEntry.getString("department") != null) {
            entry.put("department", purOrderEntry.getString("department")); // 使用部门
            entry.put("department_DspName", purOrderEntry.getString("department_DspName"));
            entry.put("department_NmbName", purOrderEntry.getString("department_NmbName"));
        }

        return entry;
    }

    /**
     * 根据一条采购订单分录，生成多条发货单分录数据
     *
     * @param purOrderEntry 采购订单的一条分录
     * @return Map<String,Object>
     * @Title generateEntryItem
     * @date 2017-05-20 12:27:43 星期六
     */
    private List<Map<String, Object>> generateEntryItems(JSONObject purOrderEntry, JSONObject purOrder) {

        // 采购订单-->发货单 转换规则

        // entryId --->"" 内码
        // parent --->"" 发货单内码(主表内码)
        // seq --->1,2,3 行号
        // orderId --->parent 采购订单内码
        // orderSeq --->seq 采购订单行号
        // material --->material 物料
        // specification --->specification 规格型号
        // lot --->"" 批次
        // dyBatchNum --->"" 批号
        // code --->"" 个体码
        // price --->price 单价
        // unit --->unit 单位
        // qty --->qty(拆分) 数量
        // dyProDate --->"" 生产日期
        // dyManufacturer --->"" 生产厂家
        // registrationNo --->"" 产品注册号
        // amount --->localAmount 金额
        // effectiveDate --->"" 有效期

        // 采购订单分录数量
        BigDecimal bQty = purOrderEntry.getBigDecimal("confirmQty").subtract(purOrderEntry.getBigDecimal("invoiceQty"));
/*        BigDecimal amount = purOrderEntry.getBigDecimal("localAmount");
        float price = purOrderEntry.getFloatValue("price");*/

        int qty = bQty.intValue();

        if (bQty.compareTo(new BigDecimal(qty)) != 0) {
            throw new BusinessLogicRunTimeException("高值耗材数量必须为正整数，请检查订单数据!");
        }

/*
        BigDecimal x1 = new BigDecimal(Float.toString(price));
        BigDecimal x2 = new BigDecimal(Float.toString(qty));
        // 数量尾差，尾差放到最后一行
        float lastLineQty = bQty.floatValue() > qty ? bQty.floatValue() - qty + 1 : 1;
        // 金额尾差，尾差放到最后一行
        BigDecimal lastLineAmount = amount.subtract(x1.multiply(purOrderEntry.getBigDecimal("invoiceQty"))).compareTo(x1.multiply(x2)) > 0
                ? x1.add(amount.subtract(x1.multiply(purOrderEntry.getBigDecimal("invoiceQty"))).subtract(x1.multiply(x2)))
                : x1.add(amount.subtract(x1.multiply(purOrderEntry.getBigDecimal("invoiceQty"))).subtract(x1.multiply(x2)));
*/

        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < qty; i++) {

            Map<String, Object> entry = new HashMap<String, Object>(qty);

            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            Item item = itemMapper.selectByPrimaryKey(purOrderEntry.getString("material"));

            entry.put("seq", 0);
            entry.put("orderId", purOrderEntry.getString("parent"));
            entry.put("orderNumber", purOrder.getString("number"));
            entry.put("orderSeq", purOrderEntry.getIntValue("seq"));
            entry.put("material", purOrderEntry.getString("material"));
            entry.put("material_DspName", purOrderEntry.getString("material_DspName"));
            entry.put("material_NmbName", purOrderEntry.getString("material_NmbName"));
            entry.put("specification", purOrderEntry.getString("specification"));
            if (item.getIsLotNumber() != null) {
                if (item.getIsLotNumber() == 1 && !item.getIsLotNumber().equals(1)) {
                    entry.put("lot", Common.createNo("MM-dd", "-", 3));
                } else {
                    entry.put("lot", "");
                }
            }

            entry.put("isLotNumber", item.getIsLotNumber());
            entry.put("dyBatchNum", "");
            if (item.getHighConsumable() != null) {
                if (item.getHighConsumable().intValue() == 1) {
                    entry.put("code", Common.createNo("yyMMdd", "", 5));
                }
            } else {
                entry.put("code", "");
            }
            entry.put("price", purOrderEntry.getFloatValue("price"));
            entry.put("unit", purOrderEntry.getString("unit"));
            entry.put("unit_DspName", purOrderEntry.getString("unit_DspName"));
            entry.put("unit_NmbName", purOrderEntry.getString("unit_NmbName"));
            entry.put("dyProDate", "");
            entry.put("dyManufacturer", "");
            entry.put("registrationNo", "");
            entry.put("effectiveDate", "");
            entry.put("actualQty", 1);
            // 拆单后，发货单明细行数量为1
            entry.put("qty", 1);
            entry.put("amount", purOrderEntry.getFloatValue("price"));

            if (item.getDyFactory() != null) {
                // 生产厂家取物料生产厂家
                entry.put("dyManufacturer", item.getDyFactory());
            }
            if (item.getRigsterNum() != null) {
                // 注册号取物料注册号
                entry.put("registrationNo", item.getRigsterNum());
            }

            if (purOrderEntry.getString("department") != null) {
                // 使用部门
                entry.put("department", purOrderEntry.getString("department"));
                entry.put("department_DspName", purOrderEntry.getString("department_DspName"));
                entry.put("department_NmbName", purOrderEntry.getString("department_NmbName"));
            }

/*            if (i == qty - 1) {
                // lastLine
                // 拆单后，发货单明细行数量为1
                entry.put("qty", lastLineQty);
                entry.put("actualQty", lastLineQty);
                // ? 本位币金额 No Amount
                entry.put("amount", lastLineAmount);
            }*/

            ret.add(entry);
        }

        return ret;
    }

    /**
     * 判断该分录对应的源单内码，源单分录是否已处理过数据
     * <p>
     * -是，返回该分录，数量累加上本条采购订单分录数据(代销物料除外)
     * <p>
     * -否，返回null
     *
     * @param shipOrder        构建中的发货单
     * @param purOrderId       采购订单
     * @param purOrderEntrySeq 采购订单内码
     * @return Map<String,Object> 采购订单分录号
     * @Title isInShipOrderEntries
     * @date 2017-05-20 12:20:26 星期六
     */
    private Map<String, Object> isInShipOrderEntries(Map<String, Object> shipOrder, String purOrderId, int purOrderEntrySeq) {

        Object obj = shipOrder.get("entry");
        if (null == obj) {
            // 没有任何分录
            return null;
        }
        JSONObject entries = JSON.parseObject(JSON.toJSONString(obj));

        if (null == entries) {
            // 没有任何分录
            return null;
        }
        JSONArray entryList = entries.getJSONArray("1");
        if (null == entryList || entryList.size() == 0) {
            // 没有任何分录
            return null;
        }

        for (int i = 0; i < entryList.size(); i++) {

            JSONObject entryItem = entryList.getJSONObject(i);

            String orderId = entryItem.getString("orderId");
            int orderSeq = entryItem.getIntValue("orderSeq");

            if (orderId.equals(purOrderId) && orderSeq == purOrderEntrySeq) {
                // 改采购订单分录已经在发货单分录中处理过--只需累加数量操作
                return entryItem;
            }
        }

        return null;
    }

    /**
     * 构建发货单表头数据
     *
     * @param purOrder 一张采购订单
     * @return Map<String,Object>
     * @Title generateHead
     * @date 2017-05-20 12:06:27 星期六
     */
    private Map<String, Object> generateHead(JSONObject purOrder) {

        Map<String, Object> shipOrderHead = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ITemplateService templateService = Environ.getBean(ITemplateService.class);

		/*
         * logistics id number logisticsNo baseType Date supplier baseStatus
		 */
        shipOrderHead.put("logistics", "");
        shipOrderHead.put("logisticsNo", "");
        shipOrderHead.put("number", ISerialNumberService.getSerialNumber(2020));

        shipOrderHead.put("Date", sdf.format(new Date()));
        shipOrderHead.put("supplier", purOrder.get("supplier")); // 采购订单"供应商"携带到发货单"供应商"字段
        shipOrderHead.put("supplier_DspName", purOrder.get("supplier_DspName")); // 采购订单"供应商名称"携带到发货单"供应商名称"字段
        shipOrderHead.put("supplier_NmbName", purOrder.get("supplier_NmbName")); // 采购订单"供应商代码"携带到发货单"供应商代码"字段
        shipOrderHead.put("saleProxy", purOrder.get("saleProxy"));// "采购模式携带到发货单"单据状态""

        shipOrderHead.put("saleProxy_DspName", purOrder.get("saleProxy_DspName"));// "采购模式名称"

        shipOrderHead.put("saleProxy_NmbName", purOrder.get("saleProxy_NmbName"));// "采购模式代码"

        shipOrderHead.put("baseStatus", 0);

        Map<String, Object> itemById = templateService.getItemById(1025, "0");
        shipOrderHead.put("baseStatus_NmbName", itemById.get("number") == null ? "" : itemById.get("number"));// 补充状态代码/名称
        shipOrderHead.put("baseStatus_DspName", itemById.get("name") == null ? "" : itemById.get("name"));

        return shipOrderHead;

    }

    /**
     * HRP->SMS接单确认接口
     */
    @Override
    @Transactional
    public String updateTickType(JSONObject jsonObject) {
        OrderEntry orderEntry = new OrderEntry();
        Order order = new Order();
        if (jsonObject.equals("")) {
            return "error";
        }
        JSONObject entry = (JSONObject) jsonObject.get("entry");
        JSONArray orderEntryArray = (JSONArray) entry.get("1");
        for (int i = 0; i < orderEntryArray.size(); i++) {
            JSONObject orderEntryObject = orderEntryArray.getJSONObject(i);
            orderEntry.setId(orderEntryObject.getString("entryId"));// 订单子表内码
            orderEntry.setLocalAmount(orderEntryObject.getBigDecimal("localAmount"));// 修改本位币金额
            orderEntry.setAmount(orderEntryObject.getBigDecimal("amount"));// 修改金额
            if (orderEntryObject.getDate("confirmDate") != null && !orderEntryObject.getDate("confirmDate").equals("")) {
                orderEntry.setConfirmDate(orderEntryObject.getDate("confirmDate"));// 修改供应商确认日期
                orderEntry.setDeliveryDate(orderEntryObject.getDate("confirmDate"));// 修改原单发货日期
            }
            if (orderEntryObject.getBigDecimal("confirmQty") != null && !orderEntryObject.getBigDecimal("confirmQty").equals("")) {
                orderEntry.setConfirmQty(orderEntryObject.getBigDecimal("confirmQty"));// 修改供应商确认数量
                orderEntry.setQty(orderEntryObject.getBigDecimal("confirmQty"));// 修改原单数量
            }
            OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
            orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
        }
        if (jsonObject.getString("id") == null || jsonObject.getString("id").equals("")) {
            return "error";
        }
        order.setId(jsonObject.getString("id"));
        order.setTickType((byte) 1);
        order.setTickDate(new Date());
        order.setTotalAmount(jsonObject.getBigDecimal("totalAmount"));
        order.setTotalTaxAmount(jsonObject.getBigDecimal("totalTaxAmount"));
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        orderMapper.updateByPrimaryKeySelective(order);
        return "success";
    }

    /**
     * 订单追踪查询
     */
    @Override
    public JSONObject traceQuery(String supplierIds, String pageNo, String pageSize, String classId, String supplier, String order, String beginDate, String endDate) {
        OrderDaoMapper orderDaoMapper = sqlSession.getMapper(OrderDaoMapper.class);
        JSONObject ret = new JSONObject();
        String orderNo = null;
        String number = null;
        String name = null;
        String startTime = null;
        String endTime = null;
        String supplierId = null;
        List<Map<String, Object>> data = null;
        if (null != order && !"".equals(order)) {
            orderNo = order;
        }
        if (null != supplier && !"".equals(supplier)) {
            supplierId = supplier;
        }
        if (null != beginDate && !"".equals(beginDate)) {
            startTime = beginDate;
        }
        if (null != endDate && !"".equals(endDate)) {

            endTime = endDate;
        }
        if (null != supplierIds && !"".equals(supplierIds)) {
            supplierId = supplierIds;
        }

        if (Integer.parseInt(pageNo) == 1) {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize), true);
        } else {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize), false);
        }
        /**
         * classId=2020 发货单 classId=2021 收货单 classId=2022 入库单 classId=2023 退货单
         */
        if (classId.equals("2020")) {
            data = orderDaoMapper.selectSendcargo(orderNo, number, name, startTime, endTime, supplierId);
        }
        if (classId.equals("2021")) {
            data = orderDaoMapper.selectPurReceival(orderNo, number, name, startTime, endTime, supplierId);
        }
        if (classId.equals("2022")) {
            data = orderDaoMapper.selectPurInwarehs(orderNo, number, name, startTime, endTime, supplierId);
        }
        if (classId.equals("2023")) {
            data = orderDaoMapper.selectPurReturns(orderNo, number, name, startTime, endTime, supplierId);
        }
        ret.put("list", data);
        if (Integer.parseInt(pageNo) == 1) {
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(data);
            ret.put("count", pageInfo.getTotal());
        }
        return ret;

    }
}
