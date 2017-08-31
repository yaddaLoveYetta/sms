package com.kingdee.eas.hrp.sms.service.api.purreturns;

import com.alibaba.fastjson.JSONArray;

public interface IPurReturnsService {
	
	/**
	 * 
	 * 退货单同步
	 * 
	 */
	public String purreturns(JSONArray jsonarray);

}
