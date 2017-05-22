package com.kingdee.eas.hrp.sms.service.impl.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Generated;
import javax.swing.plaf.TextUI;

import org.apache.commons.lang.ObjectUtils.Null;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Common;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class OrderService extends BaseService implements IOrderService {

	/**
	 * 同步订单
	 */
	@Override
	public String order(JSONArray orderjson) {
		Order order = new Order();
		OrderEntry orderEntry = new OrderEntry();
		for (int i = 0; i < orderjson.size(); i++) {
			// 录入订单抬头
			JSONObject ob = orderjson.getJSONObject(i);
			order.setId(ob.getString("id"));
			order.setSupplier(ob.getString("supplier"));
			order.setPurchasePerson(ob.getString("purchasePerson"));
			order.setSaleProxy(ob.getByte("saleProxy"));
			order.setIsInTax(ob.getByte("isInTax"));
			order.setNumber(ob.getString("number"));
			order.setIsQuicken(ob.getByte("isQuicken"));
			order.setCurrency(ob.getString("currency"));
			order.setSettlementType(ob.getString("settlementType"));
			order.setTotalAmount(ob.getBigDecimal("totalAmount"));
			order.setTotalTax(ob.getBigDecimal("totalTax"));
			order.setTotalTaxAmount(ob.getBigDecimal("totalTaxAmount"));
			order.setBaseStatus(ob.getByte("baseStutas"));
			if (ob.getDate("bizDate") != null) {
				order.setBizDate(ob.getDate("bizDate"));
			}
			if (ob.getString("createTime") != null) {
				order.setCreateTime(ob.getDate("createTime"));
			}
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			orderMapper.insertSelective(order);

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
				if (orderEntryObject.getString("deliveryDate") != null) {
					orderEntry.setDeliveryDate(orderEntryObject.getDate("deliveryDate"));
				}
				OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
				orderEntryMapper.insertSelective(orderEntry);
			}
		}
		return "success";
	}

	/**
	 * 采购订单接单
	 */
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public void tick(String id, String entryStr) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order.getTickType().equals("0")) {
			throw new BusinessLogicRunTimeException("HRP已同意接单，不可重复接单");
		}

		// 調用hrp-web-service --发送接单数据至HRP

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

					JSONArray entry1 = entrys.getJSONArray("1"); // 只处理第一个子表

					for (Iterator<Object> it = entry1.iterator(); it.hasNext();) {
						Object obj = it.next();
						JSONObject entryItem = (JSONObject) JSON.toJSON(obj);

						String entryId = entryItem.getString("entryId"); // 分录id
						float confirmQty = entryItem.getFloatValue("confirmQty"); // 接单数量

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

						orderMapper.updateByPrimaryKeySelective(order);
						entryMapper.updateByPrimaryKeySelective(orderEntry);

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
	}

	/**
	 * 产生发货单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> invoice(String data, String userType) {

		String[] idString = data.split(",");
		// 表头map
		Map<String, Object> order = new HashMap<String, Object>();
		// 表体EntryMap
		Map<String, Object> orderEntry = new HashMap<String, Object>();
		// 子表1
		ArrayList<Object> list = new ArrayList<Object>();
		// 子表1关联数据
		Map<String, Object> entry = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		ITemplateService template = Environ.getBean(ITemplateService.class);

		for (int i = 0; i < idList.size(); i++) {

			Map<String, Object> map = template.getItemById(2019, idList.get(i), userType);
			Map<String, Object> mapEntry = (Map<String, Object>) map.get("entry");
			ArrayList<Object> arrayList = (ArrayList<Object>) mapEntry.get("1");

			int s = 0;
			for (int j = 0; j < arrayList.size(); j++) {
				HashMap<String, Object> orderEntrys = (HashMap<String, Object>) arrayList.get(j);
				BigDecimal qty = (BigDecimal) orderEntrys.get("qty");
				if (Integer.parseInt(String.valueOf(map.get("tickType"))) == 0) {
					throw new BusinessLogicRunTimeException("HRP未同意接单,不可发货");
				}
				if (Integer.parseInt(String.valueOf(map.get("saleProxy"))) == 2) {
					for (int k = 0; k < qty.intValue(); k++) {
						// 表头数据
						order.put("number", Common.createShipOrderNo());
						order.put("Date", sdf.format(new Date()));
						order.put("logistics", "");
						order.put("baseType", "采购订单");
						order.put("logisticsNo", "");
						order.put("supplier_DspName", map.get("supplier_DspName"));
						order.put("supplier_NmbName", map.get("supplier_NmbName"));
						order.put("supplier", map.get("supplier"));
						order.put("baseStatus", 0);
						order.put("baseStatus_NmbName", map.get("baseStatus_NmbName"));
						order.put("baseStatus_DspName", map.get("baseStatus_DspName"));

						order.put("baseStatus", 0); // 发货单状态为新增 内码0

						Map<String, Object> itemById = template.getItemById(1025, "0", "QpXq24FxxE6c3lvHMPyYCxACEAI=");
						order.put("baseStatus_NmbName", itemById.get("number"));
						order.put("baseStatus_DspName", itemById.get("name"));

						// 表体数据
						entry.put("number", map.get("number"));
						entry.put("orderSeq", orderEntrys.get("seq"));
						entry.put("material", orderEntrys.get("material"));
						entry.put("material_NmbName", orderEntrys.get("material_NmbName"));
						entry.put("material_DspName", orderEntrys.get("material_DspName"));
						entry.put("price", orderEntrys.get("price"));
						entry.put("unit", orderEntrys.get("unit"));
						entry.put("unit_DspName", orderEntrys.get("unit_DspName"));
						entry.put("unit_NmbName", orderEntrys.get("unit_NmbName"));
						entry.put("qty", 1);
						entry.put("amount", orderEntrys.get("price"));
						entry.put("lot", "");
						entry.put("dyBatchNum", "");
						entry.put("code", "");
						entry.put("dyProDate", "");
						entry.put("dyManufacturer", "");
						entry.put("registrationNo", "");
						entry.put("effectiveDate", "");
						entry.put("seq", ++s);
						entry.put("specification", orderEntrys.get("specification"));
						list.add(entry);
						orderEntry.put("1", list);
						order.put("entry", orderEntry);
					}
				} else if (Integer.parseInt(String.valueOf(map.get("saleProxy"))) == 1) {
					// 表头
					order.put("number", Common.createShipOrderNo());
					order.put("Date", sdf.format(new Date()));
					order.put("logistics", "");
					order.put("baseType", "采购订单");
					order.put("logisticsNo", "");
					order.put("supplier", map.get("supplier"));
					order.put("supplier_DspName", map.get("supplier_DspName"));
					order.put("supplier_NmbName", map.get("supplier_NmbName"));

					order.put("baseStatus", 0); // 发货单状态为新增 内码0

					Map<String, Object> itemById = template.getItemById(1025, "0", "QpXq24FxxE6c3lvHMPyYCxACEAI=");
					order.put("baseStatus_NmbName", itemById.get("number"));
					order.put("baseStatus_DspName", itemById.get("name"));
					// 表体
					entry.put("number", map.get("number"));
					entry.put("orderSeq", orderEntrys.get("seq"));
					entry.put("material", orderEntrys.get("material"));
					entry.put("material_NmbName", orderEntrys.get("material_NmbName"));
					entry.put("material_DspName", orderEntrys.get("material_DspName"));
					entry.put("price", orderEntrys.get("price"));
					entry.put("unit", orderEntrys.get("unit"));
					entry.put("unit_DspName", orderEntrys.get("unit_DspName"));
					entry.put("unit_NmbName", orderEntrys.get("unit_NmbName"));
					entry.put("qty", orderEntrys.get("qty"));
					entry.put("amount", orderEntrys.get("localAmount"));
					entry.put("lot", "");
					entry.put("dyBatchNum", "");
					entry.put("code", "");
					entry.put("dyProDate", "");
					entry.put("dyManufacturer", "");
					entry.put("registrationNo", "");
					entry.put("effectiveDate", "");
					entry.put("seq", ++s);
					entry.put("specification", orderEntrys.get("specification"));
					list.add(entry);
					orderEntry.put("1", list);
					order.put("entry", orderEntry);
				}
			}
		}
		return order;
	}

	@Override
	public Map<String, Object> deliver(String items, String userType, String userId) {

		Map<String, Object> shipOrder = new HashMap<String, Object>();

		ITemplateService templateService = Environ.getBean(ITemplateService.class);

		JSONArray purOrders = new JSONArray();// 采购订单数据

		String[] split = items.split("\\,");

		for (int i = 0; i < split.length; i++) {

			Map<String, Object> purOrder = templateService.getItemById(2019, split[i], userType);

			if (null != purOrder) {
				purOrders.add(purOrder);
			}

		}

		if (purOrders.size() == 0) {
			throw new BusinessLogicRunTimeException("数据错误，请校验采购订单数据准确性！");
		}

		String supplier = "";

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

			if (purOrder.getIntValue("tickType") == 0) {
				throw new BusinessLogicRunTimeException("采购订单[" + purOrderNo + "]HRP未确认接单,不可发货");
			}

			if (i == 0) {
				// 第一张订单-构建发货单表头
				shipOrder = generateHead(purOrder);
			}

			generateEntries(shipOrder, purOrder);

		}

		return shipOrder;
	}

	/**
	 * 构建发货单表体数据
	 * 
	 * 往shipOrder中增加表体数据
	 * 
	 * @Title generateEntries
	 * @param shipOrder
	 *            构建中的发货单
	 * @param purOrder
	 *            一张采购订单
	 * @return void
	 * @date 2017-05-20 12:07:05 星期六
	 */
	private void generateEntries(Map<String, Object> shipOrder, JSONObject purOrder) {

		int saleProxy = purOrder.getIntValue("saleProxy"); // 销售模式 1：非代销 2：代销
		JSONArray purOrderEntries = purOrder.getJSONObject("entry").getJSONArray("1"); // 采购订单子表数据，值处理第一个子表

		for (int j = 0; j < purOrderEntries.size(); j++) {

			JSONObject purOrderEntry = purOrderEntries.getJSONObject(j); // 第一条分录

			String purOrderId = purOrderEntry.getString("parent");// 采购订单主表内码
			int purOrderEntrySeq = purOrderEntry.getIntValue("seq");// 采购订单分录行号

			// 判断该分录对应的源单内码，源单分录是否已处理过数据-是，返回该分录，数量累加上本条采购订单分录数据(代销物料除外)
			Map<String, Object> entry = isInShipOrderEntries(shipOrder, purOrderId, purOrderEntrySeq);

			if (saleProxy == 2) {
				// TODO 代销物料--拆单--拆成一个一个的物料
				
				List<Map<String, Object>> entryItems = generateEntryItems(purOrderEntry, purOrder);
				addToShipOrderEntry(shipOrder, entryItems);

			}
			if (saleProxy == 1) {
				// 非代销物料--一条采购订单分录对应一条发货单分录

				Map<String, Object> entryItem = generateEntryItem(purOrderEntry, purOrder);
				addToShipOrderEntry(shipOrder, entryItem);

			}

		}

	}

	/**
	 * 向发货单上增加一条分录数据
	 * 
	 * @Title addToShipOrderEntry
	 * @param shipOrder
	 *            发货单
	 * @param entryItem
	 *            发货单分录数据
	 * @return void
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
	 * @Title addToShipOrderEntry
	 * @param shipOrder
	 *            发货单
	 * @param entryItems
	 *            发货单分录数据
	 * @return void
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
			entry1List.add(entryItems);
		}

		JSONObject entries = new JSONObject();

		entries.put("1", entry1List);

		shipOrder.put("entry", entries);

		return;
	}

	/**
	 * 根据一条采购订单分录，生成一条发货单分录数据
	 * 
	 * @Title generateEntryItem
	 * @param purOrderEntry
	 *            采购订单的一条分录
	 * @return Map<String,Object>
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

		Map<String, Object> entry = new HashMap<String, Object>();

		entry.put("seq", 0);
		entry.put("orderId", purOrderEntry.getString("parent"));
		entry.put("orderNumber", purOrder.getString("number"));
		entry.put("orderSeq", purOrderEntry.getIntValue("seq"));
		entry.put("material", purOrderEntry.getString("material"));
		entry.put("material_DspName", purOrderEntry.getString("material_DspName"));
		entry.put("material_NmbName", purOrderEntry.getString("material_NmbName"));
		entry.put("specification", purOrderEntry.getString("specification"));
		entry.put("lot", "");
		entry.put("dyBatchNum", "");
		entry.put("code", "");
		entry.put("price", purOrderEntry.getFloatValue("price"));
		entry.put("unit", purOrderEntry.getString("unit"));
		entry.put("unit_DspName", purOrderEntry.getString("unit_DspName"));
		entry.put("unit_NmbName", purOrderEntry.getString("unit_NmbName"));
		entry.put("qty", purOrderEntry.getBigDecimal("qty"));
		entry.put("dyProDate", "");
		entry.put("dyManufacturer", "");
		entry.put("registrationNo", "");
		entry.put("amount", purOrderEntry.getBigDecimal("localAmount")); // ? 本位币金额 No Amount
		entry.put("effectiveDate", "");

		return entry;
	}

	/**
	 * 根据一条采购订单分录，生成多条发货单分录数据
	 * 
	 * @Title generateEntryItem
	 * @param purOrderEntry
	 *            采购订单的一条分录
	 * @return Map<String,Object>
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

		BigDecimal bQty = purOrderEntry.getBigDecimal("qty"); // 采购订单分录数量
		BigDecimal amount = purOrderEntry.getBigDecimal("localAmount");
		float price = purOrderEntry.getFloatValue("price");

		int qty = bQty.intValue();

		// 数量尾差，尾差放到最后一行
		float lastLineQty = bQty.floatValue() > qty ? bQty.floatValue() - qty + 1 : 1;
		// 金额尾差，尾差放到最后一行
		BigDecimal lastLineAmount = amount.compareTo(new BigDecimal(price * qty)) > 0 ? amount.subtract(new BigDecimal(price * qty)) : new BigDecimal(price);
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < qty; i++) {

			Map<String, Object> entry = new HashMap<String, Object>();

			entry.put("seq", 0);
			entry.put("orderId", purOrderEntry.getString("parent"));
			entry.put("orderNumber", purOrder.getString("number"));
			entry.put("orderSeq", purOrderEntry.getIntValue("seq"));
			entry.put("material", purOrderEntry.getString("material"));
			entry.put("material_DspName", purOrderEntry.getString("material_DspName"));
			entry.put("material_NmbName", purOrderEntry.getString("material_NmbName"));
			entry.put("specification", purOrderEntry.getString("specification"));
			entry.put("lot", "");
			entry.put("dyBatchNum", "");
			entry.put("code", "");
			entry.put("price", purOrderEntry.getFloatValue("price"));
			entry.put("unit", purOrderEntry.getString("unit"));
			entry.put("unit_DspName", purOrderEntry.getString("unit_DspName"));
			entry.put("unit_NmbName", purOrderEntry.getString("unit_NmbName"));

			entry.put("dyProDate", "");
			entry.put("dyManufacturer", "");
			entry.put("registrationNo", "");
			entry.put("effectiveDate", "");

			entry.put("qty", 1); // 拆单后，发货单明细行数量为1
			entry.put("amount", purOrderEntry.getFloatValue("price")); // ? 本位币金额 No Amount

			if (i == qty - 1) {
				// lastLine
				entry.put("qty", lastLineQty); // 拆单后，发货单明细行数量为1
				entry.put("amount", lastLineAmount); // ? 本位币金额 No Amount
			}

			ret.add(entry);
		}

		return ret;
	}

	/**
	 * 判断该分录对应的源单内码，源单分录是否已处理过数据
	 * 
	 * -是，返回该分录，数量累加上本条采购订单分录数据(代销物料除外)
	 * 
	 * -否，返回null
	 * 
	 * @Title isInShipOrderEntries
	 * @param shipOrder
	 *            构建中的发货单
	 * @param purOrderId
	 *            采购订单
	 * @param purOrderEntrySeq
	 *            采购订单内码
	 * @return Map<String,Object> 采购订单分录号
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

		for (int i=0;i<entryList.size();i++)  {

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
	 * @Title generateHead
	 * @param purOrder
	 *            一张采购订单
	 * @return Map<String,Object>
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
		shipOrderHead.put("number", Common.createShipOrderNo());

		shipOrderHead.put("baseType", "采购订单");
		shipOrderHead.put("Date", sdf.format(new Date()));
		shipOrderHead.put("supplier", purOrder.get("supplier")); // 采购订单"供应商"携带到发货单"供应商"字段
		shipOrderHead.put("supplier_DspName", purOrder.get("supplier_DspName")); // 采购订单"供应商名称"携带到发货单"供应商名称"字段
		shipOrderHead.put("supplier_NmbName", purOrder.get("supplier_NmbName")); // 采购订单"供应商代码"携带到发货单"供应商代码"字段
		shipOrderHead.put("baseStatus", 0);

		Map<String, Object> itemById = templateService.getItemById(1025, "0", "QpXq24FxxE6c3lvHMPyYCxACEAI=");
		shipOrderHead.put("baseStatus_NmbName", itemById.get("number") == null ? "" : itemById.get("number"));// 补充状态代码/名称
		shipOrderHead.put("baseStatus_DspName", itemById.get("name") == null ? "" : itemById.get("name"));

		return shipOrderHead;

	}

}
