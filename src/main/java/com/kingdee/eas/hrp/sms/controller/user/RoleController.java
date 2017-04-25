package com.kingdee.eas.hrp.sms.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.service.api.user.IRoleService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/role/")
public class RoleController {

	@Resource
	private IRoleService roleService;

	/**
	 * 获取角色所有权限<br>
	 * 构建一个角色树<br>
	 * 用于编辑角色的权限<br>
	 * 
	 * @param request
	 *            type:角色类别 0:员工,1:供应商
	 * @param response
	 */
	@RequestMapping(value = "getRolePermissions")
	public void getRolePermissions(HttpServletRequest request, HttpServletResponse response) {

		int type = ParameterUtils.getParameter(request, "type", -1); // 角色类别 1:员工,2:供应商
		int roleID = ParameterUtils.getParameter(request, "roleId", -1); // 角色ID

		if (type == -1 || roleID == -1) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "未提交角色类别[type]或角色ID[roleID]");
			return;
		}

		List<Map<String, Object>> rolePermissions = roleService.getRolePermissions(type, roleID);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, rolePermissions);

	}
}
