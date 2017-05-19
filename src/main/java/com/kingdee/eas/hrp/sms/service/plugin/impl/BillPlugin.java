package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInAdpter;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;
import com.kingdee.eas.hrp.sms.util.Environ;

/**
 * 单据插件
 * 
 * @ClassName BillPlugin
 * @author yadda
 * @date 2017-05-18 17:49:52 星期四
 */
public class BillPlugin extends PlugInAdpter {

	@SuppressWarnings("unused")
	@Override
	public PlugInRet afterSave(int classId, String id, JSONObject data) {
		ITemplateService temp = Environ.getBean(ITemplateService.class);
		ArrayList<Map<String, Object>> list = new ArrayList();
		Map<String, Object> item = temp.getItemById(classId, id, "QpXq24FxxE6c3lvHMPyYCxACEAI=");
		Map<String, Object> entrys = (Map<String, Object>) item.get("entry");
		ArrayList<Object> arrayList = (ArrayList<Object>) entrys.get("1");
		for (int i = 0; i < arrayList.size(); i++) {
			Map<String, Object> entry = new HashMap();
			HashMap<String, Object> invoiceEntry = (HashMap<String, Object>) arrayList.get(i);
			if (i == 0) {
				entry.put("seq", invoiceEntry.get("orderSeq"));
				entry.put("parent", invoiceEntry.get("orderId"));
				entry.put("invoiceQty", invoiceEntry.get("qty"));
				list.add(entry);
			} else {
				// 判斷
				BigDecimal qty = (BigDecimal) invoiceEntry.get("qty");

				String orderId = (String) invoiceEntry.get("orderId");
				int seq = Integer.parseInt(invoiceEntry.get("orderSeq").toString());

				entry = isInList(list, orderId, seq);
				
				if (null != entry) {
					// 在不在list
					entry.put("invoiceQty", qty.add((BigDecimal) entry.get("invoiceQty")));
				} 
				if(entry==null){
					Map<String, Object> entry1 = new HashMap();
					entry1.put("seq", invoiceEntry.get("orderSeq"));
					entry1.put("parent", invoiceEntry.get("orderId"));
					entry1.put("invoiceQty", invoiceEntry.get("qty"));
					list.add(entry1);
				}

			}
		}
		String da = "";
		JSONObject json = new JSONObject();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			json.put("seq",map.get("seq"));
			json.put("parent",map.get("parent"));
			json.put("invoiceQty", map.get("invoiceQty"));
		}
		temp.editItem(2019, id, json.toString(), "QpXq24FxxE6c3lvHMPyYCxACEAI=");
		
		return super.afterSave(classId, id, data);
	}

	private Map<String, Object> isInList(List<Map<String, Object>> list, String currentOrderId, int currentSeq) {

		for (Map<String, Object> item : list) {

			String orderId = (String) item.get("parent");
			int seq = Integer.parseInt( item.get("seq").toString());

			if (orderId.equals(currentOrderId) && seq == currentSeq) {
				return item;
			}
		}
		return null;
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		if (classId == 2020) {
			// 检验单据数据包

		}

		return super.beforeSave(classId, formData, data, userTyepe);
	}

}
