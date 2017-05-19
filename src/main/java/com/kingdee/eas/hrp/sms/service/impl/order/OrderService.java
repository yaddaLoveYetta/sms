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
	@Override
	public Map<String, Object> invoice(String data, String userType) {

		String[] idString = data.split(",");
		// 表头map
		Map<String, Object> order = new HashMap();
		// 表体EntryMap
		Map<String, Object> orderEntry = new HashMap();
		// 子表1
		ArrayList<Object> list = new ArrayList();
		// 子表1关联数据
		Map<String, Object> entry = new HashMap();
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
						order.put("number", Common.createInvoiceNo());
						order.put("Date", "");
						order.put("logistics", "");
						order.put("baseType", "采购订单");
						order.put("logisticsNo", "");
						order.put("supplier_DspName", map.get("supplier_DspName"));
						order.put("supplier", map.get("supplier"));
						order.put("baseStatus", 0);
						order.put("baseStatus_NmbName", map.get("baseStatus_NmbName"));
						order.put("baseStatus_DspName", map.get("baseStatus_DspName"));
						order.put("baseStatus", 0); // 发货单状态为新增 内码0
						ITemplateService templateService =Environ.getBean(ITemplateService.class);
						Map<String, Object> itemById = templateService.getItemById(1025, "0", "QpXq24FxxE6c3lvHMPyYCxACEAI=");
						order.put("baseStatus_NmbName", itemById.get("name"));
						order.put("baseStatus_DspName", itemById.get("number"));
						

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
					order.put("number", Common.createInvoiceNo());
					order.put("Date", "");
					order.put("logistics", "");
					order.put("baseType", "采购订单");
					order.put("logisticsNo", "");
					order.put("supplier_DspName", map.get("supplier_DspName"));
					order.put("supplier", map.get("supplier"));
					order.put("baseStatus", 0);
					order.put("baseStatus_NmbName", map.get("baseStatus_NmbName"));
					order.put("baseStatus_DspName", map.get("baseStatus_DspName"));
					order.put("baseStatus", 0); // 发货单状态为新增 内码0
					ITemplateService templateService =Environ.getBean(ITemplateService.class);
					Map<String, Object> itemById = templateService.getItemById(1025, "0", "QpXq24FxxE6c3lvHMPyYCxACEAI=");
					order.put("baseStatus_NmbName", itemById.get("name"));
					order.put("baseStatus_DspName", itemById.get("number"));
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

}
