package com.kingdee.eas.hrp.sms.util;

import com.kingdee.eas.hrp.sms.model.SysProfile;
import com.kingdee.eas.hrp.sms.service.api.sys.ISysParamService;

/**
 * spring容器启动后，初始化系统参数
 * 
 * @author Administrator
 *
 */
public class SystemParamUtil {

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static Object get(String category, String key) {

		ISysParamService sysParamService = Environ.getBean(ISysParamService.class);
		SysProfile sysParam = sysParamService.getSysParam(category, key);

		if (sysParam != null ) {
			return sysParam.getValue();
		}
		
		return null;

	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static Integer getInt(String category, String key) {

		Object object = get(category, key);
		if (object != null) {
			return Integer.parseInt(object.toString());
		}
		return null;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Integer getInt(String category, String key, int defaultValue) {

		Object object = get(category, key);
		if (object != null) {
			return Integer.parseInt(object.toString());
		}
		return defaultValue;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static Long getLong(String category, String key) {

		Object object = get(category, key);
		if (object != null) {
			return Long.parseLong(object.toString());
		}
		return null;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Long getLong(String category, String key, long defaultValue) {

		Object object = get(category, key);
		if (object != null) {
			return Long.parseLong(object.toString());
		}
		return defaultValue;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static String getString(String category, String key) {
		Object object = get(category, key);
		if (object != null) {
			return object.toString();
		}
		return null;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static String getString(String category, String key, String defaultValue) {
		Object object = get(category, key);
		if (object != null) {
			return object.toString();
		}
		return defaultValue;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static Float getFloat(String category, String key) {
		Object object = get(category, key);
		if (object != null) {
			return Float.parseFloat(object.toString());
		}
		return null;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Float getFloat(String category, String key, float defaultValue) {
		Object object = get(category, key);
		if (object != null) {
			return Float.parseFloat(object.toString());
		}
		return defaultValue;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @return
	 */
	public static Double getDouble(String category, String key) {
		Object object = get(category, key);
		if (object != null) {
			return Double.parseDouble(object.toString());
		}
		return null;
	}

	/**
	 * 获取系统参数值
	 * 
	 * @param category
	 *            参数分类：通常按照模块来区分
	 * @param key
	 *            参数关键字
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Double getDouble(String category, String key, double defaultValue) {
		Object object = get(category, key);
		if (object != null) {
			return Double.parseDouble(object.toString());
		}
		return defaultValue;
	}

}
