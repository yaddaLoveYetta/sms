package com.kingdee.eas.hrp.sms.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.authority.AccessMaskCode;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/hrp/")
public class SyncHRPController {

	@Resource
	ISyncHRPService syncHRPService;

	@ControllerLog(desc = "同步到HRP") // 做日志
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_SYNC, desc = "同步item") // 权限
	@RequestMapping(value = "sendItem")
	public void sendItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (items.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}

		String result = (String) syncHRPService.sendItem(classId, items);
		if ("".equals(result)) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功！");
			return;
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, result+"同步失败！");
			return;
		}
	}
	
}