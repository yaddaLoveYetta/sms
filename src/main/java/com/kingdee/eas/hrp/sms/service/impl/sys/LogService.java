package com.kingdee.eas.hrp.sms.service.impl.sys;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.sys.ILogService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.mysql.fabric.xmlrpc.base.Data;

@Service
public class LogService extends BaseService implements ILogService {

	@Override
	public void add(String userId, String userName, String ip, String desc, Data optTime, String clazz, String meth) {
		//sqlSession.getMapper(type)
		
	}

}
