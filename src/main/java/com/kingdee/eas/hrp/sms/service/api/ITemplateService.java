package com.kingdee.eas.hrp.sms.service.api;

import java.util.List;
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
	 * @return Map<String,Object>
	 * @date 2017-04-26 14:42:58 星期三
	 */
	Map<String, Object> getItemById(Integer classId, String id);

	/**
	 * 根据模板查询单个业务数据，如查询id=2的物料
	 * 
	 * @Title getItemById
	 * @param classId
	 *            业务类别
	 * @param id
	 *            内码(主键) EAS表主键竟然用字符串
	 * @param orderBy
	 *            格式化的排序条件
	 * @return Map<String,Object>
	 * @date 2017-04-26 14:42:58 星期三
	 */
	Map<String, Object> getItemById(Integer classId, String id, String orderBy);

	/**
	 * 根据模板查询多个业务数据
	 * 
	 * @Title getItemByIds
	 * @param classId
	 * @param ids
	 *            单据内码集合
	 * @param orderByStr
	 *            排序规则(拼接好的排序字符串)
	 * @return
	 * @return Map<String,Object>
	 * @date 2017-07-11 15:42:31 星期二
	 */
	List<Map<String, Object>> getItemByIds(Integer classId, List<String> ids, String orderByStr);

	/**
	 * 根基模板新增基础资料
	 * 
	 * @Title addItem
	 * @param classId
	 *            业务类型
	 * @param data
	 *            json格式的单据数据
	 * @return String 主键
	 * @date 2017-04-27 14:19:33 星期四
	 */
	String addItem(Integer classId, String data);

	/**
	 * 物料证件实现一个证件多物料新增功能,此方法仅供物料证件批量新增使用
	 * 
	 * @Title batchAddItemLicense
	 * @param classId
	 *            业务类型
	 * @param data
	 *            json格式的单据数据
	 * @return
	 * @return String
	 * @date 2017-08-29 17:09:54 星期二
	 */
	String batchAddItemLicense(Integer classId, String data);

	/**
	 * 根基模板修改基础资料
	 * 
	 * @Title editItem
	 * @param classId
	 *            业务类型
	 * @param id
	 *            修改资料的内码
	 * @param data
	 *            json格式的修改资料数据包
	 * @date 2017-04-27 15:40:28 星期四
	 */
	void editItem(Integer classId, String id, String data);

	/**
	 * 物料证件实现一个证件多物料修改功能
	 * 
	 * 此方法仅供物料证件批量新增保存后当前页面修改使用
	 * 
	 * @Title editItem
	 * @param classId
	 *            业务类型
	 * @param id
	 *            修改资料的内码
	 * @param data
	 *            json格式的修改资料数据包
	 * @date 2017-04-27 15:40:28 星期四
	 */
	void batchEditItemLicense(Integer classId, String id, String data);

	/**
	 * 根基模板删除基础资料
	 * 
	 * @Title delItem
	 * @param classId
	 *            业务类型
	 * @param items
	 *            待删除的基础资料内码集合
	 * @return void
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
	 * @return void
	 * @date 2017-05-23 16:48:45 星期二
	 */
	void unCheckItem(Integer classId, String items);

}
