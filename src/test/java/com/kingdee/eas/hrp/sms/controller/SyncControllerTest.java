/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

//	@Test
//	public void a_currencyTest() {
//
//		HttpParam param = HttpParam.init();
//		param.setCookieParams(cookie);
//		String str = "[{'currencyId':123,'currencyName':'分类','number':'12345'},{'currencyId':123,'currencyName':'分类','number':'12345'}]";
//		param.addCommon("size", "2");
//		param.addCommon("list", str);
//
//		String ret = HttpUtil.sendGet(BASE_URL + "sync/currency", param);
//		System.out.println(ret);
//	}

	@Test
	public void b_currencyTest() {

		getList("getCurrencyList");
	}

	@Test
	public void a_settlementTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'settlementId':123,'name':'结算方式1','number':'12345'},{'settlementId':456,'name':'结算方式2','number':'12345'}]";
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

//	@Test
//	public void currency() {
//
//		HttpParam param = HttpParam.init();
//		param.setCookieParams(cookie);
//		 String str = "[{'currencyId':123,'name':'分类1','number':'12345'},{'currencyId':456,'name':'分类2','number':'12345'}]";
////		String str = null;
//		param.addCommon("size", "2");
//		param.addCommon("list", str);
//
//		String ret = HttpUtil.sendGet(BASE_URL + "sync/currency", param);
//		System.out.println(ret);
//	}
}
