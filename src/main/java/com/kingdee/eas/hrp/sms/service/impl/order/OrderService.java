package com.kingdee.eas.hrp.sms.service.impl.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
				order.setLine_numbers(orderjson.getInteger("line_numbers"));
				order.setSupplier_name(orderjson.getString("supplier_name"));
				order.setPurchase_order_no(orderjson.getString("purchase_order_no"));
			if(orderjson.getString("order_time")!=null){
				order.setOrder_time(sft.parse(orderjson.getString("order_time")));
			}
			if(orderjson.getString("cutasingle_time")!=null){
				order.setCutasingle_time(sft.parse(orderjson.getString("cutasingle_time")));
			}
				order.setBuyer_id(orderjson.getString("buyer_id"));
				order.setPurchasing_mode(orderjson.getInteger("purchasing_mode"));
				order.setTax(orderjson.getInteger("tax"));
				OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
				orderMapper.insertSelective(order);
			
			//录入物料信息
			JSONArray materialJson = orderjson.getJSONArray("entry");
			for(int j=0;j<materialJson.size();j++){
				JSONObject materialObject = JSONObject.parseObject(JSON.toJSONString(materialJson.get(j)));
				material.setMaterial_code(materialObject.getString("material_code"));
				material.setMaterial_name(materialObject.getString("material_name"));
				material.setSpecifications(materialObject.getString("specifications"));
				material.setBasic_unit_measurement(materialObject.getString("basic_unit_measurement"));
				material.setPrice(materialObject.getBigDecimal("price"));
				material.setNumbers(materialObject.getInteger("numbers"));
			if(materialObject.getString("delivery_time")!=null){
				material.setDelivery_time(sft.parse(materialObject.getString("delivery_time")));
			}
				material.setOrder_id(order.getId());	
				MaterialMapper materialMapper = sqlSession.getMapper(MaterialMapper.class);
				materialMapper.insertSelective(material);
			
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "success";
	}
}
