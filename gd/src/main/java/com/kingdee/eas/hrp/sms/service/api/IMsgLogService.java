package com.kingdee.eas.hrp.sms.service.api;
/**
* 类名称：IMsgLogService.java
* 创建时间：2017年6月21日 下午2:39:05
* @version 
* 类说明：短信发送日志信息
*/
public interface IMsgLogService {
	
	void saveLog(String seqid, String mobiles, String smsContent, String reString);

}
