package com.kingdee.eas.hrp.sms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey.On;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void getUserList() {

		Map<String, String> commonParams = new HashMap<>();
		commonParams.put("pageNum", "1");
		commonParams.put("pageSize", "10");

		HttpParam param = HttpParam.init();
		param.setCommonParams(commonParams);

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "user/getUserList", param);

		System.out.println(ret);

	}

	@Test
	public void addUser() {

		String url = BASE_URL + "user/addUser";

		HttpParam hp = HttpParam.init();

		// hp.addCommon("classId", "1001");
		// hp.addCommon("data",
		// "{number:'a35',name:'测试增加1',enable:1,username:'a454',password:'av1235',FCreateTime:'2015-09-15 10:02:13'}");

		Map<String, Object> params = new HashMap<>();

		params.put("classId", 1001);

		Map<String, Object> data = new HashMap<>();
		data.put("number", "a35");
		data.put("name", "测试增加");
		data.put("username", "a454");
		data.put("password", "av1235");

		params.put("data", data);

		hp.addCommon("classId", params.get("classId").toString());
		hp.addCommon("data", params.get("data").toString());

		System.out.println(JSON.toJSONString(params));

		String ss = HttpUtil.sendGet(url, hp);

		System.out.println(ss);

	}

	@Test
	public void getSidebar() {

		HttpParam param = HttpParam.init();

		param.setCookieParams(cookie);

		Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL + "user/getSidebar", param);

		System.out.println(ret);

	}
	// extends BaseControllerTest

	public static void main(String[] args) {

		Map<String, Object> order = new HashMap<>();

		order.put("orderId", 111);
		order.put("orderNo", "12344");

		List<Map<String, Object>> entry = new ArrayList<>();

		Map<String, Object> entryItem = new HashMap<>();

		entryItem.put("itemId", 333);
		entryItem.put("qty", 20);
		entryItem.put("price", 5.5);

		entry.add(entryItem);

		Map<String, Object> entryItem2 = new HashMap<>();

		entryItem2.put("itemId", 222);
		entryItem2.put("qty", 10);
		entryItem2.put("price", 2.2);

		entry.add(entryItem2);

		order.put("entry", entry);

		JSONObject json = JSONObject.parseObject(JSON.toJSONString(order));

		System.err.println(json.getInteger("orderId"));

		JSONArray entry2 = json.getJSONArray("entry");

		JSONObject entryItem1 = JSONObject.parseObject(JSON.toJSONString(entry2.get(0)));

		entryItem1.getFloat("price");
		
		
		System.err.println(JSON.toJSONString(order));

	}
}
