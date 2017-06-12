package com.kingdee.eas.hrp.sms.controller.sendcargo;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.sendcargo.SendcargoService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/sendcargo/")
public class SendcargoController {
	
	@Resource
	IInvoiceService invoiceService;
	
	@ControllerLog(desc = "获取个体码")
	@RequestMapping(value = "getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {

		String items = ParameterUtils.getParameter(request, "items", ""); // 订单内码集合，多个订单内码用逗号分隔

		if ("".equals(items.trim())) {
			throw new BusinessLogicRunTimeException("参数错误：请选择需要发货的订单!");
		}

		List<Map<String, Object>> shipInvoice = invoiceService.getCode(items);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, shipInvoice);

	}

}