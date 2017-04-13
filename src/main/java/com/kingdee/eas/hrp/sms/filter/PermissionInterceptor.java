package com.kingdee.eas.hrp.sms.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.bbpark.service.platform.IPermissionService;
//import com.bbpark.service.platform.ITokenService;
//import com.bbpark.util.Environ;
//import com.bbpark.util.ParameterUtils;
//import com.bbpark.util.ResponseWriteUtil;
//import com.bbpark.util.StatusCode;
//import com.bbpark.util.wxUtil.WeChatUtil;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(PermissionInterceptor.class);

	public Map<String, String> allowUrls;// 不拦截的资源
	public Map<String, String> client;// 车场客户端，车场版app拦截做token验证
	public Map<String, String> app;// 有位停车app拦截做token验证
	public Map<String, String> wechat;// 有位停车公众号拦截

	public void setAllowUrls(Map<String, String> allowUrls) {
		this.allowUrls = allowUrls;
	}

	public void setClient(Map<String, String> client) {
		this.client = client;
	}

	public void setApp(Map<String, String> app) {
		this.app = app;
	}

	public void setWechat(Map<String, String> wechat) {
		this.wechat = wechat;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		
		return super.preHandle(request, response, handler);
		
		// // 判断用户是否登陆，未登陆用户不允许访问
		// String requestUrl =
		// request.getRequestURI().replace(request.getContextPath(), "");
		//
		// ITokenService tokenService = Environ.getBean(ITokenService.class);
		//
		// if (null != allowUrls && allowUrls.containsKey(requestUrl)) {
		//
		// return true;
		// }
		//
		// if (null != client && client.containsKey(requestUrl)) {
		// // 验证客户端及车场版token合法性
		//
		// String token = ParameterUtils.getParameter(request, "token", "");
		//
		// Integer parkId = tokenService.checkToken(13002, token);
		//
		// if (parkId == null || parkId == 0) {
		// ResponseWriteUtil.output(response, StatusCode.ACCESS_TOKEN_INVALID,
		// "token无效或过期!");
		// return false;
		// }
		//
		// request.getSession(true).setAttribute("parkId", parkId); //
		// session中保存下用户parkId
		//
		// return true;
		// }
		//
		// if (null != app && app.containsKey(requestUrl)) {
		// // 验证app token合法性
		// String token = ParameterUtils.getParameter(request, "token", "");
		//
		// //ios登录时session中appUserId始终为空，android登录不为空
		// //要判断“在另一台设备上登录”，需要根据token来判断，而不能从session中取
		// /*Integer appUserId = (Integer)
		// request.getSession().getAttribute("appUserId");
		//
		// if (appUserId != null) {
		// return true;
		// }*/
		//
		// Integer appUserId = tokenService.checkToken(13006, token);
		//
		// if (appUserId == null || appUserId == 0) {
		// ResponseWriteUtil.output(response, StatusCode.ACCESS_TOKEN_INVALID,
		// "token无效或过期!");
		// return false;
		// }
		//
		// request.getSession(true).setAttribute("appUserId", appUserId); //
		// session中保存下用户appUserId
		//
		// return true;
		// }
		//
		// if (null != wechat && wechat.containsKey(requestUrl)) {
		// // 获取用户的openId并放入session中
		//
		// String code = ParameterUtils.getParameter(request, "code", "");
		// String openId = (String) request.getSession().getAttribute("openId");
		//
		// if (openId != null) {
		// return true;
		// }
		// if (openId == null && "".equals(code)) {
		// // 缺少微信用户身份信息-openId
		// ResponseWriteUtil.output(response, 10001, "获取微信用户信息失败,请重试！");
		// return false;
		// }
		//
		// openId = WeChatUtil.getOpenID(code);
		// request.getSession(true).setAttribute("openId", openId); //
		// session中保存下用户openId
		//
		// return true;
		// }
		//
		// @SuppressWarnings("unchecked")
		// Map<String, Object> user = (Map<String, Object>)
		// request.getSession().getAttribute("user");
		//
		// if (user == null) {
		// ResponseWriteUtil.output(response, 10000, "会话结束请重新登陆!");
		// return false;
		// }
		//
		// if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
		//
		// Permission permission = ((HandlerMethod)
		// handler).getMethodAnnotation(Permission.class);
		//
		// // 没有声明需要权限,或者声明不验证权限
		// if (permission == null || permission.checkPermission() == false)
		// return true;
		// else {
		// // 权限判断
		// int objectType = permission.objectType();
		// int objectID = permission.objectID();
		// int accessMask = permission.accessMask();
		// String desc = permission.desc();
		//
		// IPermissionService permissionService =
		// Environ.getBean(IPermissionService.class);
		//
		// if
		// (permissionService.checkPermission(Integer.parseInt(user.get("userID").toString()),
		// objectType,
		// objectID, accessMask)) {
		// // 有权限
		// return true;
		// } else {
		// // 无权限
		// if (desc.endsWith("")) {
		// desc = "没有该权限，请联系管理员!";
		// } else {
		// desc = "没有[" + desc + "]权限，请联系管理员!";
		// }
		// ResponseWriteUtil.output(response, 500, desc);
		// return false;
		// }
		// }
		// } else {
		// return super.preHandle(request, response, handler);
		// }
	}
}
