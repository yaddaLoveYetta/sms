package com.kingdee.eas.hrp.sms.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.ILoginService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/user/")
public class LoginController {

	@Resource
	private ILoginService loginService;

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ControllerLog(desc = "用户登录")
	@RequestMapping(value = "login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String username = ParameterUtils.getParameter(request, "user", "");
		String password = ParameterUtils.getParameter(request, "pwd", "");
		String type = ParameterUtils.getParameter(request, "type", "");

		// logger.debug("{},{}", username, password);

		// 参数校验
		if ("".equals(username) || "".equals(password)) {
			throw new BusinessLogicRunTimeException("用户名或密码不能为空!");
		}
		if ("".equals(type)) {
			throw new BusinessLogicRunTimeException("用户类别不能为空！");
		}

		User user = loginService.login(username, password, type);

		if (null != user) {

			// session保存用户信息
			request.getSession(true).setAttribute("user", user);

			ResponseWriteUtil.output(response, StatusCode.SUCCESS, user);

		}

		return;

	}

	@RequestMapping(value = "loginout")
	public void loginout(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().removeAttribute("user");
		ResponseWriteUtil.output(response, StatusCode.SUCCESS);
		logger.info("注销成功!");
		return;

	}

	@RequestMapping(value = "createToken")
	public void createToken(HttpServletRequest request, HttpServletResponse response) {

		String username = ParameterUtils.getParameter(request, "user", "");
		String password = ParameterUtils.getParameter(request, "pwd", "");
		String type = ParameterUtils.getParameter(request, "type", "");

		if (username == "" || password == "") {
			throw new BusinessLogicRunTimeException("用户名或密码不能为空!");
		}
		if ("".equals(type)) {
			throw new BusinessLogicRunTimeException("用户类别不能为空！");
		}

		// 生成token
		User user = loginService.createToken(username, password, type);

		if (null == user) {
			ResponseWriteUtil.output(response, StatusCode.ACCESS_TOKEN_INVALID, "获取token失败，请重试!");
		} else {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, user);
		}

	}
}
