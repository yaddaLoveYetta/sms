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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.customize.PurchaseOrderEntryDaoMapper;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.statistics.IStatisticsService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class StatisticsService extends BaseService implements IStatisticsService {

	@Resource
	ITemplateService templateService;

	@Override
	public JSONObject getRecord(String itemId, String supplier, String orderStartDate, String orderEndDate, int pageNo,
			int pageSize) {

		JSONObject ret = new JSONObject(true);
		JSONArray list = new JSONArray();

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("orderEndDate", orderEndDate);
		sqlMap.put("orderStartDate", orderStartDate);
		sqlMap.put("supplier", supplier);
		sqlMap.put("material", itemId);
		if (pageNo == 1) {
			PageHelper.startPage(pageNo, pageSize, true);
		} else {
			PageHelper.startPage(pageNo, pageSize, false);
		}
		PurchaseOrderEntryDaoMapper mapper = sqlSession.getMapper(PurchaseOrderEntryDaoMapper.class);
		List<Map<String, Object>> selectOrderGroupById = mapper.selectOrderGroupById(sqlMap);
		if (pageNo == 1) {

			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(selectOrderGroupById);

			ret.put("count", pageInfo.getTotal());
		}
		// 如果订单数量为0，则不再往下统计
		if (selectOrderGroupById == null) {
			return null;
		} else {
			List<Map<String, Object>> selectOrderStatistics = mapper.selectOrderStatistics(sqlMap);
			// 存放每个物料的统计订单数量
			Map<String, Object> orderCount = new HashMap<String, Object>();
			// 存放物料对应的订单id
			List<String> materialOrder = new ArrayList<String>();
			// 存放物料对应的子表id
			List<String> materialEntry = new ArrayList<String>();
			// 存放每个物料的统计发货数量
			Map<String, Object> orderInvoiceQty = new HashMap<String, Object>();
			// 存放每个物料的基本计量单位
			Map<String, Object> orderMaterialUnit = new HashMap<String, Object>();
			// 存放入库
			Map<String, Object> purinwarehsActualQty = new HashMap<String, Object>();
			// 存放退货
			Map<String, Object> purreturnsReturnQty = new HashMap<String, Object>();

			for (Map<String, Object> order : selectOrderStatistics) {
				String orderId = (String) order.get("parent");
				String entryId = (String) order.get("id");
				String material = (String) order.get("material");
				String materialOrderStr = material + orderId;
				String materialEntryStr = material + entryId;

				if (orderCount.containsKey(material)) {

					if (!materialOrder.contains(materialOrderStr)) {
						// 累加物料的订单数量
//						int countOrder = (int) orderCount.get(material) + 1;
//						orderCount.replace(material, countOrder);
						double qty = (double) orderCount.get(material)
								+ Double.parseDouble(order.get("qty").toString());
						orderCount.replace(material, qty);
						materialOrder.add(materialOrderStr);
					}

					// 订单子表未统计,统计发货量
					if (!materialEntry.contains(materialEntryStr)) {
						double invoiceQty = (double) orderInvoiceQty.get(material)
								+ Double.parseDouble(order.get("invoiceQty").toString());
						orderInvoiceQty.replace(material, invoiceQty);
						materialEntry.add(materialEntryStr);
					}

					if (!(null == order.get("returnQty") || "".equals(order.get("returnQty")))) {
						double returnQty = (double) purreturnsReturnQty.get(material)
								+ Double.parseDouble(order.get("returnQty").toString());
						purreturnsReturnQty.replace(material, returnQty);
					}
					if (!(null == order.get("actualQty") || "".equals(order.get("actualQty")))) {
						double actualQty = (double) purinwarehsActualQty.get(material)
								+ Double.parseDouble(order.get("actualQty").toString());
						purinwarehsActualQty.replace(material, actualQty);
					}

				} else {

					//orderCount.put(material, 1);
					orderCount.put(material, Double.parseDouble(order.get("qty").toString()));
					materialOrder.add(materialOrderStr);

					orderInvoiceQty.put(material, Double.parseDouble(order.get("invoiceQty").toString()));
					materialEntry.add(materialEntryStr);

					if (!(null == order.get("returnQty") || "".equals(order.get("returnQty")))) {
						purreturnsReturnQty.put(material, Double.parseDouble(order.get("returnQty").toString()));
					}else{
						purreturnsReturnQty.put(material, Double.parseDouble("0"));
					}
					if (!(null == order.get("actualQty") || "".equals(order.get("actualQty")))) {
						purinwarehsActualQty.put(material, Double.parseDouble(order.get("actualQty").toString()));
					}else{
						purinwarehsActualQty.put(material, Double.parseDouble("0"));
					}
					orderMaterialUnit.put(material, order.get("unit"));
				}
			}

			Iterator<Map.Entry<String, Object>> entries = orderCount.entrySet().iterator();
			while (entries.hasNext()) {
				JSONObject json = new JSONObject(true);
				Entry<String, Object> entry = entries.next();
				String material = entry.getKey();
				String unit = (String) orderMaterialUnit.get(material);
				Map<String, Object> unitById = templateService.getItemById(1018, unit);
				Map<String, Object> itemById = templateService.getItemById(1013, material);
				json.put("materialId", material);
				json.put("materialNumber", itemById.get("number"));
				json.put("materialName", itemById.get("name"));
				json.put("model", itemById.get("specification"));
				json.put("unit", unitById.get("name"));
				json.put("orderQty", orderCount.get(material));
				// 发货数量
				json.put("outStockQty", orderInvoiceQty.get(material));
				// 退货数量
				if (null != purreturnsReturnQty.get(material)) {
					json.put("returnQty", -1 * (double) purreturnsReturnQty.get(material));
				} else {
					json.put("returnQty", 0);
				}
				// 入库数量
				if (null != purinwarehsActualQty.get(material)) {
					json.put("stockQty", purinwarehsActualQty.get(material));
				} else {
					json.put("stockQty", 0);
				}
				list.add(json);
			}
			ret.put("list", list);

			return ret;
		}
	}
}
