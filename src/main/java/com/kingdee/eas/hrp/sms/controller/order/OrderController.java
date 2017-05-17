package com.kingdee.eas.hrp.sms.controller.order;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {
	@Resource
	IOrderService orderservice;

	@ControllerLog(desc = "同步订单") // 做日志
	@RequestMapping(value = "acquisitionOrder")
	public void synchronizationOrder(HttpServletRequest request, HttpServletResponse response) {
		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONArray json = JSONArray.parseArray(listStr);
		orderservice.order(json);
	}

	@ControllerLog(desc = "确认接单")
	@RequestMapping(value = "updatetickType")
	public void updatetickType(HttpServletRequest request, HttpServletResponse response) {

		String id = ParameterUtils.getParameter(request, "id", "");
		String entry = ParameterUtils.getParameter(request, "entry", "");

		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONObject json = JSONObject.parseObject(listStr);
		orderservice.updatetickType(json);
	}

	@ControllerLog(desc = "产生发货单")
	@RequestMapping(value = "invoice")
	public void invoice(HttpServletRequest request, HttpServletResponse response) {
		String list = ParameterUtils.getParameter(request, "list", "");
		String userType = SessionUtil.getUserType(request);
		if (list.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}
		Map<String, Object> result = orderservice.invoice(list, userType);
		request.setAttribute("result", "result");
	}
}
