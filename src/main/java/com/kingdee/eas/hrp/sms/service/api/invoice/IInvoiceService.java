package com.kingdee.eas.hrp.sms.service.api.invoice;

import java.util.List;
import java.util.Map;

public interface IInvoiceService {
	
	
	List<Map<String, Object>> getCode(String items);

}
