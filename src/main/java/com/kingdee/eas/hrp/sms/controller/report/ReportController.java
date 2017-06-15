package com.kingdee.eas.hrp.sms.controller.report;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.statistics.IStatisticsService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;

@Controller
@RequestMapping(value = "/report/")
public class ReportController {

	@Resource
	IStatisticsService statisticsService;

	@ControllerLog(desc = "订单统计", classId = 0)
	@RequestMapping(value = "orderCount")
	public void orderCount(HttpServletRequest request, HttpServletResponse response) {

		String data = ParameterUtils.getParameter(request, "data", "");
		JSONObject json = JSONObject.parseObject(data);
		String itemId = json.getString("item");
		String orderStartDate = json.getString("orderStartDate");
		String orderEndDate = json.getString("orderEndDate");
		String supplier = json.getString("supplier");
		if (!(null == SessionUtil.getUserLinkSupplier() || "".equals(SessionUtil.getUserLinkSupplier())))
			supplier = SessionUtil.getUserLinkSupplier();

		// 基本参数校验
		if (data.equals("")) {
			throw new BusinessLogicRunTimeException("必须提交参数");
		}

		if (itemId.equals("") && supplier.equals("") && orderStartDate.equals("") && orderEndDate.equals("")) {
			throw new BusinessLogicRunTimeException("必须提交参数");
		}

		Map<String, Object> result = statisticsService.getRecord(itemId, supplier, orderStartDate, orderEndDate);

		ResponseWriteUtil.output(response, result);
	}

}