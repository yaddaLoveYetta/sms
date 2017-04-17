package com.kingdee.eas.hrp.sms.service.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

	@Resource
	protected SqlSession sqlSession;
	
}
