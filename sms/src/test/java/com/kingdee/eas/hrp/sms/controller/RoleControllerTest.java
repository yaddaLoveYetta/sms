package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class RoleControllerTest extends BaseControllerTest {

	@Test
	public void getRolePermissions() {

		Map<String, String> commonParams = new HashMap<>();
		commonParams.put("type", "1");
		commonParams.put("roleId", "1");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "role/getRolePermissions", param);

		System.out.println(ret);

	}
}
