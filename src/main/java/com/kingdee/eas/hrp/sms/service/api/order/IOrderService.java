package com.kingdee.eas.hrp.sms.service.api.order;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.Order;

public interface IOrderService {
	
	public String order(JSONObject orderjson);
	
	public Map<String, Object> updateOrderTime (Order order);
}
