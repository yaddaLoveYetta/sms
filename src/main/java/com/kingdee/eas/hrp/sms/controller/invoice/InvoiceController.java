package com.kingdee.eas.hrp.sms.controller.invoice;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.model.Invoice;
import com.kingdee.eas.hrp.sms.service.impl.invoice.InvoiceService;

@Controller
@RequestMapping(value = "/invoice/")
public class InvoiceController {
	
	@Resource
	InvoiceService invoiceService;
	
	@ControllerLog(desc = "产生发货单") // 做日志
	@RequestMapping(value = "addInvoice")
	public void addInvoice(HttpServletRequest request, HttpServletResponse response) {
		Invoice invoice = new Invoice();
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
		invoice.setOrderId(request.getParameter("orderId"));
		invoice.setOrderlineNumbers(Integer.parseInt(request.getParameter("orderlineNumbers")));
		invoice.setMaterialCode(request.getParameter("materialCode"));
		invoice.setMaterialName(request.getParameter("materialName"));
		invoice.setSpecifications(request.getParameter("specifications"));
		invoice.setBatch(request.getParameter("batch"));
		invoice.setProductBatchNumber(request.getParameter("productBatchNumber"));
		invoice.setIndividualCode(request.getParameter("individualCode"));
		invoice.setPrice(new BigDecimal(request.getParameter("price")));
		invoice.setBasicUnitMeasurement(request.getParameter("basicUnitMeasurement"));
		invoice.setNumbers(Integer.parseInt(request.getParameter("numbers")));
		invoice.setProductionTime(sft.parse(request.getParameter("productionTime")));
		invoice.setProductionManufacturer(request.getParameter("productionManufacturer"));
		invoice.setProductRegistrationNumber(Integer.parseInt(request.getParameter("productRegistrationNumber")));
		invoice.setAmount(new BigDecimal(request.getParameter("amount")));
		invoice.setEffectiveTime(request.getParameter("effectiveTime"));
		invoice.setInvoiceNo(request.getParameter("invoiceNo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		invoiceService.addInvoice(invoice);
		
	}

}
