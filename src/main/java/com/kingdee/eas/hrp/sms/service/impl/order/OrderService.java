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
				order.setSupplierId(orderjson.getString("supplierId"));
				order.setBuyer(orderjson.getString("buyer"));
				order.setPurchasingMode(orderjson.getString("purchasing_mode"));
				order.setTax(orderjson.getInteger("tax"));
				order.setOrderNo(orderjson.getString("orderNo"));
				order.setPurchasingType(orderjson.getString("purchasingType"));
				order.setUrgent(orderjson.getInteger("urgent"));
				order.setCurrency(orderjson.getString("currency"));
				order.setPaymentConditions(orderjson.getString("paymentConditions"));
				order.setPaymentWay(orderjson.getString("paymentWay"));
				order.setSettlementWay(orderjson.getString("settlementWay"));
				order.setAmount(orderjson.getBigDecimal("amount"));
				order.setTaxAmount(orderjson.getBigDecimal("taxAmount"));
				order.setLeviedCombined(orderjson.getBigDecimal("leviedCombined"));
				order.setAuditUser(orderjson.getString("auditUser"));
				if(orderjson.getString("date")!=null){
				order.setDate(sft.parse(orderjson.getString("date")));
				}
				if(orderjson.getString("makeDate")!=null){
				order.setMakeDate(sft.parse(orderjson.getString("makeDate")));
				}
				if(orderjson.getString("auditDate")!=null){
				order.setAuditDate(sft.parse(orderjson.getString("auditDate")));
				}
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			//录入订单物料信息
			JSONArray materialJson = orderjson.getJSONArray("entry");
			for(int j=0;j<materialJson.size();j++){
				JSONObject materialObject = JSONObject.parseObject(JSON.toJSONString(materialJson.get(j)));
				material.setMaterialId(materialObject.getString("materialId"));
				material.setBasicUnitMeasurement(materialObject.getString("basicUnitMeasurement"));
				material.setOrderId(order.getId());
				material.setUnitPrice(materialObject.getBigDecimal("unitPrice"));
				material.setNumbers(materialObject.getInteger("numbers"));
				material.setDiscount(materialObject.getDouble("discount"));
				material.setRate(materialObject.getDouble("rate"));
				material.setTaxUnitPrice(materialObject.getBigDecimal("taxUnitPrice"));
				material.setActualTaxUnitPrice(materialObject.getBigDecimal("actualTaxUnitPrice"));
				material.setDiscountPrice(materialObject.getBigDecimal("discountPrice"));
				material.setTaxPrice(materialObject.getBigDecimal("taxPrice"));
				material.setFunctionalCurrencyAmount(materialObject.getBigDecimal("functionalCurrencyAmount"));
				if(materialObject.getString("deliveryTime")!=null){
				material.setDeliveryTime(sft.parse(materialObject.getString("deliveryTime")));
				}
				MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
				materialMapper.insertSelective(material);
			
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "success";
	}

	/*@Override
	public Map<String, Object> updateOrderTime(Order order) {
		int qty= order.getConfirmDeliveryNumbers(); 
		
		MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
		
		MaterialExample example=new MaterialExample();
		
		Criteria  criteria = example.createCriteria();
		
		criteria.andOrderIdEqualTo(order.getId());
		List<Material> list=materialMapper.selectByExample(example);
		Map<String, Object> errItem = new HashMap<String, Object>();
			errItem.put("error","交货数量大于订单需求数量");
			errItem.put("success","接单成功");
		return errItem;
	}*/
}
