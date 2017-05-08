package com.kingdee.eas.hrp.sms.util;

import javax.servlet.http.HttpServletRequest;

import com.kingdee.eas.hrp.sms.model.User;

public final class SessionUtil {

	/**
	 * 获取当前会话用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		return user;
	}

	/**
	 * 获取当前会话用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserID(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user.getUserId();
	}

	/**
	 * 获取当前会话用户名
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserName(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		return user.getName();
	}

	/**
	 * 获取当前会话用户类别<br>
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserType(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		return user.getType();

	}

}
