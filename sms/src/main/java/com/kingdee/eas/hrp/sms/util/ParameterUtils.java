
package com.kingdee.eas.hrp.sms.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: ParameterUtils
 * @Description: 获取请求参数通用类
 * @author yadda
 * @date 2017年4月13日 下午2:36:24
 *
 */
public class ParameterUtils {

	private ParameterUtils() {

	}

	private static String getParameter(HttpServletRequest request, String parameterName) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("获取参数出错", e);
		}
		String para = request.getParameter(parameterName);
		if (para != null && para.equals("undefined"))
			return null;
		else
			return para;
	}

	public static Object getAttribute(HttpServletRequest request, String parameterName) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("获取参数出错", e);
		}
		return request.getAttribute(parameterName);
	}

	public static String getParameter(HttpServletRequest request, String parameterName, String defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null) {
			return value;
		}
		return defaultValue;
	}

	public static Integer getParameter(HttpServletRequest request, String parameterName, Integer defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null && !value.equals("")) {
			return Integer.parseInt(value);
		}
		return defaultValue;
	}

	public static Long getParameter(HttpServletRequest request, String parameterName, Long defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null && !value.equals("")) {
			Long.parseLong(value);
		}
		return defaultValue;
	}

	public static Float getParameter(HttpServletRequest request, String parameterName, Float defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null && !value.equals("")) {
			return Float.parseFloat(value);
		}
		return defaultValue;
	}

	public static Double getParameter(HttpServletRequest request, String parameterName, Double defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null && !value.equals("")) {
			return Double.parseDouble(value);
		}
		return defaultValue;
	}

	public static byte[] getParameter(HttpServletRequest request, String parameterName, byte[] defaultValue) {

		String value = getParameter(request, parameterName);

		if (value != null) {
			return value.getBytes();
		}
		return defaultValue;
	}

	/**
	 * 获取所有请求参数
	 * 
	 * @param request
	 * @return Map<String, Object>
	 */
	public static Map<String, String> getParameters(HttpServletRequest request) {

		Map<String, String> params = new HashMap<String, String>();

		Enumeration<String> paramEnumeration = request.getParameterNames();

		while (paramEnumeration.hasMoreElements()) {

			String key = paramEnumeration.nextElement();
			String value = request.getParameter(key);

			params.put(key, value);
		}

		return params;

	}

	public static Object getAttribute(HttpServletRequest request, String parameterName, Object defaultValue) {
		Object value = getAttribute(request, parameterName);

		if (value != null) {
			return value;
		}
		return defaultValue;
	}
}
