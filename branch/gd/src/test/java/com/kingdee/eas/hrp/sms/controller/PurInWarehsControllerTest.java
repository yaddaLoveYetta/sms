/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * @author Sunny
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurInWarehsControllerTest extends BaseControllerTest {

	@Test
	public void test() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);

		String str = "[{'sourceBillType':'50957179-0105-1000-e000-0157c0a812fd463ED552','supplier':'xqdc6DHNQVeA5UVbmjiDQzfGffw=','entry':{'1':[{'unit':'CojHwrrRQOGyqSOna54dWVuCXFc=','seq':1,'material':'bHvHLkwGTqGZHncGBOcEEkQJ5/A=','lot':'6-16_26','id':'tCjRLI0yTOyudme1n7a0t44IhhY=','amount':552.0000,'actualQty':12.00000000,'price':46.00000000,'orderId':'Eyj3f7u6RwKfidRzqeUyVjFxv60=','orderSeq':1,'parent':'YYCX67CDTmacwkemPvY/EngwYeM='}]},'bizDate':1497542400000,'baseStatus':1,'id':'YYCX67CDTmacwkemPvY/EngwYeM=','number':'PUI2017000008'}]";
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "/purinwarehs/purInWarehs", param);

		JSONObject object = (JSONObject) JSON.parse(ret);

		Assert.assertEquals(object.getIntValue("code"), 200);
	
	}
}
