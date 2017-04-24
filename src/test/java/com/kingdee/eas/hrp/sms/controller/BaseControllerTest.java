package com.kingdee.eas.hrp.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import com.alibaba.fastjson.JSON;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public abstract class BaseControllerTest {

	protected static String BASE_URL = "http://127.0.0.1:8080/sms/";
	private static String LOGIN_URL = BASE_URL + "user/login";
	private static String user = "test";
	private static String pwd = "202cb962ac59075b964b07152d234b70"; // 123
	private static String type = "1"; // 管理后台

	protected Map<String, String> cookie = new HashMap<>();

	@Before
	public void getCookies() {

		HttpParam hp = HttpParam.init();

		hp.addCommon("user", user);
		hp.addCommon("pwd", pwd);
		hp.addCommon("type", type);

		Map<String, String> cookies = HttpUtil.sendPostForCookie(LOGIN_URL, hp);

		cookie = cookies;

	}

	public static void main(String[] args) {

		String ss = "[{\"number\":\"RMB\",\"name\":\"人民币\",\"id\":1},{\"number\":\"USD\",\"name\":\"美元\",\"id\":2}]";

		List<Currency> list = JSON.parseArray(ss, Currency.class);
		System.out.println(list);
	}

}
