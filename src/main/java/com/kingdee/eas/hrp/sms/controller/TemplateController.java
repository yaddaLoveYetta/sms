package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.authority.AccessMaskCode;
import com.kingdee.eas.hrp.sms.authority.Permission;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
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
	@ControllerLog(desc = "查看", classId = 0)
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_VIEW, desc = "查看")
	public void getItems(HttpServletRequest request, HttpServletResponse response) {

		int classId = ParameterUtils.getParameter(request, "classId", -1);
		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件json
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段json
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);
		String userType = SessionUtil.getUserType(request);
		String userId = SessionUtil.getUserId(request);

		if (classId < 0) {
			throw new BusinessLogicRunTimeException("参数错误：必须提交classId");
		}

		// sqlserver ROW_BUMBER分页一定要设置 orderBy--此处指定默认

		Map<String, Object> result = templateService.getItems(classId, condition, orderBy, pageNo, pageSize, userType, userId);

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
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_VIEW, desc = "查看")
	public void getItemById(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1); // 业务类别代码
		String id = ParameterUtils.getParameter(request, "id", ""); // 内码
		String userType = SessionUtil.getUserType(request);
		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}
		if (id == null || id.equals("")) {
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
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_ADD, desc = "新增")
	public void addItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String data = ParameterUtils.getParameter(request, "data", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (data.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交data");
			return;
		}

		String id = templateService.addItem(classId, data, userType);

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("id", id);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, ret);

	}

	/**
	 * 修改基础资料
	 * 
	 * @Title editItem
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-04-27 14:09:59 星期四
	 */
	@RequestMapping(value = "editItem")
	@ControllerLog(desc = "新增")
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_EDIT, desc = "修改")
	public void editItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String id = ParameterUtils.getParameter(request, "itemId", "");
		String data = ParameterUtils.getParameter(request, "data", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}
		if (null == id || "".equals(id)) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}
		if (data.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交data");
			return;
		}

		templateService.editItem(classId, id, data, userType);

		ResponseWriteUtil.output(response, "修改成功！");

	}

	/**
	 * 删除基础资料
	 * 
	 * @Title delItem
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-05-23 16:09:51 星期二
	 */
	@RequestMapping(value = "delItem")
	@ControllerLog(desc = "删除")
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_DELETE, desc = "删除")
	public void delItem(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classID");
			return;
		}

		if (items.length() == 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交items");
			return;
		}

		templateService.delItem(classId, items, userType);

		ResponseWriteUtil.output(response, "删除成功！");

	}

	/**
	 * 审核基础资料
	 * 
	 * @Title checkItem
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-05-23 16:10:07 星期二
	 */
	@RequestMapping(value = "checkItem")
	@ControllerLog(desc = "审核")
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_CHECK, desc = "审核")
	public void checkItem(HttpServletRequest request, HttpServletResponse response) {
		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (items.length() == 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交items");
			return;
		}

		templateService.checkItem(classId, items, userType);

		ResponseWriteUtil.output(response, "审核成功！");
	}

	/**
	 * 反审核基础资料
	 * 
	 * @Title unCheckItem
	 * @param request
	 * @param response
	 *            void
	 * @date 2017-05-23 16:10:07 星期二
	 */
	@RequestMapping(value = "unCheckItem")
	@ControllerLog(desc = "反审核")
	@Permission(objectType = 0, objectId = 0, accessMask = AccessMaskCode.MASK_UNCHECK, desc = "反审核")
	public void unCheckItem(HttpServletRequest request, HttpServletResponse response) {
		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (items.length() == 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交items");
			return;
		}

		templateService.unCheckItem(classId, items, userType);

		ResponseWriteUtil.output(response, "反审核成功！");
	}

	@RequestMapping(value = "delItemByHRP")
	public void delItemByHRP(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = SessionUtil.getUserType(request);

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classID");
			return;
		}

		if (items.length() == 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交items");
			return;
		}

		templateService.delItem(classId, items, userType);
		ResponseWriteUtil.output(response, "删除成功！");

	}

}
