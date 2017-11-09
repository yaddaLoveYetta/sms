package com.kingdee.eas.hrp.sms.controller.system;

import com.kingdee.eas.hrp.sms.model.SysProfile;
import com.kingdee.eas.hrp.sms.service.api.sys.ISysParamService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yadda
 */
@Controller
@RequestMapping(value = "/sys/")
public class SysParamController {

	@Resource
	private ISysParamService sysParamService;

	/**
	 * 获取所有系统参数
	 */
	@RequestMapping(value = "getSysParams")
	public void getSysParams(HttpServletRequest request, HttpServletResponse response) {
		List<SysProfile> sysParams = sysParamService.getSysParams();
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, sysParams);
	}

	/**
	 * 获取单个参数
	 */
	@RequestMapping(value = "getSysParam")
	public void getSysParam(HttpServletRequest request, HttpServletResponse response) {

		String category = ParameterUtils.getParameter(request, "category", ""); // category
		String key = ParameterUtils.getParameter(request, "key", ""); // key

		SysProfile sysParam = sysParamService.getSysParam(category, key);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, sysParam);

	}

	/**
	 * 设置参数的值
	 */
	@RequestMapping(value = "setSysParam")
	public void setSysParam(HttpServletRequest request, HttpServletResponse response) {

		String category = ParameterUtils.getParameter(request, "category", ""); // category
		String key = ParameterUtils.getParameter(request, "key", ""); // key
		String value = ParameterUtils.getParameter(request, "value", ""); // value

		sysParamService.setSysParam(category, key, value);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS);

	}

}
