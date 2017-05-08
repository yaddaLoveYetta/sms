package com.kingdee.eas.hrp.sms.controller.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.kingdee.eas.hrp.sms.model.Order;
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
	public void synchronizationOrder(HttpServletRequest request, HttpServletResponse response,JSONObject orderjson) {
		if(orderjson.size()<=0){
			throw new BusinessLogicRunTimeException("没有可同步的数据");
		}else{
			orderservice.order(orderjson);
		}
		
	}
	
	@ControllerLog(desc = "确认接单") 
	@RequestMapping(value = "confirmOrder")
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response) {
		Order order = new Order();
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
		String type = request.getParameter("type");
		try {
			if(request.getParameter("id")!=null){
			if(type.equals("1")){
				order.setId(request.getParameter("id"));
				order.setCutasingleTime(sft.parse(request.getParameter("cutasingleTime")));
				order.setConfirmDeliveryTime(sft.parse(request.getParameter("confirmDeliveryTime")));
				order.setConfirmDeliveryNumbers(Integer.parseInt(request.getParameter("confirmDeliveryNumbers")));
				order.setConfirmOrder(Integer.parseInt(request.getParameter("0")));
				orderservice.updateOrderTime(order);
			}else if(type.equals("2")){
				order.setId(request.getParameter("id"));
				order.setCutasingleTime(sft.parse(request.getParameter("cutasingleTime")));
				order.setConfirmDeliveryTime(sft.parse(request.getParameter("confirmDeliveryTime")));
				order.setConfirmDeliveryNumbers(Integer.parseInt(request.getParameter("confirmDeliveryNumbers")));
				order.setConfirmOrder(Integer.parseInt(request.getParameter("1")));
				orderservice.updateOrderTime(order);
			}
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
}
