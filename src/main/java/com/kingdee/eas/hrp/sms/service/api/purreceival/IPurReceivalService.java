package com.kingdee.eas.hrp.sms.service.api.purreceival;

import com.alibaba.fastjson.JSONArray;

public interface IPurReceivalService {
	/**
	 * 
	 * 收货单同步
	 * 
	 */
	public String purReceival(JSONArray jsonarray);
}
