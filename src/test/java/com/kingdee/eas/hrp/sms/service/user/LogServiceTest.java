package com.kingdee.eas.hrp.sms.service.user;

import org.junit.Test;

import com.kingdee.eas.hrp.sms.BaseJunitTest;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.ILoginService;
import com.kingdee.eas.hrp.sms.util.Environ;

public class LogServiceTest extends BaseJunitTest {

	@Test
	public void login() {

		ILoginService loginService = Environ.getBean(ILoginService.class);

		User user = loginService.login("test", "202cb962ac59075b964b07152d234b70", 50801);

		System.out.println(user);
	}
}
