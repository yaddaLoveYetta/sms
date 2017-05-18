package com.kingdee.eas.hrp.sms.service.api.order;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IOrderService {

	public String order(JSONArray jsonarray);

	/**
	 * 采购订单接单
	 * 
	 * @Title tick
	 * @param id
	 *            订单id
	 * @param entry
	 *            接单数据
	 * @return String
	 * @date 2017-05-17 20:19:53 星期三
	 */
	public void tick(String id, String entry);

	Map<String, Object> invoice(String data, String userType);

}
