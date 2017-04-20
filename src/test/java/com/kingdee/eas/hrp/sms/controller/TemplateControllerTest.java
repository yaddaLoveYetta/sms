package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class TemplateControllerTest extends BaseControllerTest {

	@Test
	public void getFormTemplate() {

		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("classId", "1001");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "template/getFormTemplate",param);

		System.out.println(ret);

	}
}
