package com.kingdee.eas.hrp.sms.service.api.user;

import java.util.Map;

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

}
