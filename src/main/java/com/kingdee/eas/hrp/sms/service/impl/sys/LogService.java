package com.kingdee.eas.hrp.sms.service.impl.sys;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.sys.ILogService;
import com.mysql.fabric.xmlrpc.base.Data;

@Service
public class LogService implements ILogService {

	@Override
	public void add(String userId, String userName, String ip, String desc, Data optTime, String clazz, String meth) {
		// TODO Auto-generated method stub
		
	}

}
