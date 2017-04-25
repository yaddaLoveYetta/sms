/**
 * 
 */
package com.kingdee.eas.hrp.sms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void category() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'categoryId':123,'categoryName':'分类','number':'12345'},{'categoryId':123,'categoryName':'分类','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/category", param);
		System.out.println(ret);

	}

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
		String str = "[{'categoryId':123,'categoryName':'分类','number':'12345'},{'categoryId':123,'categoryName':'分类','number':'12345'}]";
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
		String str = "[{'certificateId':123,'certificateName':'分类','number':'12345'},{'certificateId':123,'certificateName':'分类','number':'12345'}]";
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
		String str = "[{'industryId':123,'industryName':'分类','number':'12345'},{'industryId':123,'industryName':'分类','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/industry", param);
		System.out.println(ret);
	}

	@Test
	public void b_industryTest() {

		getList("getIndustryList");
	}

	@Test
	public void a_currencyTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'currencyId':123,'currencyName':'分类','number':'12345'},{'currencyId':123,'currencyName':'分类','number':'12345'}]";
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/currency", param);
		System.out.println(ret);
	}

	@Test
	public void b_currencyTest() {

		getList("getCurrencyList");
	}

	@Test
	public void a_settlementTest() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		String str = "[{'settlementId':123,'settlementName':'分类','number':'12345'},{'settlementId':123,'settlementName':'分类','number':'12345'}]";
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
		String str = "[{'payId':123,'payName':'分类','number':'12345'},{'payId':123,'payName':'分类','number':'12345'}]";
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
		String str = "[{'itemId':123,'itemName':'分类','number':'12345'},{'itemId':123,'itemName':'分类','number':'12345'}]";
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
		String str = "[{'taxCategoryId':123,'taxCategoryName':'分类','number':'12345'},{'taxCategoryId':123,'taxCategoryName':'分类','number':'12345'}]";
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
		String str = "[{'supplierId':123,'categoryId':'','certificateId':'123','industryId':'123','currencyId':'123','settlementId':'123','payId':'123','itemId':'123','taxCategoryId':'123','number':'12345'},{'supplierId':123,'categoryId':'123','certificateId':'123','industryId':'123','currencyId':'123','settlementId':'123','payId':'123','itemId':'123','taxCategoryId':'123','number':'12345'}]";
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
	public void currency() {

		HttpParam param = HttpParam.init();
		param.setCookieParams(cookie);
		// String str =
		// "[{'currencyId':123,'currencyName':'分类','number':'12345'},{'currencyId':456,'currencyName':'分类','number':'12345'}]";
		String str = null;
		param.addCommon("size", "2");
		param.addCommon("list", str);

		String ret = HttpUtil.sendGet(BASE_URL + "sync/currency2", param);
		System.out.println(ret);
	}

	@Test
	public void syncCurrency() {

		String url = BASE_URL + "sync/currency";

		HttpParam hp = HttpParam.init();

		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> item = new HashMap<>();
		item.put("id", 1);
		item.put("name", "人民币");
		item.put("number", "RMB");

		list.add(item);

		item = new HashMap<>();
		item.put("id", 2);
		item.put("name", "美元");
		item.put("number", "USD");

		list.add(item);

		hp.addCommon("count", "2");
		hp.addCommon("list", JSON.toJSONString(list));

		System.out.println(JSON.toJSONString(hp));

		Map<String, Object> ss = HttpUtil.sendGetForMap(url, hp);

		System.out.println(ss);

	}
}
