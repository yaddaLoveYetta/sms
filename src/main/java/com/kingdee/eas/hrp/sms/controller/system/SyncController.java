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
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.util.Common;
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
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

	@ControllerLog(desc = "同步币别") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步币别") // 权限
	@RequestMapping(value = "currency2")
	public void currency(HttpServletRequest request, HttpServletResponse response) {
		// 验证是否系统工作人员
		// checkAdminUser(request); // 不需要此逻辑，可通过权限模块控制
		
		//提交的json数据格式
		//		{
		//			'count':2,
		//			'list':[
		//			        	{
		//			        		'id':1, // 币别内码(表主键)
		//			        		'name':'人民币', // 币别名称
		//			        		'number':'RMB', //币别代码
		//			        	},
		//			        	{
		//			        		'id':2,
		//			        		'name':'美元'，
		//			        		'number':'USD',
		//			        	},
		//			        ]
		//		}

		int count = ParameterUtils.getParameter(request, "count", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if(count<=0){
			throw new BusinessLogicRunTimeException("必须提交参数count");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		//listStr=JSONObject.toJSONString(JSONArray.parseArray(listStr),SerializerFeature.WriteClassName);
		
		List<Currency> list=JSON.parseArray(listStr, Currency.class);
		
		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		
		List<Map<String, Object>> ret= syncService.currency(list);
		
		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			//全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, null);
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR,"同步失败，请查看失败原因", ret);
		}
	}

}
