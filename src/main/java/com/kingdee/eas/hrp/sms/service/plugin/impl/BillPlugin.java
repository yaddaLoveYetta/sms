package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.model.OrderEntryExample;
import com.kingdee.eas.hrp.sms.model.OrderEntryExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
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
				entry.put("invoiceQty", invoiceEntry.get("actualQty"));
				list.add(entry);
			} else {
				// 判斷
				BigDecimal qty = (BigDecimal) invoiceEntry.get("actualQty");

				String orderId = (String) invoiceEntry.get("orderId");
				int seq = Integer.parseInt(invoiceEntry.get("orderSeq").toString());

				entry = isInList(list, orderId, seq);

				if (null != entry) {
					// 在不在list
					entry.put("invoiceQty", qty.add((BigDecimal) entry.get("invoiceQty")));
				}
				if (entry == null) {
					Map<String, Object> entry1 = new HashMap();
					entry1.put("seq", invoiceEntry.get("orderSeq"));
					entry1.put("parent", invoiceEntry.get("orderId"));
					entry1.put("invoiceQty", invoiceEntry.get("actualQty"));
					list.add(entry1);
				}

			}
		}
		OrderEntry orderEntry = new OrderEntry();
		SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
		OrderEntryMapper orderEntryMapper = (OrderEntryMapper) sqlSession.getMapper(OrderEntryMapper.class);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> lists = list.get(i);
			OrderEntryExample e = new OrderEntryExample();
			Criteria c = e.createCriteria();
			c.andSeqEqualTo(Integer.parseInt(lists.get("seq").toString()));
			c.andParentEqualTo(lists.get("parent").toString());
			// 根据订单号和行号查询对应的记录
			List<OrderEntry> o = orderEntryMapper.selectByExample(e);
			if (o.size() > 0) {
				orderEntry.setInvoiceQty(
						new BigDecimal(lists.get("invoiceQty").toString()).add(o.get(0).getInvoiceQty()));
				orderEntry.setId(o.get(0).getId());
				// 根据订单ID 修改发货数量
				orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
			}
		}
		return super.afterSave(classId, id, data);
	}

	private Map<String, Object> isInList(List<Map<String, Object>> list, String currentOrderId, int currentSeq) {

		for (Map<String, Object> item : list) {

			String orderId = (String) item.get("parent");
			int seq = Integer.parseInt(item.get("seq").toString());

			if (orderId.equals(currentOrderId) && seq == currentSeq) {
				return item;
			}
		}
		return null;
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		if (classId == 2020) {
			String logistics = data.getString("logistics");
			String logisticsNo = data.getString("logisticsNo");
			if (!logistics.equals("") && logistics != null) {
				if (logistics.matches("^[0-9]*$")) {
					throw new BusinessLogicRunTimeException("物流公司名称格式错误");
				}
			}
			if (!logisticsNo.equals("") && logisticsNo != null) {
				if (logisticsNo.matches("[\u4e00-\u9fa5]")) {
					throw new BusinessLogicRunTimeException("物流单号格式错误");
				}
			}
			JSONObject entry = data.getJSONObject("entry");
			JSONArray array = entry.getJSONArray("1");
			for (Iterator<Object> it = array.iterator(); it.hasNext();) {
				Object obj = it.next();
				JSONObject entryItem = (JSONObject) JSON.toJSON(obj);
				BigDecimal actualQty = entryItem.getBigDecimal("actualQty");// 实发数量
				BigDecimal qty = entryItem.getBigDecimal("qty");// 应发数量
				String lot = entryItem.getString("lot");// 批次
				String dyBatchNum = entryItem.getString("dyBatchNum");// 批号
				Date dyProDate = entryItem.getDate("dyProDate");// 生产日期
				String dyManufacturer = entryItem.getString("dyManufacturer");// 生产厂家
				String registrationNo = entryItem.getString("registrationNo");// 产品注册号
				Date effectiveDate = entryItem.getDate("effectiveDate");// 有效期
				if (actualQty.equals("") || actualQty == null) {
					throw new BusinessLogicRunTimeException("实发数量不能为空");
				}
				if (actualQty.compareTo(qty) > 0) {
					throw new BusinessLogicRunTimeException("发货数量不能大于应发数量");
				}
				if (lot.equals("") || lot == null) {
					throw new BusinessLogicRunTimeException("批次不能为空");
				}
				if (dyBatchNum.equals("") || dyBatchNum == null) {
					throw new BusinessLogicRunTimeException("批号不能为空");
				}
				if (effectiveDate.equals("") || effectiveDate == null) {
					throw new BusinessLogicRunTimeException("有效期不能为空");
				}
			}
		}

		return super.beforeSave(classId, formData, data, userTyepe);
	}

}
