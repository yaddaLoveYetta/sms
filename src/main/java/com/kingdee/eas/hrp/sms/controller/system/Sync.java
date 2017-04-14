package com.kingdee.eas.hrp.sms.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/sync/")
public class Sync {

	@RequestMapping(value = "item")
	public void syncItem(HttpServletRequest request, HttpServletResponse response) {

		String str = "{\"list\": [{ \"categoryID\": 123,\"categoryName\": \"分类\"},{\"categoryID\": 123,\"categoryName\": \"分类\"},{\"categoryID\": 123,\"categoryName\": \"分类\"}],\"size\": 10}";

		JSONObject json = JSON.parseObject(str);

		int size = json.getInteger("size");

		JSONArray array = json.getJSONArray("list");

		System.out.println("data.size=" + size);
		System.out.println("data.list=" + array);

	}

}
