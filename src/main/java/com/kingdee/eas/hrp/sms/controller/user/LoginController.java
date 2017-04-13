package com.kingdee.eas.hrp.sms.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.service.api.user.ILogin;

@Controller
@RequestMapping(value = "/user/")
public class LoginController {

	@Resource
	private ILogin login;

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String username = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");

		try {
			// 参数校验
			boolean b = login.login(username, password);

			if (b) {
				response.getWriter().write("登录成功");
				return;
			}
		} catch (RuntimeException e) {
			response.getWriter().write(e.getMessage());
		}

	}
}
