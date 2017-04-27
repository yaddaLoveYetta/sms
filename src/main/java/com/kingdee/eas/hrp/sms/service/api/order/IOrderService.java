package com.kingdee.eas.hrp.sms.service.api.order;

import com.alibaba.fastjson.JSONArray;

public interface IOrderService {
	
	public String order(JSONArray orderArray);
}
