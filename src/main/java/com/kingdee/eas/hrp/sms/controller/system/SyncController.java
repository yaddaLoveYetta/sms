package com.kingdee.eas.hrp.sms.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.model.Unit;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/")
public class SyncController {

	@Resource
	ISyncService syncService;
	
	@ControllerLog(desc = "同步单位") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步单位") // 权限
	@RequestMapping(value = "unit")
	public void unit(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Unit> list = JSONObject.parseArray(listStr, Unit.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.unit(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步item") // 做日志
	@RequestMapping(value = "sync")
	public void sync(HttpServletRequest request, HttpServletResponse response) {

		int classId = ParameterUtils.getParameter(request, "classId", -1); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		JSONArray list = JSON.parseArray(listStr);

		// 基本参数校验
		if (classId <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数classId");
		}
		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.sync(classId, list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}

	}

}
