package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;

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

	/**
	 * 通过模板获取业务数据
	 * 
	 * @Title getItems
	 * @param request
	 * @param response
	 * @return void
	 * @date 2017-04-20 13:41:06 星期四
	 */
	@RequestMapping(value = "getItems")
	public void getItems(HttpServletRequest request, HttpServletResponse response) {

		int classId = ParameterUtils.getParameter(request, "classId", -1);
		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件json
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段json
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);
		int userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			throw new BusinessLogicRunTimeException("参数错误：必须提交classId");
		}

		// sqlserver ROW_BUMBER分页一定要设置 orderBy--此处指定默认
		if (orderBy.equals("")) {

			JSONArray orderByArray = new JSONArray();
			JSONObject orderByItem = new JSONObject(true);

			orderByItem.put("fieldKey", "number");
			orderByItem.put("orderDirection", "ASC");
			orderByArray.add(orderByItem);

			orderByItem = new JSONObject();
			orderByItem.put("fieldKey", "name");
			orderByItem.put("orderDirection", "ASC");
			orderByArray.add(orderByItem);

			orderBy = JSON.toJSONString(orderByArray);

		}

		Map<String, Object> result = templateService.getItems(classId, condition, orderBy, pageNo, pageSize, userType);

		ResponseWriteUtil.output(response, result);

	}

}
