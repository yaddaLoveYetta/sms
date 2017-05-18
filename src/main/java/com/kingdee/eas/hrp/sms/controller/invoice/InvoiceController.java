package com.kingdee.eas.hrp.sms.controller.invoice;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.invoice.InvoiceService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;

@Controller
@RequestMapping(value = "/invoice/")
public class InvoiceController {
	
	@Resource
	IInvoiceService invoiceService;
	
	@ControllerLog(desc = "添加发货单记录") // 做日志
	@RequestMapping(value = "addInvoice")
	public void addInvoice(HttpServletRequest request, HttpServletResponse response) {
		String list = ParameterUtils.getParameter(request, "list", "");
		invoiceService.addInvoice(list);
	}

}
