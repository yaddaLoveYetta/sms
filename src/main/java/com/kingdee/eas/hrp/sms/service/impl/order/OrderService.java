package com.kingdee.eas.hrp.sms.service.impl.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Common;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class OrderService extends BaseService implements IOrderService{
	
	/**
	 * 同步订单
	 */
	@Override
	public String order(JSONArray orderjson){
		Order order = new Order();
		OrderEntry orderEntry = new OrderEntry();
			for(int i=0;i<orderjson.size();i++){
				//录入订单抬头
				JSONObject ob = orderjson.getJSONObject(i);
            	order.setId(ob.getString("id"));
				order.setSupplier(ob.getString("supplier"));
				order.setPurchasePerson(ob.getString("purchasePerson"));
				order.setSaleProxy(ob.getString("saleProxy"));
				order.setIsInTax(ob.getByte("isInTax"));
				order.setNumber(ob.getString("number"));
				order.setIsQuicken(ob.getByte("isQuicken"));
				order.setCurrency(ob.getString("currency"));
				order.setSettlementType(ob.getString("settlementType"));
				order.setTotalAmount(ob.getBigDecimal("totalAmount"));
				order.setTotalTax(ob.getBigDecimal("totalTax"));
				order.setTotalTaxAmount(ob.getBigDecimal("totalTaxAmount"));
				order.setBaseStatus(ob.getByte("baseStutas"));
				if(ob.getDate("bizDate")!=null){
				order.setBizDate(ob.getDate("bizDate"));
				}
				if(ob.getString("createTime")!=null){
				order.setCreateTime(ob.getDate("createTime"));
				}
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			 JSONArray orderEntryArray = JSONArray.parseArray(ob.getString("entries"));
			 for(int j=0;j<orderEntryArray.size();j++){
				//录入订单物料信息
				JSONObject orderEntryObject = orderEntryArray.getJSONObject(j);
				orderEntry.setMaterial(orderEntryObject.getString("material"));
				orderEntry.setUnit(orderEntryObject.getString("unit"));
				orderEntry.setParent(orderEntryObject.getString("parent"));
				orderEntry.setPrice(orderEntryObject.getBigDecimal("price"));
				orderEntry.setQty(orderEntryObject.getInteger("qty"));
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
			if(orderEntryObject.getString("deliveryDate")!=null){
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
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NEVER)
	public String updatetickType(JSONObject jsonObject) {  
		
		
		// 調用hrp-web-service --發送數據成功
		
		
		TransactionTemplate template= new TransactionTemplate();
		
		template.execute(new TransactionCallback(){
			@Override
			public Object doInTransaction(TransactionStatus status) {
				OrderEntry orderEntry = new OrderEntry();
				Order order = new Order();
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				order.setConfirmTickDate(new Date());
				order.setId(jsonObject.getString("id"));
				order.setConfirmTick(Byte.parseByte("1"));
				orderMapper.updateByPrimaryKey(order);
				JSONArray orderEntryArray = JSONArray.parseArray(jsonObject.getString("entry"));
				for(int i=0;i<orderEntryArray.size();i++){
				JSONObject ob = orderEntryArray.getJSONObject(i);
				orderEntry.setConfirmDate(ob.getDate("confirmDate"));
				orderEntry.setConfirmQty(ob.getInteger("confirmQty"));
				orderEntry.setId(ob.getString("id"));
				OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
				orderEntryMapper.updateByPrimaryKey(orderEntry);
				}
				return "success";
			}});
		
		return "success";
	}
	
	/**
	 * 产生发货单
	 */
	@Override
	public Map<String, Object> invoice(String data,String userType) {

		String[] idString = data.split(",");
		Map<String,Object> order = new HashMap();
		Map<String, Object> orderEntry = new HashMap();
		ArrayList<Object> list = new ArrayList();
		Map<String, Object> entry = new HashMap();
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		ITemplateService template=Environ.getBean(ITemplateService.class);
		for(int i=0;i<idList.size();i++){
			Map<String,Object> map = template.getItemById(2019,idList.get(i), userType);
			Map<String, Object> mapEntry=(Map<String, Object>) map.get("entry");
			ArrayList<Object> arrayList  = (ArrayList<Object>) mapEntry.get("1");
			for (int j = 0; j < arrayList.size(); j++) {
				HashMap<String, Object> orderEntrys = (HashMap<String, Object>) arrayList.get(j);
				int qty = (int) orderEntrys.get("qty");
				if(map.get("saleProxy").equals("2"))
				for (int k = 0; k < qty; k++) {
					//表体数据
					entry.put("number", map.get("number"));
					entry.put("seq", orderEntrys.get("seq"));
					entry.put("material", orderEntrys.get("material"));
					entry.put("material_NmbName", orderEntrys.get("material_NmbName"));
					entry.put("material_DspName", orderEntrys.get("material_DspName"));
					entry.put("price", orderEntrys.get("price"));
					entry.put("unit_DspName", orderEntrys.get("unit_DspName"));
					entry.put("qty", 1);
					entry.put("amount", orderEntrys.get("localAmount"));
					entry.put("lot", "");
					entry.put("dyBatchNum", "");
					entry.put("code", "");
					entry.put("dyProDate", "");
					entry.put("dyManufacturer", "");
					entry.put("registrationNo", "");
					entry.put("effectiveDate", "");
					//表头数据
					order.put("number", "123456");
					order.put("Date", "");
					order.put("logistics", "");
					order.put("baseType", "采购订单");
					order.put("logisticsNo", "");
					order.put("supplier_DspName", map.get("supplier_DspName"));
					order.put("supplier",  map.get("supplier"));
					order.put("baseStatus", map.get("baseStatus"));
					list.add(entry);
					orderEntry.put("1", list);
					order.put("entry", orderEntry);
				}
			}
		}
		return order;
	}



}
