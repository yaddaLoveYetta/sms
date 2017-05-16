package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.Map;

/**
 * @author Sunny
 *
 * 2017年4月25日
 */
public interface ISyncHRPService {

	
	/**
	 * 同步item
	 * Title sendItem
	 * 
	 * @param classId
	 * @param data
	 * @param userType
	 *
	 *return void 
	 *
	 * 2017年5月11日下午5:31:13
	 * @return 
	 */
	Map<String, String> sendItem(int classId, String data, String userType);

}
