package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.SysProfileMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.SysProfile;
import com.kingdee.eas.hrp.sms.model.SysProfileExample;
import com.kingdee.eas.hrp.sms.model.SysProfileExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.sys.ISysParamService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class SysParamService extends BaseService implements ISysParamService {

	@Override
	public SysProfile getSysParam(String category, String key) {

		SysProfileMapper mapper = sqlSession.getMapper(SysProfileMapper.class);

		SysProfileExample example = new SysProfileExample();
		Criteria criteria = example.createCriteria();

		criteria.andCategoryEqualTo(category);
		criteria.andKeyEqualTo(key);

		List<SysProfile> list = mapper.selectByExample(example);

		if (null == list || list.size() == 0) {
			throw new BusinessLogicRunTimeException("该参数不存在！");
		}

		return list.get(0);
	}

	@Override
	public List<SysProfile> getSysParams() {

		SysProfileMapper mapper = sqlSession.getMapper(SysProfileMapper.class);

		SysProfileExample example = new SysProfileExample();

		example.setOrderByClause("[index] ASC");
		
		List<SysProfile> list = mapper.selectByExample(example);

		return list;

	}

	@Override
	@Transactional
	public void setSysParam(String category, String key, String value) {

		SysProfileMapper mapper = sqlSession.getMapper(SysProfileMapper.class);
		SysProfile record = new SysProfile();
		record.setCategory(category);
		record.setKey(key);
		record.setValue(value);

		mapper.updateByPrimaryKeySelective(record);

	}

}
