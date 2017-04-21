package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.List;

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

	// 查询供应商资料（list）
	List<Supplier> getSupplierList(int pageNum, int pageSize);

	// 查询分类（list）
	List<Category> getCategoryList(int pageNum, int pageSize);

	// 查询证书（list）
	List<Certificate> getCertificateList(int pageNum, int pageSize);

	// 查询证书（list）
	List<Industry> getIndustryList(int pageNum, int pageSize);

	// 查询币别（list）
	List<Currency> getCurrencyList(int pageNum, int pageSize);

	// 查询结算方式（list）
	List<Settlement> getSettlementList(int pageNum, int pageSize);

	// 查询付款方式（list）
	List<Pay> getPayList(int pageNum, int pageSize);

	//查询物料（list）
	List<Item> getItemList(int pageNum, int pageSize);

	//查询税种（list）
	List<TaxCategory> getTaxCategoryList(int pageNum, int pageSize);

}