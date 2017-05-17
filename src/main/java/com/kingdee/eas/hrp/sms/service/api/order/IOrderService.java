package com.kingdee.eas.hrp.sms.service.api.order;


import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IOrderService {
	
	public String order(JSONArray jsonarray);
	
	public String updatetickType (JSONObject jsonObject);
	
	Map<String, Object> invoice( String data,String userType);
	 
}
