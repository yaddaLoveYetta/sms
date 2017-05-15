package com.kingdee.eas.hrp.sms.service.api.order;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;

public interface IOrderService {
	
	public String order(JSONArray orderjson);
	
	public Map<String, Object> updatetickType (OrderEntry orderEntry,Order order);
}
