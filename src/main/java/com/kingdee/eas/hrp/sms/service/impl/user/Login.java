package com.kingdee.eas.hrp.sms.service.impl.user;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.log.ServiceLog;
import com.kingdee.eas.hrp.sms.service.api.user.ILogin;

@Service
public class Login implements ILogin {

	@ServiceLog(desc = "登录服务")
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub

		int i = 1 / 0;
		// 密码错误
		if (true) {
			throw new RuntimeException("用户名或密码错误");
		}

		return false;
	}

}
