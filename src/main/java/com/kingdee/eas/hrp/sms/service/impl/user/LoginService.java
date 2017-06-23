package com.kingdee.eas.hrp.sms.service.impl.user;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
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

	/*
	 * 防缓存击穿
	 */
	private BloomFilter<String> bf;

	@PostConstruct
	private void Init() {

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		List<User> users = mapper.selectByExample(null);

		bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), users.size(), 0.001);

		for (User user : users) {
			bf.put(user.getName());
		}

	}

	@ServiceLog(desc = "登录服务")
	@Override
	public User login(String username, String password, String type) {

		// if (!bf.mightContain(username)) {
		// // 可能不是正确的用户名
		// throw new BusinessLogicRunTimeException("BloomFilter用户名或密码错误");
		// }
		
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
