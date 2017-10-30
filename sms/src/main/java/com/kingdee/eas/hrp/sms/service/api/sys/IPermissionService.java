package com.kingdee.eas.hrp.sms.service.api.sys;

import com.kingdee.eas.hrp.sms.model.ObjectType;

public interface IPermissionService {

	/**
	 * 权限判断
	 * 
	 * @Title checkPermissionByUserId
	 * @Description 根据用户id判断其对某系统的权限
	 * @param userId
	 *            用户id
	 * @param objectType
	 *            系统ID
	 * @param objectId
	 *            子系统ID
	 * @param accessMask
	 *            权限掩码
	 * 
	 * @return boolean
	 * @date 2017-04-15 21:00:53 星期六
	 */
	boolean checkPermissionByUserId(String userId, int objectType, int objectId, int accessMask);

	/**
	 * 权限判断
	 * 
	 * @Title checkPermissionByRole
	 * @Description 根据用户角色判断其对某系统的权限
	 * @param roleId
	 *            角色ID
	 * @param objectType
	 *            系统ID
	 * @param objectId
	 *            子系统ID
	 * @param accessMask
	 *            权限掩码
	 * 
	 * @return boolean
	 * @date 2017-04-15 20:58:18 星期六
	 */
	boolean checkPermissionByRole(String roleId, int objectType, int objectId, int accessMask);

	/**
	 * 通过classId转换获取objectType-objectId
	 * 
	 * @Title getAccessType
	 * @param classId
	 * @return
	 * @return Map<String,Integer>
	 * @date 2017-05-26 15:16:07 星期五
	 */
	ObjectType getAccessType(int classId);
}
