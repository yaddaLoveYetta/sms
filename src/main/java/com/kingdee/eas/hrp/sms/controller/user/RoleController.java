package com.kingdee.eas.hrp.sms.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.model.AccessControl;
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

		String type = ParameterUtils.getParameter(request, "type", ""); // 角色类别
																		// Ro9iCuOsVEmznmE+YZSi7hAEEAQ=:员工,f1sGInqJq0aUNY5MmpKM8RAEEAQ=:供应商
		String roleId = ParameterUtils.getParameter(request, "roleId", ""); // 角色ID

		if (("").equals(type) || ("").equals(roleId)) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "未提交角色类别[type]或角色ID[roleID]");
			return;
		}

		List<Map<String, Object>> rolePermissions = roleService.getRolePermissions(type, roleId);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, rolePermissions);

	}

	/**
	 * 保存权限设置
	 * 
	 * @Title saveRolePerMissions
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-04-25 16:23:08 星期二
	 */
	@RequestMapping(value = "saveRolePerMissions")
	public void saveRolePerMissions(HttpServletRequest request, HttpServletResponse response) {

		String roleId = ParameterUtils.getParameter(request, "roleId", "");
		String data = ParameterUtils.getParameter(request, "data", "");

		if (data == null || "".equals(data) || "".equals(roleId)) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "提交数据不完整");
			return;
		}

		JSONArray arry = JSONArray.parseArray(data);

		roleService.saveRolePermissions(arry, roleId);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS);

	}
}
