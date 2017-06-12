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
public class FileUploadControllerTest extends BaseControllerTest {



	@Test
	public void delete() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		param.addCommon("classId", "3040");
		param.addCommon("data", "E2B661CB-31A8-43AD-A13D-C1CCCB5A9491ssssssss,F6724851-12CC-46AD-AD15-68A8DE89D559ssssssss");

		String ret = HttpUtil.sendGet(BASE_URL + "file/delete", param);
		System.out.println(ret);
	}
}
