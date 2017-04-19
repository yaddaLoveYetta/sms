package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class UserControllerTest {

	@Test
	public void getUserList() {

		Map<String, String> commonParams = new HashMap<>();
		commonParams.put("pageNum", "1");
		commonParams.put("pageSize", "10");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);

		Map<String, Object> ret = HttpUtil.sendGetForMap("http://127.0.0.1:8081/sms/user/getUserList", param);

	}
}
