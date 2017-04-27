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

	/**
	 * 通过模板插入数据
	 * 
	 * @Title addItem
	 * @param statement
	 * @return int
	 * @date 2017-04-27 14:36:32 星期四
	 */
	int add(Map<String, Object> statement);

	/**
	 * 通过模板更新数据
	 * 
	 * @Title addItem
	 * @param statement
	 * @return int
	 * @date 2017-04-27 14:36:32 星期四
	 */
	void edit(Map<String, Object> statement);

	/**
	 * 通过模板删除数据
	 * 
	 * @Title del
	 * @param statement
	 *            void
	 * @date 2017-04-27 15:18:12 星期四
	 */
	void del(Map<String, Object> statement);
}
