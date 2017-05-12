package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.List;
import java.util.Map;

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
import com.kingdee.eas.hrp.sms.model.Unit;

/**
 * @author Sunny
 *
 * 2017年4月25日
 */
public interface ISyncHRPService {

	
	/**
	 * 同步item
	 * Title sendItem
	 * 
	 * @param classId
	 * @param data
	 * @param userType
	 *
	 *return void 
	 *
	 * 2017年5月11日下午5:31:13
	 * @return 
	 */
	Map<String, String> sendItem(int classId, String data, String userType);

	Map<String, String> sendItem1(int classId, String data, String userType);

}
