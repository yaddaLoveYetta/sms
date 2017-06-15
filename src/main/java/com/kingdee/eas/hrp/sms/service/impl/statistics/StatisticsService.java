package com.kingdee.eas.hrp.sms.service.impl.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.log.ServiceLog;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.statistics.IStatisticsService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class StatisticsService extends BaseService implements IStatisticsService {

	@Resource
	ITemplateService templateService;

	@SuppressWarnings("unchecked")
	@ServiceLog(desc = "获取订单统计记录")
	@Override
	public Map<String, Object> getRecord(String itemId, String supplier, String orderStartDate, String orderEndDate) {

		Map<String, Object> result = new HashMap<String, Object>();

		// 筛选供应商和对应物料的订单数据
		JSONArray conditionArry = new JSONArray();
		JSONObject condition = new JSONObject(true);
		if (!(null == supplier || "".equals(supplier))) {
			condition.put("fieldKey", "supplier");
			condition.put("logicOperator", "=");
			condition.put("value", supplier);
			condition.put("needConvert", false);
			conditionArry.add(condition);
		}
		if (!(null == itemId || "".equals(itemId))) {
			condition = new JSONObject(true);
			condition.put("fieldKey", "material");
			condition.put("logicOperator", "=");
			condition.put("value", itemId);
			condition.put("needConvert", false);
			conditionArry.add(condition);
		}
		if (!(null == orderStartDate || "".equals(orderStartDate))) {
			condition = new JSONObject(true);
			condition.put("fieldKey", "tickDate");
			condition.put("logicOperator", ">=");
			condition.put("value", orderStartDate);
			condition.put("needConvert", false);
			conditionArry.add(condition);
		}
		if (!(null == orderEndDate || "".equals(orderEndDate))) {
			condition = new JSONObject(true);
			condition.put("fieldKey", "tickDate");
			condition.put("logicOperator", "<=");
			condition.put("value", orderEndDate);
			condition.put("needConvert", false);
			conditionArry.add(condition);
		}
		Map<String, Object> orderData = templateService.getItems(2019, conditionArry.toString(), "", 1, 10000);
		// 如果订单数量为0，则不再往下统计
		if (((long) orderData.get("count")) > 0) {
			List<Map<String, Object>> orderList = (List<Map<String, Object>>) orderData.get("list");
			// 存放每个物料的统计订单数量
			Map<String, Object> orderCount = new HashMap<String, Object>();
			// 存放每个物料的统计发货数量
			Map<String, Object> orderInvoiceQty = new HashMap<String, Object>();
			// 存放每个物料的基本计量单位
			Map<String, Object> orderMaterialUnit = new HashMap<String, Object>();
			// 存放订单
			StringBuilder materialId = new StringBuilder("");
			StringBuilder orderId = new StringBuilder("");
			List<String> orderIdList = new ArrayList<String>();
			for (Map<String, Object> order : orderList) {
				orderIdList.add((String) order.get("id"));
				orderId.append("'").append((String) order.get("id")).append("',");
				Map<String, Object> entryData = (Map<String, Object>) order.get("entry");
				List<Map<String, Object>> entryData1 = (List<Map<String, Object>>) entryData.get("1");
				for (int i = 0; i < entryData1.size(); i++) {
					Map<String, Object> entry = (Map<String, Object>) entryData1.get(i);
					String material = (String) entry.get("material");
					materialId.append("'").append(material).append("',");
					if (orderCount.containsKey(material)) {
						int countOrder = (int) orderCount.get(material) + 1;
						orderCount.replace(material, countOrder);
						double invoiceQty = (double) orderInvoiceQty.get(material)
								+ Double.parseDouble(entry.get("invoiceQty").toString());
						orderInvoiceQty.replace(material, invoiceQty);
					} else {
						orderCount.put(material, 1);
						orderInvoiceQty.put(material, Double.parseDouble(entry.get("invoiceQty").toString()));
						orderMaterialUnit.put(material, entry.get("unit"));
					}
				}
			}
			orderId.deleteCharAt(orderId.length()-1);
			materialId.deleteCharAt(materialId.length()-1);

			// 筛选订单中的退货单和入库单
			condition = new JSONObject(true);
			condition.put("fieldKey", "orderId");
			condition.put("logicOperator", "in");
			condition.put("value", orderId);
			condition.put("needConvert", false);
			conditionArry.add(condition);
			// 如果前端没有传入物料筛选条件，就根据筛选出的订单中的物料进行退货单和入库单的筛选
			if (null == itemId || "".equals(itemId)) {
				condition = new JSONObject(true);
				condition.put("fieldKey", "material");
				condition.put("logicOperator", "in");
				condition.put("value", materialId);
				condition.put("needConvert", false);
				conditionArry.add(condition);
			}

			Map<String, Object> purreturnsData = templateService.getItems(2023, conditionArry.toString(), "", 1, 10000);
			Map<String, Object> purreturnsReturnQty = new HashMap<String, Object>();
			// 如果退货单数量为0，则不再往下统计
			if (((long) purreturnsData.get("count")) > 0) {
				List<Map<String, Object>> purreturnsList = (List<Map<String, Object>>) purreturnsData.get("list");
				// 存放每个物料的统计退货数量
				for (Map<String, Object> purreturns : purreturnsList) {
					Map<String, Object> entryData = (Map<String, Object>) purreturns.get("entry");
					List<Map<String, Object>> entryData1 = (List<Map<String, Object>>) entryData.get("1");
					for (int i = 0; i < entryData1.size(); i++) {
						Map<String, Object> entry = (Map<String, Object>) entryData1.get(i);
						String material = (String) entry.get("material");
						if (orderCount.containsKey(material)) {
							double returnQty = (double) purreturnsReturnQty.get(material)
									+ Double.parseDouble(entry.get("returnQty").toString());
							purreturnsReturnQty.replace(material, returnQty);
						} else {
							purreturnsReturnQty.put(material, Double.parseDouble(entry.get("returnQty").toString()));
						}
					}
				}
			}

			Map<String, Object> purinwarehsData = templateService.getItems(2022, conditionArry.toString(), "", 1,
					10000);
			Map<String, Object> purinwarehsActualQty = new HashMap<String, Object>();
			// 如果入库单数量为0，则不再往下统计
			if (((long) purinwarehsData.get("count")) > 0) {
				List<Map<String, Object>> purinwarehsList = (List<Map<String, Object>>) purinwarehsData.get("list");
				// 存放每个物料的统计入库数量
				for (Map<String, Object> purinwarehs : purinwarehsList) {
					Map<String, Object> entryData = (Map<String, Object>) purinwarehs.get("entry");
					List<Map<String, Object>> entryData1 = (List<Map<String, Object>>) entryData.get("1");
					for (int i = 0; i < entryData1.size(); i++) {
						Map<String, Object> entry = (Map<String, Object>) entryData1.get(i);
						String material = (String) entry.get("material");
						if (orderCount.containsKey(material)) {
							double actualQty = (double) purinwarehsActualQty.get(material)
									+ Double.parseDouble(entry.get("actualQty").toString());
							purinwarehsActualQty.replace(material, actualQty);
						} else {
							purinwarehsActualQty.put(material, Double.parseDouble(entry.get("returnQty").toString()));
						}
					}
				}
			}

			Iterator<Map.Entry<String, Object>> entries = orderCount.entrySet().iterator();
			while (entries.hasNext()) {
				Map<String, Object> record = new HashMap<String, Object>();
				Entry<String, Object> entry = entries.next();
				String material = entry.getKey();
				Map<String, Object> itemById = templateService.getItemById(1013, material);
				record.put("itemId", material);
				record.put("itemNumber", itemById.get("number"));
				record.put("itemName", itemById.get("name"));
				record.put("specification", itemById.get("specification"));
				record.put("unit", orderMaterialUnit.get(material));
				record.put("orderAmount", orderCount.get(material));
				// 发货数量
				record.put("invoiceQty", orderInvoiceQty.get(material));
				// 退货数量
				record.put("returnQty", purreturnsReturnQty.get(material));
				// 入库数量
				record.put("actualQty", purinwarehsActualQty.get(material));
				result.put(material, record);
			}

			return result;
		} else {
			return null;
		}
	}
}
