package com.kingdee.eas.hrp.sms.service.api.sys;

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
	String sendItem(int classId, String data);

	/**
	 * 删除item
	 * Title delItem
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
	String delItem(int classId, String data);

}
