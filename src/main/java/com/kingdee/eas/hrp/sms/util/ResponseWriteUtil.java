package com.kingdee.eas.hrp.sms.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * response工具类，向浏览器直接写入数据.数据个格式为{code:200,msg:'success',data:object}
 * 
 * @author yadda
 *
 */
public class ResponseWriteUtil {

	private static Logger logger = Logger.getLogger(ResponseWriteUtil.class);

	private static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.CHINA);

	// private static final JsonConfig config = new JsonConfig();

	// static {
	//
	// config.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
	//
	// @Override
	// public Object processObjectValue(String name, Object value, JsonConfig
	// config) {
	//
	// if (value == null) {
	// return "";
	// } else if (value instanceof Date) {
	// synchronized (sdfDateTime) {
	// return sdfDateTime.format((Date) value);
	// }
	// } else {
	// return value.toString();
	// }
	// }
	//
	// @Override
	// public Object processArrayValue(Object value, JsonConfig config) {
	// if (value == null) {
	// return "";
	// } else {
	// synchronized (sdfDateTime) {
	// return sdfDateTime.format((Date) value);
	// }
	// }
	// }
	// });
	//
	// config.registerJsonValueProcessor(java.sql.Date.class, new
	// JsonValueProcessor() {
	//
	// @Override
	// public Object processObjectValue(String name, Object value, JsonConfig
	// config) {
	//
	// if (value == null) {
	// return "";
	// } else if (value instanceof java.sql.Date) {
	//
	// synchronized (sdfDate) {
	// return sdfDate.format((Date) value);
	// }
	// } else {
	// return value.toString();
	// }
	// }
	//
	// @Override
	// public Object processArrayValue(Object value, JsonConfig config) {
	// if (value == null) {
	// return "";
	// } else {
	// synchronized (sdfDate) {
	// return sdfDate.format((Date) value);
	// }
	// }
	// }
	// });
	//
	// config.registerJsonValueProcessor(java.sql.Timestamp.class, new
	// JsonValueProcessor() {
	//
	// @Override
	// public Object processObjectValue(String name, Object value, JsonConfig
	// config) {
	//
	// if (value == null) {
	// return "";
	// } else if (value instanceof java.sql.Timestamp) {
	// synchronized (sdfDateTime) {
	// return sdfDateTime.format((Date) value);
	// }
	// } else {
	// return value.toString();
	// }
	// }
	//
	// @Override
	// public Object processArrayValue(Object value, JsonConfig config) {
	// if (value == null) {
	// return "";
	// } else {
	// synchronized (sdfDateTime) {
	// return sdfDateTime.format((Date) value);
	// }
	// }
	// }
	// });
	//
	// config.registerJsonValueProcessor(java.sql.Time.class, new
	// JsonValueProcessor() {
	//
	// @Override
	// public Object processObjectValue(String name, Object value, JsonConfig
	// config) {
	// if (value == null) {
	// return "";
	// } else if (value instanceof java.sql.Time) {
	// synchronized (sdfTime) {
	// return sdfTime.format(value);
	// }
	// } else {
	// return value.toString();
	// }
	// }
	//
	// @Override
	// public Object processArrayValue(Object value, JsonConfig config) {
	// if (value == null) {
	// return "";
	// } else {
	// synchronized (sdfTime) {
	// return sdfTime.format((Date) value);
	// }
	// }
	// }
	// });
	// }

	public static void output(HttpServletResponse response, Result r) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("回应数据" + JSON.toJSONString(r));
			}
			response.setContentType("application/json;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin", "*");

			// JSONObject json = JSONObject.fromObject(r, config);

			response.getWriter().write(JSON.toJSONString(r));
			response.getWriter().close();

		} catch (IOException e) {
			logger.error("Exception:", e);
		}
	}

	/**
	 * 返回Jsonp信息
	 * 
	 * @param response
	 * @param callback
	 * @param r
	 */
	public static void output(HttpServletResponse response, String callback, Result r) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("回应数据[" + r + "]");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin", "*");

			// JSONObject json = JSONObject.fromObject(r, config);

			JSONObject json = JSONObject.parseObject(JSON.toJSONString(r));

			String jsonStr = callback + "(" + json.toString() + ")";
			response.getWriter().write(jsonStr);
			response.getWriter().close();

		} catch (IOException e) {
			logger.error("Exception:", e);
		}
	}

	/**
	 * 返回数据对象，默认成功
	 * 
	 * @param response
	 *            HttpServletResponse 对象
	 * @param data
	 *            回应数据
	 */
	public static void output(HttpServletResponse response, Object data) {
		Result r = new Result(data);
		output(response, r);
	}

	/**
	 * 返回代码及数据对象，数据统一被封装到Result.data对象中
	 * 
	 * @param response
	 *            HttpServletResponse 对象
	 * @param code
	 *            返回码
	 * @param data
	 *            回应数据
	 */
	public static void output(HttpServletResponse response, int code, Object data) {
		Result r = new Result(code, data);
		output(response, r);
	}

	/**
	 * 返回代码及消息 eg:output(response,"-1","登陆失败，用户名或密码错误")
	 * 
	 * @param response
	 *            HttpServletResponse 对象
	 * @param code
	 *            返回码
	 * @param message
	 *            回应消息
	 */
	public static void output(HttpServletResponse response, int code, String message) {
		Result r = new Result(code, message);
		output(response, r);
	}

	/**
	 * 返回代码,信息及数据对象，数据统一被封装到Result.data对象中
	 * 
	 * @param response
	 *            HttpServletResponse 对象
	 * @param code
	 *            返回码
	 * @param message
	 *            回应消息
	 * @param data
	 *            回应数据
	 */
	public static void output(HttpServletResponse response, int code, String message, Object data) {
		Result r = new Result(code, message, data);
		output(response, r);
	}

	/**
	 * 返回跨域Ajax请求信息
	 * 
	 * @param response
	 * @param callback
	 * @param code
	 * @param data
	 * @param msg
	 */
	public static void ouputJsonp(HttpServletResponse response, String callback, int code, Object data, String msg) {
		Result r = new Result(code, msg, data);
		output(response, callback, r);
	}

}
