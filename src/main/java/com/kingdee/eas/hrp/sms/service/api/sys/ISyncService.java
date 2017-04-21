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
	void category(JSONArray list);
	void certificate(JSONArray list);
	void industry(JSONArray list);
	void currency(JSONArray list);
	void settlement(JSONArray list);
	void pay(JSONArray list);
	void item(JSONArray list);
	void taxCategory(JSONArray list);
	List<Supplier> getSupplierList(int pageNum, int pageSize);
	List<Category> getCategoryList(int pageNum, int pageSize);
	List<Certificate> getCertificateList(int pageNum, int pageSize);
	List<Industry> getIndustryList(int pageNum, int pageSize);
	List<Settlement> getSettlementList(int pageNum, int pageSize);
	List<Currency> getCurrencyList(int pageNum, int pageSize);
	List<Pay> getPayList(int pageNum, int pageSize);
	List<Item> getItemList(int pageNum, int pageSize);
	List<TaxCategory> getTaxCategoryList(int pageNum, int pageSize);
	
}
