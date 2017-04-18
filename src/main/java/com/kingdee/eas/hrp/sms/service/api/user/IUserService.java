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
	User getUser(int userId);

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
	List<Map<String, Object>> getSysMenu(int type, int userId);
}
