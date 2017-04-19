package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void getUserList() {

		Map<String, String> commonParams = new HashMap<>();
		commonParams.put("pageNum", "1");
		commonParams.put("pageSize", "10");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "user/getUserList", param);

		System.out.println(ret);

	}

	@Test
	public void getSidebar() {

		HttpParam param = HttpParam.init();

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "user/getSidebar", param);

		System.out.println(ret);

	}
}
