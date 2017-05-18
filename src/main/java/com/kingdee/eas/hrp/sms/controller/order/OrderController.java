package com.kingdee.eas.hrp.sms.controller.order;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
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
	@RequestMapping(value = "tick")
	public void tick(HttpServletRequest request, HttpServletResponse response) {

		String id = ParameterUtils.getParameter(request, "id", "");
		String entry = ParameterUtils.getParameter(request, "entry", "");

		// 基本的参数校验
		if ("".equals(id) || "".equals(entry)) {
			throw new BusinessLogicRunTimeException("请选择订单进行接单操作！");
		}

		orderservice.tick(id, entry);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS);

		// String listStr = ParameterUtils.getParameter(request, "list", "");
		// JSONObject json = JSONObject.parseObject(listStr);
		// orderservice.updatetickType(json);
	}

	@ControllerLog(desc = "产生发货单")
	@RequestMapping(value = "invoice")
	public void invoice(HttpServletRequest request, HttpServletResponse response) {
		String items = ParameterUtils.getParameter(request, "items", "");
		String userType = SessionUtil.getUserType(request);
		if (items.equals("")) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交id");
			return;
		}
		Map<String, Object> result = orderservice.invoice(items, userType);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, result);
	}
}
