package com.kingdee.eas.hrp.sms.controller.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.api.report.IReportService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;

@Controller
@RequestMapping(value = "/report/")
public class ReportController {

	@Resource
	IReportService reportService;

	@ControllerLog(desc = "订单统计", classId = 0)
	@RequestMapping(value = "orderCount")
	public void orderCount(HttpServletRequest request, HttpServletResponse response) {

		String itemId = ParameterUtils.getParameter(request, "material", "");
		String orderStartDate = ParameterUtils.getParameter(request, "beginDate", "");
		String orderEndDate = ParameterUtils.getParameter(request, "endDate", "");
		String supplier = ParameterUtils.getParameter(request, "supplier", "");
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);

		if (!(null == SessionUtil.getUserLinkSupplier() || "".equals(SessionUtil.getUserLinkSupplier())))
			supplier = SessionUtil.getUserLinkSupplier();

		if (itemId.equals("") && supplier.equals("") && orderStartDate.equals("") && orderEndDate.equals("")) {
			throw new BusinessLogicRunTimeException("必须提交参数");
		}

		JSONObject result = reportService.getRecord(itemId, supplier, orderStartDate, orderEndDate, pageNo, pageSize);
		ResponseWriteUtil.output(response, result);
	}

	@ControllerLog(desc = "订单追踪查询")
	@RequestMapping(value = "traceQuery")
	public void traceQuery(HttpServletRequest request, HttpServletResponse response) {
		String supplierIds = "";
		String pageNo = ParameterUtils.getParameter(request, "pageNo", "");
		String pageSize = ParameterUtils.getParameter(request, "pageSize", "");
		String classId = ParameterUtils.getParameter(request, "classId", "");
		String supplier = ParameterUtils.getParameter(request, "supplier", "");
		String order = ParameterUtils.getParameter(request, "order", "");
		String beginDate = ParameterUtils.getParameter(request, "beginDate", "");
		String endDate = ParameterUtils.getParameter(request, "endDate", "");
		if (SessionUtil.getUserType().equals("B3sMo22ZLkWApjO/oEeDOxACEAI=")) {
			if (!(null == SessionUtil.getUserLinkSupplier() || "".equals(SessionUtil.getUserLinkSupplier()))) {
				supplierIds = SessionUtil.getUserLinkSupplier();
			}
		}
		IOrderService orderService = Environ.getBean(IOrderService.class);

		JSONObject shipSendcargo = orderService.traceQuery(supplierIds, pageNo, pageSize, classId, supplier, order, beginDate, endDate);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, shipSendcargo);
	}

	/**
	 * 物料证件综合查询
	 * 
	 * @Title itemLicenseQuery
	 * @param request
	 * @param response
	 * @return void
	 * @date 2017-09-21 15:35:06 星期四
	 */
	@RequestMapping(value = "getItemLicense")
	public void getItemLicense(HttpServletRequest request, HttpServletResponse response) {

		String itemNumber = ParameterUtils.getParameter(request, "itemNumber", "");// 物料编码
		String itemName = ParameterUtils.getParameter(request, "itemName", "");// 物料名称
		String itemModel = ParameterUtils.getParameter(request, "itemModel", "");// 物料规格

		String manufacturer = ParameterUtils.getParameter(request, "manufacturer", "");// 生产厂家
		String idNumber = ParameterUtils.getParameter(request, "idNumber", "");// 证件编码
		String idName = ParameterUtils.getParameter(request, "idName", "");// 注册证名称
		String idType = ParameterUtils.getParameter(request, "idType", "");// 证书类别
		String authOrg = ParameterUtils.getParameter(request, "authOrg", "");// 发证机关
		String agent = ParameterUtils.getParameter(request, "agent", "");// 代理商
		String supplier = ParameterUtils.getParameter(request, "supplier", "");// 供应商
		int soonExpired = ParameterUtils.getParameter(request, "soonExpired", -1);// 即将过期
		int expired = ParameterUtils.getParameter(request, "expired", -1);// 已过期

		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);

		Map<String, Object> params = new HashMap<String, Object>();

		if (!itemNumber.isEmpty()) {
			params.put("itemNumber", itemNumber);
		}
		if (!itemName.isEmpty()) {
			params.put("itemName", itemName);
		}
		if (!itemModel.isEmpty()) {
			params.put("itemModel", itemModel);
		}
		if (!manufacturer.isEmpty()) {
			params.put("manufacturer", manufacturer);
		}
		if (!idNumber.isEmpty()) {
			params.put("idNumber", idNumber);
		}
		if (!idName.isEmpty()) {
			params.put("idName", idName);
		}
		if (!idType.isEmpty()) {
			params.put("idType", idType);
		}
		if (!authOrg.isEmpty()) {
			params.put("authOrg", authOrg);
		}
		if (!agent.isEmpty()) {
			params.put("agent", agent);
		}
		if (!supplier.isEmpty()) {
			params.put("supplier", supplier);
		}
		if (soonExpired == 1) {
			params.put("soonExpired", soonExpired);
			//params.put("PromptLeadDay", SystemParamUtil.getInt("sys", "Prompt-lead-day", 0));
		}
		
		params.put("PromptLeadDay", SystemParamUtil.getInt("sys", "Prompt-lead-day", 0));// 用来获取有效期天数与设定提示提前天数关系
		
		if (expired == 1) {
			params.put("expired", expired);
		}

		params.put("pageSize", pageSize);
		params.put("pageNo", pageNo);

		Map<String, Object> ret = reportService.getItemLicense(params);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, ret);
	}

	public static void main(String[] args) {
		String s[] = new String[4];

		s[0] = "dfsdf";
		System.out.println(s[0]);

		String[] s2 = new String[1];
		s2[0] = "dfdsfdfdf";
		System.out.println(s2[0]);

	}

}