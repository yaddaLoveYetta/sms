package com.kingdee.eas.hrp.sms.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.impl.order.OrderService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {
	@Resource
	OrderService orderservice;
	
	@ControllerLog(desc = "同步订单") // 做日志
	@RequestMapping(value = "acquisitionOrder")
	public void certificate(HttpServletRequest request, HttpServletResponse response,JSONObject orderjson) {
		if(orderjson.size()<=0){
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}else{
			orderservice.order(orderjson);
		}
		
	}
}
