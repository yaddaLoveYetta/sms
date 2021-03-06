package com.kingdee.eas.hrp.sms.controller.report;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.api.statistics.IStatisticsService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/report/")
public class ReportController {

	@Resource
	IStatisticsService statisticsService;

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

		JSONObject result = statisticsService.getRecord(itemId, supplier, orderStartDate, orderEndDate, pageNo,
				pageSize);
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

}