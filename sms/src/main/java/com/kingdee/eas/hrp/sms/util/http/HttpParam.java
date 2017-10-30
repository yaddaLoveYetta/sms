package com.kingdee.eas.hrp.sms.util.http;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求参数封装
 * 
 * @ClassName HttpParam
 * @author yadda
 * @date 2017-04-18 20:10:08 星期二
 */
public class HttpParam {

	// 通过http头自定义传输的参数名称定义
	public final static String HEADER_IP = "X-Forwarded-For";
	// 设置http头User-Agent
	public final static String HEADER_User_Agent = "User-Agent";

	// get/post传参
	private Map<String, String> commonParams;
	// cookie传参
	private Map<String, String> cookieParams;
	// header传参
	private Map<String, String> headerParams;

	/**
	 * @return the commonParams
	 */
	public Map<String, String> getCommonParams() {
		return commonParams;
	}

	/**
	 * @param commonParams
	 *            the commonParams to set
	 */
	public void setCommonParams(Map<String, String> commonParams) {
		this.commonParams = commonParams;
	}

	/**
	 * @return the cookieParams
	 */
	public Map<String, String> getCookieParams() {
		return cookieParams;
	}

	/**
	 * @param cookieParams
	 *            the cookieParams to set
	 */
	public void setCookieParams(Map<String, String> cookieParams) {
		this.cookieParams = cookieParams;
	}

	/**
	 * @return the headerParams
	 */
	public Map<String, String> getHeaderParams() {
		return headerParams;
	}

	/**
	 * @param headerParams
	 *            the headerParams to set
	 */
	public void setHeaderParams(Map<String, String> headerParams) {
		this.headerParams = headerParams;
	}

	/**
	 * 私有化构造函数
	 */
	private HttpParam() {

		commonParams = new HashMap<String, String>();
		cookieParams = new HashMap<String, String>();
		headerParams = new HashMap<String, String>();
	}

	/**
	 * 静态调用初始化
	 * 
	 * @return
	 */
	public static HttpParam init() {
		return new HttpParam();
	}

	/**
	 * 是否存在get/post传参参数
	 * 
	 * @return
	 */
	public boolean hasCommom() {
		return !getCommonParams().isEmpty();
	}

	/**
	 * 是否存在cookie传参参数
	 * 
	 * @return
	 */
	public boolean hasCookie() {
		return !getCookieParams().isEmpty();
	}

	/**
	 * 是否存在header传参参数
	 * 
	 * @return
	 */
	public boolean hasHeader() {
		return !getHeaderParams().isEmpty();
	}

	/**
	 * 增加get/post传参参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpParam addCommon(String key, String value) {
		this.getCommonParams().put(key, value);
		return this;
	}

	/**
	 * 增加Cookie传参参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpParam addCookie(String key, String value) {
		this.getCookieParams().put(key, value);
		return this;
	}

	/**
	 * 增加Header传参参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpParam addHeader(String key, String value) {
		this.getHeaderParams().put(key, value);
		return this;
	}
}
