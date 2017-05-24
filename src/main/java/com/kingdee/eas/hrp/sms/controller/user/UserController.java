package com.kingdee.eas.hrp.sms.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Resource
	private IUserService userService;

	/**
	 * 根据用户角色权限获取菜单列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getSidebar")
	public void getSidebar(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			throw new BusinessLogicRunTimeException("用户信息错误，请重新登陆！");
		}

		String type = user.getType();
		String userId = user.getUserId();

		// 获取用户菜单
		List<Map<String, Object>> sidebars = userService.getSysMenu(type, userId);

		if (sidebars == null || sidebars.size() == 0) {
			throw new BusinessLogicRunTimeException("用户无任何权限，请先联系管理员为你配置权限!");
		}

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, sidebars);

		return;

	}

	/**
	 * 修改密码<br>
	 * 业务逻辑：<br>
	 * 用户提交的验证码正确且手机号码未其注册时预留的手机号码才可以重置密码
	 * 
	 * @param request
	 *            user:用户名 <br>
	 *            oldPwd:原密码 newPwd:新密码 MD5加密<br>
	 * @param response
	 */
	@RequestMapping(value = "editPwd")
	public void editPwd(HttpServletRequest request, HttpServletResponse response) {

		String userId = ParameterUtils.getParameter(request, "userId", "");// 用户名
		String oldpwd = ParameterUtils.getParameter(request, "oldpwd", "");// 原密码
		String newpwd = ParameterUtils.getParameter(request, "newpwd", "");// 新密码

		if (userService.editPwd(userId, oldpwd, newpwd)) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "密码修改成功，请退出重新登录！");
			return;
		} else
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "原密码错误！");

		return;

	}

	public void getUserByToken(HttpServletRequest request, HttpServletResponse response){
		
	}
}
