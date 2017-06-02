package com.kingdee.eas.hrp.sms.controller.purinwarehs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.purinwarehs.IPurInWarehsService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/purinwarehs/")
public class PurInWarehsController {
	
	@Resource
	IPurInWarehsService iPurInWarehsService;
	
	@ControllerLog(desc = "同步入库单") // 做日志
	@RequestMapping(value = "purInWarehs")
	public void purInWarehs(HttpServletRequest request, HttpServletResponse response) {
		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONArray json = JSONArray.parseArray(listStr);
		String ret = iPurInWarehsService.purInWarehsService(json);
		if (ret.equals("success")) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		}
	}
}
