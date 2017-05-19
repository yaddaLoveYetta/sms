package com.kingdee.eas.hrp.sms.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdee.eas.hrp.sms.util.Common;

/**
 * Http调用二次封装，对外提供简约的Http调用
 * 
 * @ClassName HttpUtil
 * @author yadda
 * @date 2017-04-18 20:10:08 星期二
 */
public final class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String sendGet(String url) {

		return sendGet(url, HttpParam.init());
	}

	public static Map<String, Object> sendGetForMap(String url) {

		return sendGetForMap(url, HttpParam.init());
	}

	/**
	 * 封装sendGet方法
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return
	 */

	public static String sendGet(String url, HttpParam hp) {

		HttpClient http = null;
		try {

			if (hp.hasCommom()) {

				List<NameValuePair> list = new ArrayList<NameValuePair>();

				for (Map.Entry<String, String> entry : hp.getCommonParams().entrySet()) {
					list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

				String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));

				url = url + "?" + params;
			}

			http = new DefaultHttpClient();

			HttpGet get = new HttpGet(url);

			logger.debug("send get with url:" + url);

			if (hp.hasCookie()) {
				// 设置cookie内容

				StringBuilder cookies = new StringBuilder();

				for (Map.Entry<String, String> entry : hp.getCookieParams().entrySet()) {
					cookies.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
				}

				get.setHeader("cookie", cookies.toString());
				logger.debug("add cookie:" + cookies.toString());
			}

			if (hp.hasHeader()) {
				for (Map.Entry<String, String> entry : hp.getHeaderParams().entrySet()) {
					get.setHeader(entry.getKey(), entry.getKey());
					logger.debug("add header:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			HttpEntity entity = http.execute(get).getEntity();

			return EntityUtils.toString(entity, "utf-8").trim();

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			http.getConnectionManager().shutdown();
		}

	}

	/**
	 * 封装sendGet方法
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return Map
	 */
	@SuppressWarnings("resource")
	public static Map<String, Object> sendGetForMap(String url, HttpParam hp) {

		HttpClient http = null;
		try {
			if (hp.hasCommom()) {

				List<NameValuePair> list = new ArrayList<NameValuePair>();

				for (Map.Entry<String, String> entry : hp.getCommonParams().entrySet()) {
					list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

				String params;

				params = EntityUtils.toString(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));

				url = url + "?" + params;
			}

			http = new DefaultHttpClient();

			HttpGet get = new HttpGet(url);
			logger.debug("send get with url:" + url);

			if (hp.hasCookie()) {
				// 设置cookie内容

				StringBuilder cookies = new StringBuilder();

				for (Map.Entry<String, String> entry : hp.getCookieParams().entrySet()) {
					cookies.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
				}

				get.setHeader("cookie", cookies.toString());
				logger.debug("add cookie:" + cookies.toString());
			}

			if (hp.hasHeader()) {
				for (Map.Entry<String, String> entry : hp.getHeaderParams().entrySet()) {
					get.setHeader(entry.getKey(), entry.getKey());
					logger.debug("add header:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			HttpEntity entity = http.execute(get).getEntity();

			return Common.toHashMap(EntityUtils.toString(entity, "utf-8").trim());

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			http.getConnectionManager().shutdown();
		}

	}

	/**
	 * 封装sendPost方法
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return string
	 */

	public static String sendPost(String url, HttpParam hp) {

		HttpClient http = new DefaultHttpClient();

		try {

			HttpPost post = new HttpPost(url);
			logger.debug("send post with url:" + url);

			if (hp.hasCookie()) {
				// 设置cookie内容
				StringBuilder cookies = new StringBuilder();
				for (Map.Entry<String, String> entry : hp.getCookieParams().entrySet()) {
					cookies.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
				}
				post.setHeader("cookie", cookies.toString());
				logger.debug("add cookie:" + cookies.toString());
			}

			if (hp.hasHeader()) {
				for (Map.Entry<String, String> entry : hp.getHeaderParams().entrySet()) {
					post.setHeader(entry.getKey(), entry.getKey());
					logger.debug("add header:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			List<NameValuePair> paramList = new ArrayList<NameValuePair>();

			if (hp.hasCommom()) {
				for (Map.Entry<String, String> entry : hp.getCommonParams().entrySet()) {
					paramList.add(new org.apache.http.message.BasicNameValuePair(entry.getKey(), entry.getValue()));
					logger.debug("add param:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			post.setEntity(new UrlEncodedFormEntity(paramList, Charset.forName("UTF-8")));

			HttpEntity entity = http.execute(post).getEntity();

			return EntityUtils.toString(entity, "utf-8").trim();

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			http.getConnectionManager().shutdown();
		}

	}

	/**
	 * 封装sendPost方法
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return Map
	 */
	public static Map<String, Object> sendPostForMap(String url, HttpParam hp) {

		HttpClient http = new DefaultHttpClient();

		try {

			HttpPost post = new HttpPost(url);
			logger.debug("send post with url:" + url);

			if (hp.hasCookie()) {
				// 设置cookie内容
				StringBuilder cookies = new StringBuilder();
				for (Map.Entry<String, String> entry : hp.getCookieParams().entrySet()) {
					cookies.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
				}
				post.setHeader("cookie", cookies.toString());
				logger.debug("add cookie:" + cookies.toString());
			}

			if (hp.hasHeader()) {
				for (Map.Entry<String, String> entry : hp.getHeaderParams().entrySet()) {
					post.setHeader(entry.getKey(), entry.getKey());
					logger.debug("add header:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			List<NameValuePair> paramList = new ArrayList<NameValuePair>();

			if (hp.hasCommom()) {
				for (Map.Entry<String, String> entry : hp.getCommonParams().entrySet()) {
					paramList.add(new org.apache.http.message.BasicNameValuePair(entry.getKey(), entry.getValue()));
					logger.debug("add param:" + entry.getKey() + "=" + entry.getValue());
				}
			}

			post.setEntity(new UrlEncodedFormEntity(paramList, Charset.forName("UTF-8")));

			HttpEntity entity = http.execute(post).getEntity();

			return Common.toHashMap(EntityUtils.toString(entity, "utf-8").trim());

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			http.getConnectionManager().shutdown();
		}

	}

	/**
	 * 封装sendPost方法
	 * 
	 * @param url
	 *            目的地址
	 * @param param
	 *            请求参数
	 * @return string
	 */
	public static String sendPost(String url, String param) {

		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;

		try {
			String requestUrl = url;
			// 创建URL对象
			java.net.URL connUrl = new java.net.URL(requestUrl);
			// 打开URL连接
			java.net.HttpURLConnection http = (java.net.HttpURLConnection) connUrl.openConnection();
			// 设置POST方式
			http.setRequestMethod("POST");
			// 发送post请求必须允许输出
			http.setDoOutput(true);
			http.setDoInput(true);

			http.setReadTimeout(30 * 1000);
			http.setConnectTimeout(20 * 1000);
			// 设置通用属性
			http.setRequestProperty("Accept", "*/*");
			http.setRequestProperty("Connection", "Keep-Alive");
			http.setRequestProperty("Charset", "utf-8");
			http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");

			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(http.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw new RuntimeException("sendPost:" + e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}

		return result;
	}

	/**
	 * 封装sendPost方法
	 * 
	 * @param url
	 *            目的地址
	 * @param param
	 *            请求参数
	 * @return string
	 */
	public static Map<String, Object> sendPostForMap(String url, String param) {

		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;

		try {
			String requestUrl = url;
			// 创建URL对象
			java.net.URL connUrl = new java.net.URL(requestUrl);
			// 打开URL连接
			java.net.HttpURLConnection http = (java.net.HttpURLConnection) connUrl.openConnection();
			// 设置POST方式
			http.setRequestMethod("POST");
			// 发送post请求必须允许输出
			http.setDoOutput(true);
			http.setDoInput(true);

			http.setReadTimeout(30 * 1000);
			http.setConnectTimeout(20 * 1000);
			// 设置通用属性
			http.setRequestProperty("Accept", "*/*");
			http.setRequestProperty("Connection", "Keep-Alive");
			http.setRequestProperty("Charset", "utf-8");
			http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");

			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(http.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8")); // 读取响应输入流

			String result = "";// 返回的结果
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}

			return Common.toHashMap(result);

		} catch (Exception e) {
			throw new RuntimeException("sendPost:" + e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}

	}

	/**
	 * 封装sendGet方法,返回Cookie，通常是登录接口调用
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return
	 */
	public static Map<String, String> sendPostForCookie(String url, HttpParam hp) {

		Map<String, String> ret = new HashMap<>();

		// ==================================================================
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

		// 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
		PostMethod postMethod = new PostMethod(url);

		if (hp.hasCookie()) {
			// 设置cookie内容
			StringBuilder cookies = new StringBuilder();
			for (Map.Entry<String, String> entry : hp.getCookieParams().entrySet()) {
				cookies.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
			}
			postMethod.setRequestHeader("cookie", cookies.toString());
			logger.debug("add cookie:" + cookies.toString());
		}

		// 请求头
		if (hp.hasHeader()) {
			for (Map.Entry<String, String> entry : hp.getHeaderParams().entrySet()) {
				postMethod.setRequestHeader(entry.getKey(), entry.getValue());
				logger.debug("add header:" + entry.getKey() + "=" + entry.getValue());
			}
		}

		// body参数
		org.apache.commons.httpclient.NameValuePair[] nameValuePairs = {};
		if (hp.hasCommom()) {
			for (Map.Entry<String, String> entry : hp.getCommonParams().entrySet()) {

				postMethod.setParameter(entry.getKey(), entry.getValue());
			}
		}

		try {
			// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
			client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			client.executeMethod(postMethod);
			// 获得登陆后的 Cookie
			Cookie[] cookies = client.getState().getCookies();

			for (Cookie cookie : cookies) {
				ret.put(cookie.getName(), cookie.getValue());
			}

		} catch (Exception e) {
			postMethod.releaseConnection();
			System.out.println(e.getMessage());
		}
		return ret;

		// =================================================================================

	}
	
	/**
	 * 封装sendGet方法
	 * 
	 * @param url
	 *            请求地址
	 * @param hp
	 *            参数
	 * @return
	 */

	public static String doGetRequest(String url) {

		HttpClient http = null;
		try {
			http = new DefaultHttpClient();

			HttpGet get = new HttpGet(url);

			logger.debug("send get with url:" + url);

			HttpEntity entity = http.execute(get).getEntity();

			return EntityUtils.toString(entity, "utf-8").trim();

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			http.getConnectionManager().shutdown();
		}

	}
}
