package com.kingdee.eas.hrp.sms.service.api;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;

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
	Map<String, Object> getItems(int classId, String condition, String orderBy, int pageNo, int pageSize);

	/**
	 * 根据模板查询单个业务数据，如查询id=2的物料
	 * 
	 * @Title getItemById
	 * @param classId
	 *            业务类别
	 * @param id
	 *            内码(主键) EAS表主键竟然用字符串
	 * @param userType
	 *            用户类型
	 * @return Map<String,Object>
	 * @date 2017-04-26 14:42:58 星期三
	 */
	Map<String, Object> getItemById(Integer classId, String id);

	/**
	 * 根基模板新增基础资料
	 * 
	 * @Title addItem
	 * @param classId
	 * @param data
	 * @return String 主键
	 * @date 2017-04-27 14:19:33 星期四
	 */
	String addItem(Integer classId, String data);

	/**
	 * 根基模板修改基础资料
	 * 
	 * @Title editItem
	 * @param classId
	 * @param id
	 * @param data
	 *            void
	 * @param userType
	 * @date 2017-04-27 15:40:28 星期四
	 */
	void editItem(Integer classId, String id, String data);

	/**
	 * 根基模板删除基础资料
	 * 
	 * @Title delItem
	 * @param classId
	 *            业务类型
	 * @param items
	 *            待删除的基础资料内码集合
	 * @return void
	 * @return
	 * @date 2017-05-02 17:49:38 星期二
	 */
	void delItem(Integer classId, String items);

	/**
	 * 审核基础资料
	 * 
	 * @Title checkItem
	 * @param classId
	 *            业务类型
	 * @param items
	 *            待审核的基础资料内码集合
	 * @param userType
	 * @return void
	 * @date 2017-05-23 16:11:01 星期二
	 */
	void checkItem(Integer classId, String items);

	/**
	 * 反审核
	 * 
	 * @Title checkItem
	 * @param classId
	 *            业务类型
	 * @param items
	 *            待反审核的基础资料内码集合
	 * @param userType
	 * @return void
	 * @date 2017-05-23 16:48:45 星期二
	 */
	void unCheckItem(Integer classId, String items);
}
