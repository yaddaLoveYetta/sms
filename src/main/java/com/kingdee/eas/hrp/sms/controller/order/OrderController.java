package com.kingdee.eas.hrp.sms.controller.order;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.impl.order.OrderService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {
	@Resource
	OrderService orderservice;
	
	@ControllerLog(desc = "同步订单") // 做日志
	@RequestMapping(value = "acquisitionOrder")
	public void synchronizationOrder(HttpServletRequest request, HttpServletResponse response) {
		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONArray json =  JSONArray.parseArray(listStr);
		orderservice.order(json);
	}
	
	@ControllerLog(desc = "确认接单") 
	@RequestMapping(value = "updatetickType")
	public void updatetickType(HttpServletRequest request, HttpServletResponse response) {
		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONObject json =  JSONObject.parseObject(listStr);
		orderservice.updatetickType(json);
	}
}
