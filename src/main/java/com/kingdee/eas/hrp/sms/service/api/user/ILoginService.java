package com.kingdee.eas.hrp.sms.service.api.user;

import com.kingdee.eas.hrp.sms.model.User;

public interface ILoginService {

	User login(String username, String password);
}
