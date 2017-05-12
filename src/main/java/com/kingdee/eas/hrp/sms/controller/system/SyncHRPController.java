package com.kingdee.eas.hrp.sms.controller.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.StringEncoder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.mybatis.generator.api.dom.xml.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.TemplateService;
import com.kingdee.eas.hrp.sms.service.impl.sys.SyncHRPService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/hrp/")
public class SyncHRPController {

	@Resource
	ITemplateService templateService;
	@Resource
	ISyncHRPService syncHRPService;

	@ControllerLog(desc = "同步item") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步item") // 权限
	@RequestMapping(value = "sendItem")
	@SuppressWarnings("unchecked")
	public void sendItem(HttpServletRequest request, HttpServletResponse response) {

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

		Map<String, String> result = syncHRPService.sendItem1(classId, data, userType);

		InputStream is = null;

		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod("http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade/sms2hrpSupplier");

		method.setRequestHeader("Host", "http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade");

		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		method.setParameter("size", result.get("size"));
		method.setParameter("list", result.get("list"));
		method.setParameter("classId", String.valueOf(classId));

		try {

			client.executeMethod(method);

			is = method.getResponseBodyAsStream();

			// Document document = Jsoup.parse(is, "gbk", "");

			// System.err.println(document);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
			try {

				if (is != null) {
					is.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}