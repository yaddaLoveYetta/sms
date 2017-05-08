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
				order.setLineNumbers(orderjson.getInteger("lineNumbers"));
				order.setSupplierName(orderjson.getString("supplierName"));
			if(orderjson.getString("orderTime")!=null){
				order.setOrderTime(sft.parse(orderjson.getString("orderTime")));
			}
			if(orderjson.getString("cutasingleTime")!=null){
				order.setCutasingleTime(sft.parse(orderjson.getString("cutasingleTime")));
			}
				order.setBuyer(orderjson.getString("buyer"));
				order.setPurchasingMode(orderjson.getInteger("purchasing_mode"));
				order.setTax(orderjson.getInteger("tax"));
				order.setPrice(orderjson.getBigDecimal("price"));
				order.setNumbers(orderjson.getInteger("numbers"));
				if(orderjson.getString("deliveryTime")!=null){
					order.setDeliveryTime(sft.parse(orderjson.getString("deliveryTime")));
				}
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			//录入订单物料信息
			JSONArray materialJson = orderjson.getJSONArray("entry");
			for(int j=0;j<materialJson.size();j++){
				JSONObject materialObject = JSONObject.parseObject(JSON.toJSONString(materialJson.get(j)));
				material.setMaterialCode(materialObject.getString("materialCode"));
				material.setMaterialName(materialObject.getString("materialName"));
				material.setSpecifications(materialObject.getString("specifications"));
				material.setBasicUnitMeasurement(materialObject.getString("basicUnitMeasurement"));
				material.setOrderId(order.getId());	
				MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
				materialMapper.insertSelective(material);
			
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "success";
	}

	@Override
	public Map<String, Object> updateOrderTime(Order order) {
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		Order orderList  = orderMapper.selectByPrimaryKey(order.getId());
		Map<String, Object> errItem = new HashMap<String, Object>();
		if(orderList.getNumbers()<order.getConfirmDeliveryNumbers()){
			errItem.put("error","交货数量大于订单需求数量");
		}else{
			orderMapper.updateByPrimaryKeySelective(order);
			errItem.put("success","接单成功");
		}
		return errItem;
	}
}
