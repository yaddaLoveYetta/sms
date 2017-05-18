package com.kingdee.eas.hrp.sms.controller.invoice;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.impl.invoice.InvoiceService;

@Controller
@RequestMapping(value = "/invoice/")
public class InvoiceController {
	
	@Resource
	InvoiceService invoiceService;
	
	@ControllerLog(desc = "产生发货单") // 做日志
	@RequestMapping(value = "addInvoice")
	public void addInvoice(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

}
