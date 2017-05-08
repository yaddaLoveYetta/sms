package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.UserMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ServiceLog;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.model.UserExample;
import com.kingdee.eas.hrp.sms.model.UserExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.user.ILoginService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Common;

@Service
public class LoginService extends BaseService implements ILoginService {

	@ServiceLog(desc = "登录服务")
	@Override
	public User login(String username, String password, String type) {

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();

		Criteria criteria = example.createCriteria();

		criteria.andNameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		criteria.andTypeEqualTo(type);

		List<User> users = mapper.selectByExample(example);

		if (null != users && users.size() == 1) {
			return users.get(0);
		}

		throw new BusinessLogicRunTimeException("用户名或密码错误");

	}

	@Override
	@Transactional
	public User createToken(String username, String password, String type) {

		User user = login(username, password, type);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		user.setToken(Common.getUUIDKey());

		mapper.updateByPrimaryKey(user);

		return user;

	}

}
