package com.kingdee.eas.hrp.sms.service.plugin;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 后台系统-基础资料业务插件接口<br>
 * 
 * 用于处理个性化业务需求<br>
 * 
 * 例如：基础资料操作前的数据校验，操作前的关联操作<br>
 * 
 * 操作后的数据校验，关联操作等等跟业务相关但又缺乏共性的逻辑<br>
 * 
 * @ClassName IPlugIn
 * @author yadda
 * @date 2017-04-27 17:40:40 星期四
 */
public interface IPlugIn {
	/**
	 * 基础资料新增前操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param formData
	 *            单据模板
	 * @param data
	 *            业务数据
	 * @return
	 */
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, int userTyepe);

	/**
	 * 基础资料新增后操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param id
	 *            新增的资料内码
	 * @param data
	 *            业务数据
	 * @return
	 */
	public PlugInRet afterSave(int classId, int id, JSONObject data);

	/**
	 * 基础资料修改前操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param data
	 *            业务数据
	 * @return
	 */
	public PlugInRet beforeModify(int classId, int id,Map<String, Object> formData, JSONObject data, int userType);

	/**
	 * 基础资料修改后操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param data
	 *            业务数据
	 * @return
	 */
	public PlugInRet afterModify(int classId, JSONObject data);

	/**
	 * 基础资料删除前操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param items
	 *            删除的内码集合
	 * @return
	 */
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String items);

	/**
	 * 基础资料删除后操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param items
	 *            删除的内码集合
	 * @return
	 */
	public PlugInRet afterDelete(int classId, String items);

	/**
	 * 基础资料查询前操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param param
	 *            查询参数
	 * @return
	 */
	public PlugInRet beforeQuery(int classId, Map<String, Object> param);

	/**
	 * 基础资料查询后操作
	 * 
	 * @param classId
	 *            业务类型
	 * @param list
	 *            查询结果集
	 * @return
	 */
	public PlugInRet afterQuery(int classId, List<Map<String, Object>> list);
}
