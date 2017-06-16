/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * @author Sunny
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticsControllerTest extends BaseControllerTest {



	@Test
	public void delete() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		
		//String str = "{'item':'8a97N4VHRSK7aHJYI9yGtEQJ5/A=','orderStartDate':'2017-06-10','orderEndDate':'2017-06-11','supplier':'C58lu7xvQHqq/KxNTurocTfGffw='}";
		//String str = "{'item':'','orderStartDate':'2017-06-09','orderEndDate':'','supplier':''}";
		//param.addCommon("orderStartDate", "2017-06-09");
		//param.addCommon("item", "8a97N4VHRSK7aHJYI9yGtEQJ5/A=");
		param.addCommon("supplier", "C58lu7xvQHqq/KxNTurocTfGffw=");
		
		String ret = HttpUtil.sendGet(BASE_URL + "statistics/order", param);
		System.out.println(ret);
	}
}
