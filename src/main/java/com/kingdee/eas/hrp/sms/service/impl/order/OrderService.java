package com.kingdee.eas.hrp.sms.service.impl.order;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.OrderMapper;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class OrderService extends BaseService implements IOrderService{
	
	@Override
	public String order(JSONArray orderArray){
		Iterator<Object> it = orderArray.iterator();
			Order order=new Order();
		 while (it.hasNext()) {
             JSONObject ob = (JSONObject) it.next();
             if(ob.getString("supplier_id")!=null){
            	 order.setSupplier_id(ob.getString("supplier_id"));
             }
             if(ob.getString("supplier_name")!=null){
            	 order.setSupplier_name(ob.getString("supplier_name"));
             }
             if(ob.getString("buyer_id")!=null){
            	 order.setBuyer_id(ob.getString("buyer_id"));
             }
             /*if(ob.getString("material_code")!=null){
            	 order.setMaterial_code(ob.getString("material_code"));
             }
             if(ob.getString("material_name")!=null){
            	 order.setMaterial_name(ob.getString("material_name"));
             }
             if(ob.getString("specifications")!=null){
            	 order.setSpecifications(ob.getString("specifications"));
             }
             if(ob.getString("basic_unit_measurement")!=null){
            	 order.setBasic_unit_measurement(ob.getString("basic_unit_measurement"));
             }
             if(ob.getBigDecimal("price")!=null){
            	 order.setPrice(ob.getBigDecimal("price"));
             }
             if(ob.getInteger("numbers")!=null){
            	 order.setNumbers(ob.getInteger("numbers"));
             }*/
             if(ob.getDate("order_time")!=null){
					order.setOrder_time(ob.getDate("order_time"));
             }
            /* if(ob.getDate("delivery_time")!=null){
            	 order.setDelivery_time(ob.getDate("delivery_time"));
             }*/
             if(ob.getDate("cutasingle_time")!=null){
            	 order.setCutasingle_time(ob.getDate("cutasingle_time"));
             }
             if(ob.getInteger("confirm_delivery_numbers")!=null){
            	 order.setConfirm_delivery_numbers(ob.getInteger("confirm_delivery_numbers"));
             }
             if(ob.getInteger("purchasing_mode")!=null){
            	 order.setPurchasing_mode(ob.getInteger("purchasing_mode"));
             }
             if(ob.getInteger("tax")!=null){
            	 order.setTax(ob.getInteger("tax"));
             }
             if(ob.getInteger("confirm_order")!=null){
            	 order.setConfirm_order(ob.getInteger("confirm_order"));
             }
		 }
		 OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		 mapper.insertSelective(order);
		 
		return "success";
	}
}
