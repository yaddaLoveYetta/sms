package com.kingdee.eas.hrp.sms.controller.order;

import java.util.List;
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
        String ret = orderservice.order(json);
        if (ret.equals("success")) {
            ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
        }
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

    /**
     * 采购订单发货<br/>
     * <p>
     * 选择一张或多张订单，根据业务规则，生成一张发货单数据返回前端
     *
     * @param request
     * @param response void
     * @Title deliver
     * @date 2017-05-19 23:56:27 星期五
     */
    @ControllerLog(desc = "采购订单发货")
    @RequestMapping(value = "deliver")
    public void deliver(HttpServletRequest request, HttpServletResponse response) {

        String items = ParameterUtils.getParameter(request, "items", ""); // 订单内码集合，多个订单内码用逗号分隔
        if ("".equals(items.trim())) {
            throw new BusinessLogicRunTimeException("参数错误：请选择需要发货的订单!");
        }

        Map<String, Object> shipOrder = orderservice.deliver(items);
        ResponseWriteUtil.output(response, StatusCode.SUCCESS, shipOrder);

    }

    @ControllerLog(desc = "HRP->SMS接单确认") // 做日志
    @RequestMapping(value = "updateTickType")
    public void updateTickType(HttpServletRequest request, HttpServletResponse response) {
        String listStr = ParameterUtils.getParameter(request, "list", "");
        JSONObject json = JSONObject.parseObject(listStr);
        String ret = orderservice.updateTickType(json);
        if (ret.equals("success")) {
            ResponseWriteUtil.output(response, StatusCode.SUCCESS, "修改订单接单状态成功");
        } else {
            ResponseWriteUtil.output(response, StatusCode.PARAMETER_IS_NOT_EXIST, "参数为空或参数不存在");
        }
    }

}