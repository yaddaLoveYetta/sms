package com.kingdee.eas.hrp.sms.service.api.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IOrderService {
	
	public String order(JSONObject orderjson);
}
