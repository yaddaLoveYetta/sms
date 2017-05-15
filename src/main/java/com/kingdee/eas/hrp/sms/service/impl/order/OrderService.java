package com.kingdee.eas.hrp.sms.service.impl.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.MaterialMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.model.Material;
import com.kingdee.eas.hrp.sms.model.MaterialExample;
import com.kingdee.eas.hrp.sms.model.MaterialExample.Criteria;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class OrderService extends BaseService implements IOrderService{
	
	@Override
	public String order(JSONObject orderjson){
		Order order = new Order();
		OrderEntry orderEntry = new OrderEntry();
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
			try{
			//录入订单抬头
            	order.setId(orderjson.getString("id"));
				order.setSupplier(orderjson.getString("supplierId"));
				order.setPurchasePerson(orderjson.getString("purchasePerson"));
				order.setSaleProxy(orderjson.getString("saleProxy"));
				order.setIsInTax(orderjson.getByte("isInTax"));
				order.setNumber(orderjson.getString("number"));
				order.setIsQuicken(orderjson.getByte("isQuicken"));
				order.setCurrency(orderjson.getString("currency"));
				order.setSettlementType(orderjson.getString("settlementType"));
				order.setTotalAmount(orderjson.getBigDecimal("totalAmount"));
				order.setTotalTax(orderjson.getBigDecimal("totalTax"));
				order.setTotalTaxAmount(orderjson.getBigDecimal("totalTaxAmount"));
				if(orderjson.getString("date")!=null){
				order.setBizDate(sft.parse(orderjson.getString("BizDate")));
				}
				if(orderjson.getString("makeDate")!=null){
				order.setCreateTime(sft.parse(orderjson.getString("createTime")));
				}
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			//录入订单物料信息
			JSONArray materialJson = orderjson.getJSONArray("entries");
			for(int j=0;j<materialJson.size();j++){
				JSONObject materialObject = JSONObject.parseObject(JSON.toJSONString(materialJson.get(j)));
				orderEntry.setMaterial(materialObject.getString("supplierMaterialNumber"));
				orderEntry.setUnit(materialObject.getString("unit"));
				orderEntry.setParent(materialObject.getString("parent"));
				orderEntry.setPrice(materialObject.getBigDecimal("price"));
				orderEntry.setQty(materialObject.getInteger("qty"));
				orderEntry.setDiscountRate(materialObject.getDouble("discountRate"));
				orderEntry.setTaxRate(materialObject.getDouble("taxRate"));
				orderEntry.setTaxPrice(materialObject.getBigDecimal("taxPrice"));
				orderEntry.setActualTaxPrice(materialObject.getBigDecimal("actualTaxPrice"));
				orderEntry.setDiscountAmount(materialObject.getBigDecimal("discountAmount"));
				orderEntry.setTax(materialObject.getBigDecimal("tax"));
				orderEntry.setLocalAmount(materialObject.getBigDecimal("localAmount"));
				orderEntry.setSeq(Integer.parseInt(materialObject.getString("seq")));
				orderEntry.setId(materialObject.getString("id"));
				if(materialObject.getString("deliveryDate")!=null){
					orderEntry.setDeliveryDate(sft.parse(materialObject.getString("deliveryDate")));
				}
				OrderEntryMapper orderEntryMapper = sqlSession.getMapper(OrderEntryMapper.class);
				orderEntryMapper.insertSelective(orderEntry);
			
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "success";
	}

	@Override
	public Map<String, Object> updatetickType(OrderEntry orderEntry, Order order) {
		// TODO Auto-generated method stub
		return null;
	}



}
