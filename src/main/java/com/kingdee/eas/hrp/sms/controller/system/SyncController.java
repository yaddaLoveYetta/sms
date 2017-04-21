package com.kingdee.eas.hrp.sms.controller.system;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
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
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/")
public class SyncController {

	@Resource
	ISyncService syncService;

	public boolean checkAdminUser(HttpServletRequest request) {
		// if (SessionUtil.getUserType(request) != 50810) {
		// // 非系统工作人员，禁止操作
		// throw new BusinessLogicRunTimeException("非系统工作人员，禁止操作");
		// }
		return true;
	}

	// 同步供应商资料
	@RequestMapping(value = "supplier")
	public void smsSupplier(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.supplier(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询供应商
	@RequestMapping(value = "getSupplierList")
	public void getSupplierList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getSupplierList(pageNum, pageSize);
	}

	// 同步分类
	@RequestMapping(value = "category")
	public void smsCategory(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.category(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询分类
	@RequestMapping(value = "getCategoryList")
	public void getCategoryList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		List<Category> list = syncService.getCategoryList(pageNum, pageSize);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, list);
	}

	// 同步证书
	@RequestMapping(value = "certificate")
	public void smsCertificate(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.certificate(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询证书
	@RequestMapping(value = "getCertificateList")
	public void getCertificateList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getCertificateList(pageNum, pageSize);
	}

	// 同步行业
	@RequestMapping(value = "industry")
	public void smsIndustry(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.industry(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询行业
	@RequestMapping(value = "getIndustryList")
	public void getIndustryList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getIndustryList(pageNum, pageSize);
	}

	// 同步币别
	@RequestMapping(value = "currency")
	public void smsCurrency(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.currency(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询币种
	@RequestMapping(value = "getCurrencyList")
	public void getCurrencyList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getCurrencyList(pageNum, pageSize);
	}

	// 同步结算方式
	@RequestMapping(value = "settlement")
	public void smsSettlement(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.settlement(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询结算方式
	@RequestMapping(value = "getSettlementList")
	public void getSettlementList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getSettlementList(pageNum, pageSize);
	}

	// 同步付款方式
	@RequestMapping(value = "pay")
	public void smsPay(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.pay(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询付款方式
	@RequestMapping(value = "getPayList")
	public void getPayList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getPayList(pageNum, pageSize);
	}

	// 同步物料
	@RequestMapping(value = "item")
	public void smsItem(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.item(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询物料
	@RequestMapping(value = "getItemList")
	public void getItemList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getItemList(pageNum, pageSize);
	}

	// 同步税种
	@RequestMapping(value = "taxCategory")
	public void smsTaxCategory(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		checkAdminUser(request);

		String size = ParameterUtils.getParameter(request, "size", "");
		String list = ParameterUtils.getParameter(request, "list", "");
		JSONArray array = JSONArray.parseArray(list);
		Map<String, JSONObject> data = syncService.taxCategory(array);

		if (data.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "error", data);
		}
	}

	// 查询税种
	@RequestMapping(value = "getTaxCategoryList")
	public void getTaxCategoryList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		syncService.getTaxCategoryList(pageNum, pageSize);
	}

}
