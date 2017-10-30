package com.kingdee.eas.hrp.sms.service.user;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kingdee.eas.hrp.sms.BaseJunitTest;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.util.Environ;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends BaseJunitTest {

	@Test
	public void getUsersTest() {

		IUserService userServeice = Environ.getBean(IUserService.class);

		List<User> users = userServeice.getUserList(1, 2);

		System.out.println(users);
	}
}
