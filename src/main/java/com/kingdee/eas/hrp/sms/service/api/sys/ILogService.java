package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.Date;

public interface ILogService {

	/**
	 * 添加一条操作日志
	 * 
	 * @Title add
	 * @param userName
	 * @param ip
	 * @param desc
	 * @param optTime
	 * @param clazz
	 * @param method
	 * @param params
	 * @return void
	 * @date 2017-05-27 10:42:11 星期六
	 */
	void add(String userName, String ip, String desc, Date optTime, String clazz, String method,String params);

}
