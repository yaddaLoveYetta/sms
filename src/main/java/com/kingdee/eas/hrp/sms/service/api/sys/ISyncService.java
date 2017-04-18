package com.kingdee.eas.hrp.sms.service.api.sys;

import com.alibaba.fastjson.JSONArray;

public interface ISyncService {

	void supplier(JSONArray list);
	void category(JSONArray list);
	void certificate(JSONArray list);
	void industry(JSONArray list);
	void currency(JSONArray list);
	void settlement(JSONArray list);
	void pay(JSONArray list);
	void item(JSONArray list);
	void taxCategory(JSONArray list);
	
}
