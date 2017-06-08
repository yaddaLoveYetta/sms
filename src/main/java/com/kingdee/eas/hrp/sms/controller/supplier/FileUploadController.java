package com.kingdee.eas.hrp.sms.controller.supplier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.authority.AccessMaskCode;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.TemplateService;
import com.kingdee.eas.hrp.sms.service.impl.supplier.FileUploadService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/file/")
public class FileUploadController {

	@Resource
	IFileUploadService fileUploadService;
	@Resource
	ITemplateService templateService;

	@SuppressWarnings("unchecked")
	@ControllerLog(desc = "上传附件") // 做日志
	// @Permission(objectType = 0, objectId = 0, accessMask =
	// AccessMaskCode.MASK_SYNC, desc = "同步item") // 权限
	@RequestMapping(value = "upload")
	public void sendItem(HttpServletRequest request, HttpServletResponse response) {

//		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
//		String license = ParameterUtils.getParameter(request, "license", "");
		Integer classId=3040;
		String license="0C66D60C-F468-479F-9991-716115F2CABAssssssss";

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		List<String> list = null;
		try {
			list = fileUploadService.upload(request,classId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String path : list) {
			//判断是否有该附件路径已存在，是则不添加新的附近记录
			JSONArray conditionArry = new JSONArray();
			JSONObject condition = new JSONObject(true);
			condition.put("fieldKey", "path");
			condition.put("logicOperator", "=");
			condition.put("value", path);
			conditionArry.add(condition);
			condition = new JSONObject(true);
			condition.put("fieldKey", "license");
			condition.put("logicOperator", "=");
			condition.put("value", license);
			conditionArry.add(condition);
			
			Map<String, Object> items = templateService.getItems(classId, conditionArry.toString(), "", 1, 1000);
			if ((long) items.get("count") > 0)
				continue;
			JSONObject json = new JSONObject(true);
			json.put("license", license);
			json.put("path", path);
			templateService.addItem(classId, json.toString());
		}

		 ResponseWriteUtil.output(response, StatusCode.SUCCESS, "上传成功！");
		 return;
	}

}