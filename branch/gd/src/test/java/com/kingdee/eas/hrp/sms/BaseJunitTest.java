package com.kingdee.eas.hrp.sms;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

@RunWith(SMSSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring-mvc.xml", "classpath:/config/applicationContext.xml" })
public class BaseJunitTest {

	
	private static final SerializeConfig SC = new SerializeConfig();

	static {

		SC.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		String str = "{'item':'','orderStartDate':'2017-06-16 00:00:00.000','orderEndDate':'','supplier':''}";
		JSONObject json= JSONObject.parseObject(str);
		String jsonString = JSONObject.toJSONString(json, SC, SerializerFeature.WriteDateUseDateFormat);
		System.out.println(jsonString);
	}
}
