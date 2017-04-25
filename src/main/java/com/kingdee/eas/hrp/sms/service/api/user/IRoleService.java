package com.kingdee.eas.hrp.sms.service.api.user;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.Role;

public interface IRoleService {

	/**
	 * 获取角色权限
	 * 
	 * @param roleID
	 * @return
	 */
	Map<String, Object> getAccessByRole(int roleId);

	/**
	 * 获取角色信息
	 * 
	 * @Title getRole
	 * @param roleId
	 *            角色id
	 * @return Role
	 * @date 2017-04-18 11:57:24 星期二
	 */
	Role getRole(int roleId);

	/**
	 * 获取角色所有权限
	 * 
	 * @Title getRoleTypePermissions
	 * @param type
	 *            角色类别(供应商 or 平台)
	 * @param type
	 *            角色id
	 * @return List<Map<String,Object>>
	 * @date 2017-04-25 11:25:36 星期二
	 */
	List<Map<String, Object>> getRolePermissions(int type, int roleId);

	/**
	 * 保存角色权限
	 * 
	 * @Title saveRolePermissions
	 * @param arry
	 *            权限集合
	 * @param roleId
	 *            角色id void
	 * @date 2017-04-25 16:33:34 星期二
	 */
	void saveRolePermissions(JSONArray arry, int roleId);

}
