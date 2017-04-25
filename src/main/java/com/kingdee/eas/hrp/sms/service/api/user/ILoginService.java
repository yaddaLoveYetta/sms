package com.kingdee.eas.hrp.sms.service.api.user;

import com.kingdee.eas.hrp.sms.model.User;

public interface ILoginService {

	/**
	 * 前端用户登录
	 * 
	 * @Title login
	 * @param username 用户名
	 * @param password 密码
	 * @param type  用户类别
	 * @return User
	 * @date 2017-04-25 10:06:14 星期二
	 */
	User login(String username, String password, int type);

	/**
	 * 获取token，用于用户在hrp系统与sms之间交互数据时验权使用
	 * 
	 * @Title createToken
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param type
	 *            用户类别(hrp中操作用户为sms系统用户，统一传1)
	 * @return User
	 * @date 2017-04-25 10:06:28 星期二
	 */
	User createToken(String username, String password, int type);
}
