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
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.model.Material;
import com.kingdee.eas.hrp.sms.model.MaterialExample;
import com.kingdee.eas.hrp.sms.model.MaterialExample.Criteria;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class OrderService extends BaseService implements IOrderService{
	
	@Override
	public String order(JSONObject orderjson){
		Order order = new Order();
		Material material = new Material();
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
			try{
			//录入订单抬头
            	order.setId(orderjson.getString("id"));
				order.setSupplier(orderjson.getString("supplierId"));
				order.setPurchasePerson(orderjson.getString("purchasePerson"));
				order.setSaleProxy(orderjson.getString("saleProxy"));
				order.setIsInTax(orderjson.getDouble("isInTax"));
				order.setNumber(orderjson.getString("number"));
				order.setIsQuicken(orderjson.getInteger("isQuicken"));
				order.setCurrency(orderjson.getString("currency"));
				order.setPaymentCondition(orderjson.getString("paymentCondition"));
				order.setPaymentType(orderjson.getString("paymentType"));
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
				material.setMaterial(materialObject.getString("supplierMaterialNumber"));
				material.setUnit(materialObject.getString("unit"));
				material.setParent(materialObject.getString("parent"));
				material.setPrice(materialObject.getBigDecimal("price"));
				material.setQty(materialObject.getInteger("qty"));
				material.setDiscountRate(materialObject.getDouble("discountRate"));
				material.setTaxRate(materialObject.getDouble("taxRate"));
				material.setTaxPrice(materialObject.getBigDecimal("taxPrice"));
				material.setActualTaxPrice(materialObject.getBigDecimal("actualTaxPrice"));
				material.setDiscountAmount(materialObject.getBigDecimal("discountAmount"));
				material.setTax(materialObject.getBigDecimal("tax"));
				material.setLocalAmount(materialObject.getBigDecimal("localAmount"));
				material.setSeq(Integer.parseInt(materialObject.getString("seq")));
				material.setId(materialObject.getString("id"));
				if(materialObject.getString("deliveryDate")!=null){
				material.setDeliveryDate(sft.parse(materialObject.getString("deliveryDate")));
				}
				MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
				materialMapper.insertSelective(material);
			
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "success";
	}

	@Override
	public Map<String, Object> updatetickType(Material material, Order order) {
		MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
		materialMapper.updateByPrimaryKey(material);
		
		return null;
	}


}
