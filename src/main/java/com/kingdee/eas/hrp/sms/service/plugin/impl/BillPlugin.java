package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.kingdee.eas.hrp.sms.dao.customize.InvoiceDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ItemMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.model.OrderEntryExample;
import com.kingdee.eas.hrp.sms.model.OrderEntryExample.Criteria;
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

	@SuppressWarnings("unchecked")
	@Override
	public PlugInRet afterSave(int classId, String id, JSONObject data) {
		if (classId == 2020) {
			ITemplateService temp = Environ.getBean(ITemplateService.class);
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> item = temp.getItemById(classId, id);
			Map<String, Object> entrys = (Map<String, Object>) item.get("entry");
			ArrayList<Object> arrayList = (ArrayList<Object>) entrys.get("1");
			for (int i = 0; i < arrayList.size(); i++) {
				Map<String, Object> entry = new HashMap<String, Object>();
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
						Map<String, Object> entry1 = new HashMap<String, Object>();
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
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data) {

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
			if (array == null || array.equals("")) {
				throw new BusinessLogicRunTimeException("发货单列表中缺少需要发货的商品");
			}
			System.out.println(array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject entrys = array.getJSONObject(i);
				JSONObject datas = entrys.getJSONObject("data");
				if (datas == null || datas.equals("")) {
					throw new BusinessLogicRunTimeException("发货单列表中缺少需要发货的商品");
				}
				String actualQty = datas.getString("actualQty");// 实发数量
				if (actualQty.matches("^[0-9]*$")) {
					System.out.println(actualQty.matches("^[0-9]*$"));
				} else {
					throw new BusinessLogicRunTimeException("发货数量格式不正确");
				}
				BigDecimal qty = datas.getBigDecimal("qty");// 应发数量
				String lot = datas.getString("lot");// 批次
				String dyBatchNum = datas.getString("dyBatchNum");// 批号
				Date effectiveDate = datas.getDate("effectiveDate");// 有效期
				SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
				ItemMapper itemMapper = (ItemMapper) sqlSession.getMapper(ItemMapper.class);
				Item items = itemMapper.selectByPrimaryKey(datas.getString("material"));

				String code = datas.getString("code");
				if (actualQty.equals("") || actualQty == null) {
					throw new BusinessLogicRunTimeException("实发数量不能为空");
				}
				if (new BigDecimal(actualQty).compareTo(qty) > 0) {
					throw new BusinessLogicRunTimeException("发货数量不能大于应发数量");
				}
				if (items.getIsLotNumber() != null) {
					if (items.getIsLotNumber().equals("1") || items.getIsLotNumber() == 1) {
						if (lot.equals("") || lot == null) {
							throw new BusinessLogicRunTimeException("批次不能为空");
						}
						if (dyBatchNum.equals("") || dyBatchNum == null) {
							throw new BusinessLogicRunTimeException("批号不能为空");
						}
					}
				}
				if (items.getHighConsumable() != null) {
					if (items.getHighConsumable().equals("1") || items.getHighConsumable() == 1) {
						if (code.equals("") || code == null) {
							throw new BusinessLogicRunTimeException("高值物料个体码不能为空");
						}
					}
				}
				if (effectiveDate.equals("") || effectiveDate == null) {
					throw new BusinessLogicRunTimeException("有效期不能为空");
				}
			}
		}
		PlugInRet ret = new PlugInRet();
		ret.setCode(200);
		ret.setData(data);
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlugInRet beforeModify(int classId, String id, Map<String, Object> formData, JSONObject data) {
		if (classId == 2020) {
			JSONObject entry = data.getJSONObject("entry");
			JSONArray array = entry.getJSONArray("1");
			boolean fl = false; // 表示有没有可用分录
			for (int i = 0; i < array.size(); i++) {
				JSONObject entrys = array.getJSONObject(i);
				if (entrys.get("flag").equals("1") || entrys.get("flag").equals("2")) {
					fl = true;
					break;
				}
			}
			if (!fl) {
				throw new BusinessLogicRunTimeException("没有可用分录");
			}
			ITemplateService temp = Environ.getBean(ITemplateService.class);
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> item = temp.getItemById(classId, id);
			// 判断发货单是否已发送到医院
			if (item.get("type") != null) {
				if (item.get("type").equals("1") || Integer.parseInt(item.get("type").toString()) == 1) {
					throw new BusinessLogicRunTimeException("已发送到医院的发货单不能删除");
				}
			}
			Map<String, Object> entrys = (Map<String, Object>) item.get("entry");
			ArrayList<Object> arrayList = (ArrayList<Object>) entrys.get("1");
			// 遍历发货单子表数据，取出采购订单号，采购订单行号和应发数量
			for (int i = 0; i < arrayList.size(); i++) {
				HashMap<String, Object> invoiceEntry = (HashMap<String, Object>) arrayList.get(i);
				Map<String, Object> entry1 = new HashMap<String, Object>();
				entry1.put("orderId", invoiceEntry.get("orderId"));
				entry1.put("orderSeq", invoiceEntry.get("orderSeq"));
				entry1.put("actualQty", invoiceEntry.get("actualQty"));
				list.add(entry1);
			}
			OrderEntry orderEntry = new OrderEntry();
			SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
			OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
			// 根据采购订单号和采购订单行号修改发货数量
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> lists = list.get(i);
				int seq = Integer.parseInt(lists.get("orderSeq").toString());
				String parent = lists.get("orderId").toString();
				BigDecimal actualQty = new BigDecimal(lists.get("actualQty").toString());
				OrderEntryExample e = new OrderEntryExample();
				Criteria c = e.createCriteria();
				c.andSeqEqualTo(seq);
				c.andParentEqualTo(parent);
				// 根据订单号和行号查询对应的记录
				List<OrderEntry> o = orderEntryMapper.selectByExample(e);
				if (o.size() > 0) {
					orderEntry.setInvoiceQty(o.get(0).getInvoiceQty().subtract(actualQty));
					orderEntry.setId(o.get(0).getId());
					// 根据订单ID 修改发货数量
					orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
				}
			}
		}
		PlugInRet ret = new PlugInRet();
		ret.setCode(200);
		ret.setData(data);
		return ret;
	}

	@Override
	public PlugInRet afterModify(int classId, JSONObject data) {
		if (classId == 2020) {
			JSONObject entry = (JSONObject) data.get("entry");
			JSONArray array = JSONArray.parseArray(entry.getString("1"));
			OrderEntry orderEntry = new OrderEntry();
			SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
			OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
			for (int i = 0; i < array.size(); i++) {
				JSONObject entry1 = array.getJSONObject(i);
				JSONObject datas = entry1.getJSONObject("data");
				int seq = Integer.parseInt(datas.get("orderSeq").toString());
				String parent = datas.get("orderId").toString();
				BigDecimal actualQty = new BigDecimal(datas.get("actualQty").toString());
				OrderEntryExample e = new OrderEntryExample();
				Criteria c = e.createCriteria();
				c.andSeqEqualTo(seq);
				c.andParentEqualTo(parent);
				// 根据订单号和行号查询对应的记录
				List<OrderEntry> o = orderEntryMapper.selectByExample(e);
				if (o.size() > 0) {
					if (o.get(0).getInvoiceQty().add(actualQty).compareTo(o.get(0).getConfirmQty()) == 1) {
						throw new BusinessLogicRunTimeException("发货总数不能大于接单数量");
					}
					orderEntry.setInvoiceQty(o.get(0).getInvoiceQty().add(actualQty));
					orderEntry.setId(o.get(0).getId());
					// 根据订单ID 修改发货数量
					orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
				}
			}
		}
		return super.afterModify(classId, data);
	}

	@Override
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String data) {
		if (classId == 2020) {
			String[] split = data.split("\\,");
			for (int i = 0; i < split.length; i++) {
				ITemplateService temp = Environ.getBean(ITemplateService.class);
				Map<String, Object> item = temp.getItemById(classId, split[i]);
				if (item.get("type") != null) {
					if (item.get("type").equals("1") || Integer.parseInt(item.get("type").toString()) == 1) {
						throw new BusinessLogicRunTimeException("已发送到医院的发货单不能删除");
					}
				}
			}
		}
		PlugInRet ret = new PlugInRet();
		ret.setCode(200);
		return ret;
	}

	@Override
	public PlugInRet afterDelete(int classId, List<Map<String, Object>> data, String items) {
		if (classId == 2020) {
			SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
			OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
			OrderEntry orderEntry = new OrderEntry();
			for (int i = 0; i < data.size(); i++) {
				List<Map<String, Object>> entry = (List<Map<String, Object>>) data.get(i);
				String parent = entry.get(0).get("orderId").toString();
				int seq = Integer.parseInt(entry.get(0).get("orderSeq").toString());
				BigDecimal actualQty = new BigDecimal(entry.get(0).get("actualQty").toString());
				OrderEntryExample e = new OrderEntryExample();
				Criteria c = e.createCriteria();
				c.andSeqEqualTo(seq);
				c.andParentEqualTo(parent);
				List<OrderEntry> o = orderEntryMapper.selectByExample(e);
				for (int j = 0; j < o.size(); j++) {
					orderEntry.setParent(parent);
					orderEntry.setSeq(seq);
					orderEntry.setInvoiceQty(o.get(0).getInvoiceQty().subtract(actualQty));
					orderEntry.setId(o.get(0).getId());
					orderEntryMapper.updateByPrimaryKeySelective(orderEntry);
				}
			}
		}
		return super.afterDelete(classId, data, items);
	}

}
