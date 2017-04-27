package com.kingdee.eas.hrp.sms.service.api;

import java.util.Map;

import com.kingdee.eas.hrp.sms.model.FormFields;

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

	/**
	 * 获取单据模板 </br>
	 * 不按page打包
	 * 
	 * @Title getFormFields
	 * @param classId
	 *            业务类别
	 * @param page
	 *            -1 查询所有page
	 * @return Map<String,FormFields>
	 * @date 2017-04-21 09:32:18 星期五
	 */
	Map<String, FormFields> getFormFields(int classId, int page);

	/**
	 * 根据模板获取业务数据（一般用来获取基础资料）
	 * 
	 * @Title getItems
	 * @param classId
	 *            业务类别
	 * @param condition
	 *            格式化的查询条件
	 * @param orderBy
	 *            格式化的排序条件
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param userType
	 *            用户类型
	 * @return Map<String,Object>
	 * @date 2017-04-20 13:49:24 星期四
	 */
	Map<String, Object> getItems(int classId, String condition, String orderBy, int pageNo, int pageSize, int userType);

	/**
	 * 根据模板查询单个业务数据，如查询id=2的物料
	 * 
	 * @Title getItemById
	 * @param classId
	 *            业务类别
	 * @param id
	 *            内码(主键)
	 * @param userType
	 *            用户类型
	 * @return Map<String,Object>
	 * @date 2017-04-26 14:42:58 星期三
	 */
	Map<String, Object> getItemById(Integer classId, Integer id, int userType);

	/**
	 * 根基模板新增基础资料
	 * 
	 * @Title addItem
	 * @param classId
	 * @param data
	 * @return int
	 * @date 2017-04-27 14:19:33 星期四
	 */
	int addItem(Integer classId, String data);
}
