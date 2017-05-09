package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.City;
import com.kingdee.eas.hrp.sms.model.Country;
import com.kingdee.eas.hrp.sms.model.County;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.Pay;
import com.kingdee.eas.hrp.sms.model.Province;
import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.model.Supplier_License_Type;
import com.kingdee.eas.hrp.sms.model.TaxCategory;

/**
 * @author Sunny
 *
 * 2017年4月25日
 */
public interface ISyncService {

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

	/**
	 * 同步分类
	 * @Title category
	 * @param list
	 * 		  category 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> category(List<Category> list);
	
	/**
	 * 同步证书
	 * @Title certificate
	 * @param list
	 * 		  certificate 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> certificate(List<Certificate> list);
	
	/**
	 * 同步证书
	 * @Title license
	 * @param list
	 * 		  license 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> license(List<Supplier_License_Type> list);
	
	/**
	 * 同步行业
	 * @Title industry
	 * @param list
	 * 		  industry 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> industry(List<Industry> list);
	
	/**
	 * 同步结算方式
	 * @Title settlement
	 * @param list
	 * 		  settlement 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> settlement(List<Settlement> list);
	
	/**
	 * 同步付款方式
	 * @Title pay
	 * @param list
	 * 		  pay 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> pay(List<Pay> list);
	
	/**
	 * 同步物料
	 * @Title item
	 * @param list
	 * 		  item 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> item(List<Item> list);
	
	/**
	 * 同步税种
	 * @Title taxCategory
	 * @param list
	 * 		  taxCategory 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> taxCategory(List<TaxCategory> list);

	/**
	 * 同步供应商
	 * @Title supplier
	 * @param list
	 * 		  supplier 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> supplier(List<Supplier> list);

	/**
	 * 同步国家
	 * @Title country
	 * @param list
	 * 		  country 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> country(List<Country> list);

	/**
	 * 同步城市
	 * @Title city
	 * @param list
	 * 		  city 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> city(List<City> list);

	/**
	 * 同步省份
	 * @Title province
	 * @param list
	 * 		  province 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> province(List<Province> list);

	/**
	 * 同步区县
	 * @Title county
	 * @param list
	 * 		  county 集合
	 * @return List<Map<String,Object>> 同步失败记录及失败原因
	 */
	List<Map<String, Object>> county(List<County> list);

}
