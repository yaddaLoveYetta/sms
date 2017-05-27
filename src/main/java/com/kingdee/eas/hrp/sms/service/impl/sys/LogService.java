package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.SysLogMapper;
import com.kingdee.eas.hrp.sms.model.SysLog;
import com.kingdee.eas.hrp.sms.service.api.sys.ILogService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class LogService extends BaseService implements ILogService {

	@Override
	@Transactional
	public void add(String userName, String ip, String desc, Date optTime, String clazz, String method, String params) {

		SysLogMapper mapper = sqlSession.getMapper(SysLogMapper.class);

		SysLog log = new SysLog();

		log.setUserName(userName);
		log.setIp(ip);
		log.setMessage(desc);
		log.setOperateTime(optTime);
		log.setClazz(clazz);
		log.setMethod(method);
		log.setParams(params);

		mapper.insertSelective(log);

	}

}
