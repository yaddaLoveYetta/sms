package com.kingdee.eas.hrp.sms.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

/**
 * 拦截请求
 * 
 * @ClassName SecurityInterceptor
 * @Description 校验请求合法性
 * @author yadda
 * @date 2017-04-15 21:43:12 星期六
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	public Map<String, String> allowUrls;// 不拦截的资源
	public Map<String, String> clientUrls;// 不拦截的资源

	public void setClientUrls(Map<String, String> clientUrls) {
		this.clientUrls = clientUrls;
	}

	public void setAllowUrls(Map<String, String> allowUrls) {
		this.allowUrls = allowUrls;
	}

	/**
	 * 判断用户是否登陆，未登陆用户不允许访问
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @date 2017-04-15 21:42:32 星期六
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

		if (null != allowUrls && allowUrls.containsKey(requestUrl)) {
			// 不拦截的请求
			return super.preHandle(request, response, handler);
		}

		if (null != clientUrls && clientUrls.containsKey(requestUrl)) {
			// HRP调用的业务接口--验证token合法性

			IUserService userService = Environ.getBean(IUserService.class);

			String token = ParameterUtils.getParameter(request, "token", "");

			User user = userService.getUserByToken(token);

			if (null == user) {
				ResponseWriteUtil.output(response, StatusCode.ACCESS_TOKEN_INVALID, "token无效或过期!");
				return false;
			}

			SessionUtil.set("user", user);// 保存下用户信息

			return super.preHandle(request, response, handler);
		}

		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			ResponseWriteUtil.output(response, StatusCode.SESSION_LOST, "会话结束请重新登陆!");
			return false;
		}

		SessionUtil.set("user", user);// 将user放入ThreadLocal中，方便service层调用

		return super.preHandle(request, response, handler);

	}
}
