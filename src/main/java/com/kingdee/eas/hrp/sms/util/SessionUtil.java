package com.kingdee.eas.hrp.sms.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class SessionUtil {

	/**
	 * 获取当前会话用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getCurrentUser(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		return user;
	}

	/**
	 * 获取当前会话用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static int getUserID(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		return (Integer) user.get("userID");
	}

	/**
	 * 获取当前会话用户名
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserName(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		return (String) user.get("name");
	}


	/**
	 * 获取当前会话用户类别<br>
	 * 
	 * @param request
	 * @return
	 */
	public static int getUserType(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		return (Integer) user.get("type");

	}

}
