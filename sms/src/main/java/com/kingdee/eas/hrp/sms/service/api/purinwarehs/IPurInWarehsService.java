package com.kingdee.eas.hrp.sms.service.api.purinwarehs;

import com.alibaba.fastjson.JSONArray;

public interface IPurInWarehsService {
	
	/**
	 * 
	 * 入库单同步
	 * 
	 */
	public String purInWarehs(JSONArray jsonarray);

}
