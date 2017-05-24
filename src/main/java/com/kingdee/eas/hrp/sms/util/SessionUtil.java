package com.kingdee.eas.hrp.sms.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kingdee.eas.hrp.sms.model.User;

public final class SessionUtil {

	private static final ThreadLocal<Map<String, Object>> SESSION_MAP = new ThreadLocal<Map<String, Object>>();

	public static void set(String key, Object value) {

		Map<String, Object> map = SESSION_MAP.get();

		if (map == null) {
			map = new HashMap<String, Object>();
			SESSION_MAP.set(map);
		}

		map.put(key, value);
	}

	public static Object get(String key) {

		Map<String, Object> map = (Map<String, Object>) SESSION_MAP.get();

		return map.get(key);

	}

	public static User getUser() {

		Map<String, Object> map = (Map<String, Object>) SESSION_MAP.get();

		return (User) map.get("user");

	}

	/**
	 * 获得线程中保存的属性，使用指定类型进行转型.
	 * 
	 * @param key
	 *            属性名称
	 * @param clazz
	 *            类型
	 * @param <T>
	 *            自动转型
	 * @return 属性值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) {
		return (T) get(key);
	}

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
	public static String getUserId(HttpServletRequest request) {
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
