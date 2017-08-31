package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

public interface RoleDaoMapper {

	/**
	 * 获取角色所有权限
	 * 
	 * @Title getRoleTypePermissions
	 * @param type
	 *            角色类别(供应商 or 平台)
	 * @return List<Map<String,Object>>
	 * @date 2017-04-25 11:25:36 星期二
	 */
	public List<Map<String, Object>> getRoleTypePermissions(int type);

}
