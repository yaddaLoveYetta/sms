package com.kingdee.eas.hrp.sms.controller.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.kingdee.eas.hrp.sms.model.Material;
import com.kingdee.eas.hrp.sms.model.Order;
import com.kingdee.eas.hrp.sms.model.OrderEntry;
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
	@RequestMapping(value = "updatetickType")
	public void updatetickType(HttpServletRequest request, HttpServletResponse response) {
		OrderEntry orderEntry = new OrderEntry();
		Order order  = new Order();
		
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
		String type = request.getParameter("type"); 
		try {
			if(type.equals("1")){
				order.setConfirmTick(Byte.parseByte(request.getParameter("0")));	
			}else{
				order.setConfirmTick(Byte.parseByte(request.getParameter("1")));	
			}
			order.setTickTime(new Date());
			orderEntry.setConfirmDate(sft.parse(request.getParameter("ConfirmDate")));
			orderEntry.setConfirmQty(Integer.parseInt(request.getParameter("ConfirmQty")));
			//material.setSupplierMaterialNumber(request.getParameter("supplierMaterialNumber"));
			orderservice.updatetickType(orderEntry,order);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
