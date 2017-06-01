package com.kingdee.eas.hrp.sms.service.api.sys;

/**
 * @author Sunny
 *
 *         2017年4月25日
 */
public interface ISyncHRPService {

	/**
	 * 同步item Title sendItem
	 * 
	 * @param classId
	 * @param data
	 * @param userType
	 *
	 *            return void
	 *
	 *            2017年5月11日下午5:31:13
	 * @return
	 */
	String sendItem(int classId, String data);

	/**
	 * 调HRP登录接口，返回SessionId Title loginInEAS
	 *
	 * return String
	 *
	 * 2017年5月11日下午5:31:13
	 * 
	 * @return
	 */
	String loginInEAS();

	/**
	 * 调HRP同步接口 Title sendItemByWS
	 *
	 * @param sessionId
	 * @param data
	 * @param method
	 * 
	 * @return String
	 *
	 * 2017年5月11日下午5:31:13
	 * @return
	 */
	String syncItemByWS(String sessionId, String data, String method);

}
