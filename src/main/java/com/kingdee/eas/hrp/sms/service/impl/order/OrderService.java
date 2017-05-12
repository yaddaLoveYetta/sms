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
				order.setSaleType(orderjson.getString("saleType"));
				order.setIsQuicken(orderjson.getInteger("isQuicken"));
				order.setCurrency(orderjson.getString("currency"));
				order.setPaymentCondition(orderjson.getString("paymentCondition"));
				order.setPaymentType(orderjson.getString("paymentType"));
				order.setSettlementType(orderjson.getString("settlementType"));
				order.setTotalAmount(orderjson.getBigDecimal("totalAmount"));
				order.setTotalTax(orderjson.getBigDecimal("totalTax"));
				order.setTotalTaxAmount(orderjson.getBigDecimal("totalTaxAmount"));
				if(orderjson.getString("date")!=null){
				order.setDate(sft.parse(orderjson.getString("date")));
				}
				if(orderjson.getString("makeDate")!=null){
				order.setCreateTime(sft.parse(orderjson.getString("createTime")));
				}
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			//录入订单物料信息
			JSONArray materialJson = orderjson.getJSONArray("entry");
			for(int j=0;j<materialJson.size();j++){
				JSONObject materialObject = JSONObject.parseObject(JSON.toJSONString(materialJson.get(j)));
				material.setSupplierMaterialNumber(materialObject.getString("supplierMaterialNumber"));
				material.setNoNumMaterialModel(materialObject.getString("noNumMaterialModel"));
				material.setOrderId(order.getId());
				material.setPrice(materialObject.getBigDecimal("price"));
				material.setQty(materialObject.getInteger("qty"));
				material.setDiscountRate(materialObject.getDouble("discountRate"));
				material.setTaxRate(materialObject.getDouble("taxRate"));
				material.setTaxPrice(materialObject.getBigDecimal("taxPrice"));
				material.setActualTaxPrice(materialObject.getBigDecimal("actualTaxPrice"));
				material.setDiscountPrice(materialObject.getBigDecimal("discountPrice"));
				material.setTax(materialObject.getBigDecimal("tax"));
				material.setLocalPrice(materialObject.getBigDecimal("localPrice"));
				material.setLineNumbers(Integer.parseInt(materialObject.getString("lineNumbers")));
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
