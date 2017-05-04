package com.kingdee.eas.hrp.sms.service.order;


import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.BaseJunitTest;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.util.Environ;

public class OrderServiceTest extends BaseJunitTest {
	@Test
	public void orderTest() {
		String ss = "{\"entry\":[{\"material_code\":333},{\"material_code\":222}],\"id\":111}";
		IOrderService mapper = Environ.getBean(IOrderService.class);
		JSONObject json=JSONObject.parseObject(ss);
		 mapper.order(json);
	}

}