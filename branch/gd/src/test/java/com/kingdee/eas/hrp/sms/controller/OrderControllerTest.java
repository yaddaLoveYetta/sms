package com.kingdee.eas.hrp.sms.controller;


import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class OrderControllerTest extends BaseControllerTest {

	
	@Test
	public void invoice(){
		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "5DwH8qtqSgi57JS4URzj4zFxv60=";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "order/invoice", param);
		System.out.println(ret);
	}
}
