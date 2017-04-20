package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;

/**
 * 操作系统单据模板
 * 
 * @ClassName TemplateController
 * @author yadda
 * @date 2017-04-20 10:30:13 星期四
 */
@Controller
@RequestMapping(value = "/template/")
public class TemplateController {

	@Resource
	private ITemplateService templateService;

	/**
	 * 查询基础资料
	 * 
	 * @param request
	 *            type:基础资料类别
	 * @param response
	 */
	@RequestMapping(value = "getFormTemplate")
	public void getFormTemplate(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);

		if (classId < 0) {
			throw new BusinessLogicRunTimeException("参数错误：必须提交classId");
		}

		Map<String, Object> result = templateService.getFormTemplate(classId, 0);

		ResponseWriteUtil.output(response, result);

	}

}
