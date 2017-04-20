package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

public interface TemplateDaoMapper {

	/**
	 * 通过模板查询数据
	 * 
	 * @param statementParam
	 *            查询语句结构
	 * @return
	 */
	List<Map<String, Object>> getItems(Map<String, Object> statementParam);
}
