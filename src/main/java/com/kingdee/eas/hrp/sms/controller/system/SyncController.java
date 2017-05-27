package com.kingdee.eas.hrp.sms.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Session;
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
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.City;
import com.kingdee.eas.hrp.sms.model.Country;
import com.kingdee.eas.hrp.sms.model.County;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.Pay;
import com.kingdee.eas.hrp.sms.model.Province;
import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.model.Supplier_License_Type;
import com.kingdee.eas.hrp.sms.model.TaxCategory;
import com.kingdee.eas.hrp.sms.model.Unit;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sync/")
public class SyncController {

	@Resource
	ISyncService syncService;

	@ControllerLog(desc = "同步币别") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步币别") // 权限
	@RequestMapping(value = "currency")
	public void currency(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Currency> list = JSONObject.parseArray(listStr, Currency.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.currency(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步分类") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步分类") // 权限
	@RequestMapping(value = "category")
	public void category(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Category> list = JSONObject.parseArray(listStr, Category.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.category(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步证书") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步证书") // 权限
	@RequestMapping(value = "certificate")
	public void certificate(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Certificate> list = JSONObject.parseArray(listStr, Certificate.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.certificate(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步证书分类") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步证书分类") // 权限
	@RequestMapping(value = "license")
	public void license(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Supplier_License_Type> list = JSONObject.parseArray(listStr, Supplier_License_Type.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.license(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步行业") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步行业") // 权限
	@RequestMapping(value = "industry")
	public void industry(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Industry> list = JSONObject.parseArray(listStr, Industry.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.industry(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步结算方式") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步结算方式") // 权限
	@RequestMapping(value = "settlement")
	public void settlement(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Settlement> list = JSONObject.parseArray(listStr, Settlement.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.settlement(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步付款方式") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步付款方式") // 权限
	@RequestMapping(value = "pay")
	public void pay(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Pay> list = JSONObject.parseArray(listStr, Pay.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.pay(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步物料") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步物料") // 权限
	@RequestMapping(value = "item")
	public void item(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Item> list = JSONObject.parseArray(listStr, Item.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.item(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步税种") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步税种") // 权限
	@RequestMapping(value = "taxCategory")
	public void taxCategory(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<TaxCategory> list = JSONObject.parseArray(listStr, TaxCategory.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.taxCategory(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步供应商资料") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步供应商资料") // 权限
	@RequestMapping(value = "supplier")
	public void supplier(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Supplier> list = JSONObject.parseArray(listStr, Supplier.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.supplier(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步国家") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步国家") // 权限
	@RequestMapping(value = "country")
	public void country(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Country> list = JSONObject.parseArray(listStr, Country.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.country(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步城市") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步城市") // 权限
	@RequestMapping(value = "city")
	public void city(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<City> list = JSONObject.parseArray(listStr, City.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.city(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步省份") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步省份") // 权限
	@RequestMapping(value = "province")
	public void province(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<Province> list = JSONObject.parseArray(listStr, Province.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.province(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

	@ControllerLog(desc = "同步区县") // 做日志
	@Permission(objectType = 130, objectId = 01, accessMask = 4, desc = "同步区县") // 权限
	@RequestMapping(value = "county")
	public void county(HttpServletRequest request, HttpServletResponse response) {

		int size = ParameterUtils.getParameter(request, "size", 0); // 提交同步的记录数
		String listStr = ParameterUtils.getParameter(request, "list", ""); // 提交同步的数据

		// 基本参数校验
		if (size <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数size");
		}
		if (listStr.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}
		List<County> list = JSONObject.parseArray(listStr, County.class);

		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.county(list);

		// 如果返回的数据为空，设置成功code，返回代data为空，反之设置错误消息，返回相关错误data
		if (ret.isEmpty()) {
			// 全部同步成功
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		} else {
			// 有同步失败记录-返回同步失败，客户端解析失败原因
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, "同步失败，请查看失败原因", ret);
		}
	}

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

		String userType ="QpXq24FxxE6c3lvHMPyYCxACEAI=";

		JSONArray list = JSON.parseArray(listStr);

		// 基本参数校验
		if (classId <= 0) {
			throw new BusinessLogicRunTimeException("必须提交参数classId");
		}
		if (list.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}

		List<Map<String, Object>> ret = syncService.sync(classId, list, userType);

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
