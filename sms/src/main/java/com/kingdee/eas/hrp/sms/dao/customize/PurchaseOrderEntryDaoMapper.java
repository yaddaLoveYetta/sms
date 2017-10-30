package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;
/**
 * 模板操作接口
* @ClassName TemplateDaoMapper 
* @author yadda 
* @date 2017-04-20 16:46:36 星期四
 */
public interface PurchaseOrderEntryDaoMapper {

	/**
	 * 通过模板查询数据-根据groupby item
	 * 
	 * @param statementParam
	 *            查询语句结构
	 * @return
	 */
	List<Map<String, Object>> selectOrderGroupById(Map<String, Object> statementParam);
	
	/**
	 * 通过模板查询数据
	 * 
	 * @param statementParam
	 *            查询语句结构
	 * @return
	 */
	List<Map<String, Object>> selectOrderStatistics(Map<String, Object> statementParam);
	

}
