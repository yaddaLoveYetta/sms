/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.Supplier_License_Type;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * @author Sunny
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncControllerTest extends BaseControllerTest {

	public void getList(String type) {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		param.addCommon("pageNum", "1");
		param.addCommon("pageSize", "10");

		String ret = HttpUtil.sendGet(BASE_URL + "sync/getCategoryList", param);
		System.out.println(ret);
	}

	@Test
	public void a_categoryTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'categoryId':,'name':'分类1','number':'12345'},{'categoryId':123,'name':'分类2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/category", param);
		System.out.println(ret);
	}

	@Test
	public void b_categoryTest() {

		getList("getCategegoryList");
	}

	@Test
	public void a_certificateTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'certificateId':'','name':'','number':'12345'},{'certificateId':456,'name':'证书3','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/certificate", param);
		System.out.println(ret);
	}

	@Test
	public void b_certificateTest() {

		getList("getCertificateList");
	}

	@Test
	public void a_industryTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'id':123,'name':'行业1','number':'12345'},{'id':456,'name':'行业2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/industry", param);
		System.out.println(ret);
	}

	@Test
	public void b_industryTest() {

		getList("getIndustryList");
	}

	// @Test
	// public void a_currencyTest() {
	//
	// HttpParam param = HttpParam.init();
	// param.setCookieParams(cookie);
	// String str =
	// "[{'currencyId':123,'currencyName':'分类','number':'12345'},{'currencyId':123,'currencyName':'分类','number':'12345'}]";
	// param.addCommon("size", "2");
	// param.addCommon("list", str);
	//
	// String ret = HttpUtil.sendGet(BASE_URL + "sync/currency", param);
	// System.out.println(ret);
	// }

	@Test
	public void b_currencyTest() {

		getList("getCurrencyList");
	}

	@Test
	public void a_settlementTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'id':123,'name':'结算方式1','number':'12345'},{'id':456,'name':'结算方式2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/settlement", param);
		System.out.println(ret);
	}

	@Test
	public void b_settlementTest() {

		getList("getSettlementList");
	}

	@Test
	public void a_payTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'payId':123,'name':'付款方式1','number':'12345'},{'payId':456,'name':'付款方式2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/pay", param);
		System.out.println(ret);
	}

	@Test
	public void b_payTest() {

		getList("getPayList");
	}

	@Test
	public void a_itemTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'itemId':123,'name':'物料1','number':'12345'},{'itemId':456,'name':'物料2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/item", param);
		System.out.println(ret);
	}

	@Test
	public void b_itemTest() {

		getList("getItemList");
	}

	@Test
	public void a_taxCategoryTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'taxCategoryId':123,'name':'税种1','number':'12345'},{'taxCategoryId':456,'name':'税种2','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/taxCategory", param);
		System.out.println(ret);
	}

	@Test
	public void b_taxCategoryTest() {

		getList("getTaxCategoryList");
	}

	@Test
	public void a_supplierTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'id':123,'categoryId':'','certificateId':'123','industryId':'123','currencyId':'123','settlementId':'123','payId':'123','itemId':'123','taxCategoryId':'123','number':'12345'},{'id':456,'categoryId':'123','certificateId':'123','industryId':'123','currencyId':'123','settlementId':'123','payId':'123','itemId':'123','taxCategoryId':'123','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/supplier", param);
		System.out.println(ret);
	}

	@Test
	public void b_supplierTest() {

		getList("getSupplierList");
	}

	@Test
	public void license() {

		String str = "[{'id':123,'name':'license1','number':'12345','isMust':1,'isControl':1,'syncStatus':'1','review':'2'},{'id':456,'name':'license2','number':'6789','isMust':1,'isControl':1,'syncStatus':'1','review':'2'}]";

		List<Supplier_License_Type> list = JSONObject.parseArray(str, Supplier_License_Type.class);

	}

	@Test
	public void sync() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		// String str =
		// "[{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'},{'id':'ZYs36ftiQtm47pC4RgHyujfGffw=','number':'GDS0019','name':'佛山市顺德区龙江镇品派家具厂'}]";
		String str = "[{'id':'uPFUTmqOSRiJuUX9sckOZDBpAwU=','unit':'111111112'}]";
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
