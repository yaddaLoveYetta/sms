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
public class SyncControllerTest extends BaseControllerTest {

	@Test
	public void sync() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		// String str =
		// "[{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'}]";
		String str = "[{'id':'eMCJ/ZVzQKW4opEHYz5FjjBpAwU=','unit':'111111112'}]";
		//String str = "{'number':'2512','taxRate':0E-8,'address':'','cORP':'','taxId':'','review':'true','name':'宁波博硕倍医疗器械有限公司','id':'y02FNd8USnCS9mka7GxOsDfGffw=','bRNO':'','categoryId':'jWIqxazUQOOEIgj9YNKkJXolaaI=','syncStatus':'true','status':'1'}";
		param.addCommon("classId", "3030");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/sync", param);
		System.out.println(ret);
	}

	@Test
	public void sync2() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);

		String str = "[{'categoryId': '5j+2iTRjREKc00hBgnFfB3olaaI=','createOrganization': 'px6r/xY4TU2E3EKF8XOjysznrtQ=','currencyId': 'dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC','id': 'bHKwiwOuRzSzl26sHqA38DfGffw=','name': '甘肃宝信生物科技有限公司','number': '002','status': '1','taxRate': 0}]";
		param.addCommon("classId", "1005");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/sync", param);

		JSONObject object = (JSONObject) JSON.parse(ret);

		Assert.assertEquals(object.getIntValue("code"), 200);
	
	}
}
