package com.kingdee.eas.hrp.sms.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.kingdee.eas.hrp.sms.util.StatusCode;

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

	/**
	 * 通过内码获取单个基础资料
	 * 
	 * @Title getItemById
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-04-26 14:39:30 星期三
	 */
	@RequestMapping(value = "getItemById")
	public void getItemById(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1); // 业务类别代码
		Integer id = ParameterUtils.getParameter(request, "id", -1); // 内码
		int userType = SessionUtil.getUserType(request);
		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}
		if (id < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}

		Map<String, Object> result = templateService.getItemById(classId, id, userType);

		if (result == null) {
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "资料不存在！");
			return;
		}

		ResponseWriteUtil.output(response, result);

	}

	/**
	 * 新增基础资料
	 * 
	 * @Title addItem
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-04-27 14:09:59 星期四
	 */
	@RequestMapping(value = "addItem")
	public void addItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String data = ParameterUtils.getParameter(request, "data", "");

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (data.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交data");
			return;
		}

		int id = templateService.addItem(classId, data);
		ResponseWriteUtil.output(response, "新增成功！");

	}

	@RequestMapping(value = "editItem")
	public void editItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		Integer id = ParameterUtils.getParameter(request, "itemId", -1);
		String data = ParameterUtils.getParameter(request, "data", "");

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}
		if (id < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}
		if (data.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交data");
			return;
		}

		templateService.editItem(classId, id, data);

		ResponseWriteUtil.output(response, "修改成功！");

	}

}
