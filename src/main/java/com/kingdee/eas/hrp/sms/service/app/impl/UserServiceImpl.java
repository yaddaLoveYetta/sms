package com.kingdee.eas.hrp.sms.service.app.impl;

import com.kingdee.eas.hrp.sms.dao.generate.UserMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.app.api.UserService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.yadda.api.core.ApiMapping;
import com.yadda.api.core.MethodEnum;
import com.yadda.api.core.Token;
import com.yadda.api.core.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yadda
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private TokenService tokenService;

    /**
     * 第三方应用获取token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    @Override
    @ApiMapping(value = "kingdee.eas.hrp.sms.user.getToken")
    public String getToken(String appId, String appSecret) {
        Token token = tokenService.create(appId, appSecret);

        if (null != token) {
            return token.getAccessToken();
        }
        return null;
    }

    @ApiMapping(value = "kingdee.eas.hrp.sms.user.getUser", useLogin = true, methodType = MethodEnum.POST)
    public User getUser(String userId) {

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByPrimaryKey(userId);

        if (null == user) {
            throw new BusinessLogicRunTimeException("token关联用户无效,请联系管理员!");
        }

        System.out.println(SessionUtil.getUserId());
        System.out.println(SessionUtil.getUserName());
        System.out.println(SessionUtil.getUserLinkSupplier());


        return user;

    }
}
