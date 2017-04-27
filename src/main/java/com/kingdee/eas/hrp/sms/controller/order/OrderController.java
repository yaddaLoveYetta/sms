package com.kingdee.eas.hrp.sms.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.service.impl.order.OrderService;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {
	@Resource
	OrderService orderservice;
	
	@RequestMapping(value = "AcquisitionOrder")
	public void certificate(HttpServletRequest request, HttpServletResponse response,JSONArray orderArray) {
		orderservice.order(orderArray);
	}
}
