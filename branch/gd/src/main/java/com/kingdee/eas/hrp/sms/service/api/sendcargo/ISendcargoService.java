package com.kingdee.eas.hrp.sms.service.api.sendcargo;

import java.util.List;
import java.util.Map;

public interface ISendcargoService {
	
	
	List<Map<String, Object>> getCode(String items);
	
	public void sendCargoHrp(String items);

}