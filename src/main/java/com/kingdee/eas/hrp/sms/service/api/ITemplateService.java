package com.kingdee.eas.hrp.sms.service.api;

import java.util.Map;

public interface ITemplateService {

	/**
	 * 获取系统模板
	 * 
	 * @Title getFormTemplate
	 * @param classId
	 *            业务类别
	 * @param type
	 *            0：前段获取 1：后端获取
	 * @return Map<String,Object>
	 * @date 2017-04-20 11:31:27 星期四
	 */
	Map<String, Object> getFormTemplate(int classId, int type);
}
