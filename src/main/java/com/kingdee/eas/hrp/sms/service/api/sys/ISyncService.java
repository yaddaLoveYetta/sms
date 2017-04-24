package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.Pay;
import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.model.TaxCategory;

public interface ISyncService {

	Map<String, JSONObject> supplier(JSONArray list);

	Map<String, JSONObject> category(JSONArray list);

	Map<String, JSONObject> certificate(JSONArray list);

	Map<String, JSONObject> industry(JSONArray list);

	Map<String, JSONObject> currency(JSONArray list);

	Map<String, JSONObject> settlement(JSONArray list);

	Map<String, JSONObject> pay(JSONArray list);

	Map<String, JSONObject> item(JSONArray list);

	Map<String, JSONObject> taxCategory(JSONArray list);

	List<Supplier> getSupplierList(int pageNum, int pageSize);

	List<Category> getCategoryList(int pageNum, int pageSize);

	List<Certificate> getCertificateList(int pageNum, int pageSize);

	List<Industry> getIndustryList(int pageNum, int pageSize);

	List<Settlement> getSettlementList(int pageNum, int pageSize);

	List<Currency> getCurrencyList(int pageNum, int pageSize);

	List<Pay> getPayList(int pageNum, int pageSize);

	List<Item> getItemList(int pageNum, int pageSize);

	List<TaxCategory> getTaxCategoryList(int pageNum, int pageSize);

	/**
	 * 同步币别
	 * 
	 * @Title currency
	 * @param list
	 *            Currency集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 * @date 2017-04-22 21:45:59 星期六
	 */
	List<Map<String, Object>> currency(List<Currency> list);

}
