package com.kingdee.eas.hrp.sms.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

public class LogResFilter implements Filter {

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String ip = getIp(req);

		String agent = req.getHeader("User-Agent");
		/**** 若浏览器类型为空，判断是否app端访问 ****/
		if (agent == null || isMoblie(req)) {
			agent = "APP";
		}
		;
		MDC.put("ip", ip);
		MDC.put("agent", agent);

		if (session == null) {
			MDC.put("userId", "");
			MDC.put("userName", "未登陆用户");
			MDC.put("userType", "-1");
		} else {
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

			if (user != null) {
				MDC.put("userId", user.get("userID").toString());
				MDC.put("userName", user.get("name").toString());
				MDC.put("userType", user.get("type").toString());
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig Config) throws ServletException {

	}

	/**
	 * 判断请求是否来自于手机终端
	 * 
	 * @param request
	 * @return
	 */
	public boolean isMoblie(HttpServletRequest request) {
		String agent = (request.getHeader("User-Agent") + "").toLowerCase().trim();

		if (agent == "" || agent.indexOf("mobile") != -1 || agent.indexOf("mobi") != -1 || agent.indexOf("nokia") != -1
				|| agent.indexOf("samsung") != -1 || agent.indexOf("sonyericsson") != -1 || agent.indexOf("mot") != -1
				|| agent.indexOf("blackberry") != -1 || agent.indexOf("lg") != -1 || agent.indexOf("htc") != -1
				|| agent.indexOf("j2me") != -1 || agent.indexOf("ucweb") != -1 || agent.indexOf("opera mini") != -1
				|| agent.indexOf("mobi") != -1 || agent.indexOf("android") != -1 || agent.indexOf("iphone") != -1) {
			// 终端可能是手机
			return true;

		}

		return false;
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
