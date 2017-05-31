package com.kingdee.eas.hrp.sms.util;

import java.util.HashMap;
import java.util.Map;

import com.kingdee.eas.hrp.sms.exception.SessionLostRuntimeException;
import com.kingdee.eas.hrp.sms.model.User;

public final class SessionUtil {

	private static ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<Map<String, Object>>();

	/**
	 * 新增
	 * 
	 * @Title set
	 * @param key
	 * @param value
	 * @return void
	 * @date 2017-05-30 01:03:36 星期二
	 */
	public static void set(String key, Object value) {

		Map<String, Object> map = LOCAL.get();

		if (map == null) {
			map = new HashMap<String, Object>();
			LOCAL.set(map);
		}

		map.put(key, value);
	}

	/**
	 * 删除
	 * 
	 * @Title remove
	 * @param key
	 * @return void
	 * @date 2017-05-30 01:03:05 星期二
	 */
	public static void remove(String key) {

		Map<String, Object> map = LOCAL.get();

		if (map == null) {
			return;
		}

		map.remove(key);
	}

	/**
	 * 获取
	 * 
	 * @Title get
	 * @param key
	 * @return
	 * @return Object
	 * @date 2017-05-30 01:03:50 星期二
	 */
	public static Object get(String key) {

		Map<String, Object> map = (Map<String, Object>) LOCAL.get();

		if (null == map) {
			return null;
		}

		return map.get(key);

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
	 * 获取当前线程用户-同session中保存的用户
	 * 
	 * @Title getUser
	 * @return
	 * @return User
	 * @date 2017-05-30 01:04:05 星期二
	 */
	public static User getUser() {

		Object object = get("user");

		if (null == object) {
			throw new SessionLostRuntimeException("用户未登录，请重新登录！");
		}

		return (User) object;

	}

	/**
	 * 获取当前线程用户类别
	 * 
	 * @Title getUserType
	 * @return
	 * @return String
	 * @date 2017-05-30 01:04:05 星期二
	 */
	public static String getUserType() {

		return getUser().getType();

	}

	/**
	 * 获取当前线程用户名
	 * 
	 * @Title getUserName
	 * @return
	 * @return String
	 * @date 2017-05-30 01:05:11 星期二
	 */
	public static String getUserName() {

		return getUser().getName();

	}

	/**
	 * 获取当前线程用户id
	 * 
	 * @Title getUserId
	 * @return
	 * @return String
	 * @date 2017-05-30 01:05:24 星期二
	 */
	public static String getUserId() {

		return getUser().getUserId();

	}

}
