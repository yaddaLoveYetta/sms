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

	/**
	 * 采购订单发货<br/>
	 * 
	 * 选择一张或多张订单，根据业务规则，生成一张发货单数据返回前端
	 * 
	 * @Title deliver
	 * @param items
	 *            订单内码集合，多个订单内码用逗号分隔<br/>
	 *            eg: 100,101 表示选择内码为100及101的采购订单合并成一张发货单数据
	 * @param userType
	 *            当前操作用户类别
	 * @param userId
	 *            当前操作用户内码
	 * @return Map<String,Object> 采购订单转换生成的发货单数据
	 * @date 2017-05-19 23:58:15 星期五
	 */
	Map<String, Object> deliver(String items, String userType, String userId);

}
