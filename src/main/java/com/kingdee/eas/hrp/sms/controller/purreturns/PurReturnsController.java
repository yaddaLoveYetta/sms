package com.kingdee.eas.hrp.sms.controller.purreturns;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.purreturns.IPurReturnsService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/purreturns/")
public class PurReturnsController {
		
	@Resource
	IPurReturnsService iPurReturnsService;
	
	@ControllerLog(desc = "同步退货单") // 做日志
	@RequestMapping(value = "purreturns")
	public void purreturns(HttpServletRequest request, HttpServletResponse response) {
		String listStr = ParameterUtils.getParameter(request, "list", "");
		JSONArray json = JSONArray.parseArray(listStr);
		String ret = iPurReturnsService.purreturns(json);
		if (ret.equals("success")) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "同步成功");
		}
	}
}
