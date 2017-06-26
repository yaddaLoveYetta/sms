package com.kingdee.eas.hrp.sms.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.MsgLogMapper;
import com.kingdee.eas.hrp.sms.model.MsgLog;
import com.kingdee.eas.hrp.sms.service.api.IMsgLogService;

@Service
public class MsgLogService extends BaseService implements IMsgLogService {

	@Override
	@Transactional
	public void saveLog(String seqid, String mobiles, String smsContent, String reString) {
		MsgLogMapper mapper = sqlSession.getMapper(MsgLogMapper.class);
		MsgLog log = new MsgLog();
		log.setSeqid(seqid);
		log.setMobiles(mobiles);
		log.setSmsContent(smsContent);
		log.setRestr(reString);
		log.setSendtime(new Date());
		mapper.insertSelective(log);

	}

}
