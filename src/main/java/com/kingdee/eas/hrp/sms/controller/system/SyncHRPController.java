package com.kingdee.eas.hrp.sms.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/hrp/")
public class SyncHRPController {

	@Resource
	ISyncHRPService syncHRPService;

	@ControllerLog(desc = "同步item") // 做日志
	@Permission(objectType = 0, objectId = 0, accessMask = 256, desc = "同步item") // 权限
	@RequestMapping(value = "sendItem")
	public void sendItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = "QpXq24FxxE6c3lvHMPyYCxACEAI=";

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (items.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}

		String result = (String) syncHRPService.sendItem(classId, items, userType);
		if ("".equals(result)) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功！");
			return;
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, result+"同步失败！");
			return;
		}
	}
	
	@ControllerLog(desc = "删除item") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "删除item") // 权限
	@RequestMapping(value = "delItem")
	public void delItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String data = ParameterUtils.getParameter(request, "data", "");
		String userType = "QpXq24FxxE6c3lvHMPyYCxACEAI=";

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (data.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}

		String result = (String) syncHRPService.delItem(classId, data, userType);
		if ("" == result) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功！");
			return;
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, result+"同步失败！");
			return;
		}
	}
}