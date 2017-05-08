package com.kingdee.eas.hrp.sms.service.api.user;

import java.util.List;
import java.util.Map;

import com.kingdee.eas.hrp.sms.model.User;

public interface IUserService {

	/**
	 * 分页获取系统用户
	 * 
	 * @Title getUsers
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @return List<User>
	 * @date 2017年4月15日 上午12:01:39
	 */
	List<User> getUserList(int pageNum, int pageSize);

	/**
	 * 根据用户id获取用户
	 * 
	 * @Title getUser
	 * @param userId
	 * @return User
	 * @date 2017-04-18 12:02:12 星期二
	 */
	User getUser(String userId);

	/**
	 * 根据用户获取可用菜单列表
	 * 
	 * @Title getSysMenu
	 * @param type
	 *            用户类别
	 * @param userId
	 *            用户id
	 * @return List<Map<String,Object>>
	 * @date 2017-04-18 11:34:51 星期二
	 */
	List<Map<String, Object>> getSysMenu(String type, String userId);

	/**
	 * 修改密码
	 * 
	 * @Title editPwd
	 * @param userId
	 *            用户id
	 * @param oldpwd
	 *            原密码
	 * @param newpwd
	 *            新密码
	 * @return boolean
	 * @date 2017-05-06 00:04:16 星期六
	 */
	boolean editPwd(String userId, String oldpwd, String newpwd);
}
