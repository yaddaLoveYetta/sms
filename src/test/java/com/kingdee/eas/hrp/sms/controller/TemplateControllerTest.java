package com.kingdee.eas.hrp.sms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;
import com.sun.media.jfxmedia.control.VideoDataBuffer;

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
	
	@Test
	public void getItems() throws HttpException, IOException {

		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("classId", "1022");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);
		super.getCookies();
		param.setCookieParams(cookie);
		
		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "template/getItems",param);

		System.out.println(ret);

	}
	
	@Test
	public void delItems(){
		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("classId", "1005");
		commonParams.put("items", "123");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);
		param.setCookieParams(cookie);
		
		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "template/delItem",param);

		System.out.println(ret);
	}
	
	@Test
	public void delItemsByHRP(){
		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("classId", "1005");
		commonParams.put("items", "wqNYbozcQG6XOSIOzGV3ozfGffw=,,");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);
		param.setCookieParams(cookie);
		
		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "template/delItemByHRP",param);

		System.out.println(ret);
	}
}
