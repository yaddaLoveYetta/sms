package com.kingdee.eas.hrp.sms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		// JSONObject json = new JSONObject();
		//
		// json.put("name", "张三");
		// json.put("age", 18);
		//
		// JSONArray array = new JSONArray();
		//
		// array.add("语文");
		// array.add("数学");
		// array.add("英语");
		//
		// json.put("class", array);
		//
		// System.out.println(json.toJSONString());

		String str = "{\"list\": [{ \"categoryID\": 123,\"categoryName\": \"分类\"},{\"categoryID\": 123,\"categoryName\": \"分类\"},{\"categoryID\": 123,\"categoryName\": \"分类\"}],\"size\": 10}";

		JSONObject json = JSON.parseObject(str);

		int size = json.getInteger("size");

		JSONArray array = json.getJSONArray("list");

		for (Object object : array) {
			JSONObject item = (JSONObject) object;

			item.getIntValue("categoryID");
			item.getString("categoryName");
			
		}
		System.out.println("data.size=" + size);
		System.out.println("data.list=" + array);

	}
}
