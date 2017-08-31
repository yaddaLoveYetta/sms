/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * @author Sunny
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncHRPControllerTest extends BaseControllerTest {



	@Test
	public void sendItem() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		param.addCommon("classId", "1005");
		param.addCommon("items", "wqNYbozcQG6XOSIOzGV3ozfGffw=,,");

		String ret = HttpUtil.sendGet(BASE_URL + "sync/hrp/sendItem", param);
		System.out.println(ret);
	}
	
	@Test
	public void delItem() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		param.addCommon("classId", "1005");
		param.addCommon("items", "wqNYbozcQG6XOSIOzGV3ozfGffw=,,");

		String ret = HttpUtil.sendGet(BASE_URL + "sync/hrp/delItem", param);
		System.out.println(ret);
	}
}
