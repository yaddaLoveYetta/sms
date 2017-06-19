/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * @author Sunny
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticsControllerTest extends BaseControllerTest {

	private static final SerializeConfig SC = new SerializeConfig();

	static {

		SC.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}

	@Test
	public void delete() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);

		// String str =
		// "{'item':'8a97N4VHRSK7aHJYI9yGtEQJ5/A=','orderStartDate':'2017-06-10','orderEndDate':'2017-06-11','supplier':'C58lu7xvQHqq/KxNTurocTfGffw='}";
		// String str =
		// "{'item':'','orderStartDate':'2017-06-09','orderEndDate':'','supplier':''}";
		// param.addCommon("orderStartDate", "2017-06-09");
		// param.addCommon("item", "8a97N4VHRSK7aHJYI9yGtEQJ5/A=");
		param.addCommon("supplier", "C58lu7xvQHqq/KxNTurocTfGffw=");

		String ret = HttpUtil.sendGet(BASE_URL + "/report/orderCount", param);
		System.out.println(ret);
	}

	@Test
	public void test() throws ParseException {
		String str = "{'item':'','orderStartDate':'2017-06-16 00:00:00.000','orderEndDate':'','supplier':''}";
		JSONObject json = JSONObject.parseObject(str);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse("2017-06-16 00:00:00.000");
		json.put("data", date);
		String jsonString = JSONObject.toJSONString(json, SC, SerializerFeature.WriteDateUseDateFormat);
		System.out.println(jsonString);
	}

}
