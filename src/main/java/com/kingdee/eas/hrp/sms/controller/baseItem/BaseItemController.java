package com.kingdee.eas.hrp.sms.controller.baseItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseItemController {

	/**
	 * 新增用户，用户名按流水号生成
	 * 
	 * 生成流水号
	 * 
	 * @Title getEncode
	 * @param request
	 * @param response
	 * @return void
	 * @date 2017-10-11 15:38:57 星期三
	 */
	@RequestMapping(value = "getUserNameEncode")
	public void getUserNameEncode(HttpServletRequest request, HttpServletResponse response) {


	}
}
