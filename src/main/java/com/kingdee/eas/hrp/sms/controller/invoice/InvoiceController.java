package com.kingdee.eas.hrp.sms.controller.invoice;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.invoice.InvoiceService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/invoice/")
public class InvoiceController {
	
	@Resource
	IInvoiceService invoiceService;
	
	@ControllerLog(desc = "打印个体码")
	@RequestMapping(value = "print")
	public void deliver(HttpServletRequest request, HttpServletResponse response) {

		String items = ParameterUtils.getParameter(request, "items", ""); // 订单内码集合，多个订单内码用逗号分隔

		if ("".equals(items.trim())) {
			throw new BusinessLogicRunTimeException("参数错误：请选择需要发货的订单!");
		}

		Map<String, Object> shipInvoice = invoiceService.print(items);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, shipInvoice);

	}

}
