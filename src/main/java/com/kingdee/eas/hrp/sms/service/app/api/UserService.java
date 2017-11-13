package com.kingdee.eas.hrp.sms.service.app.api;

/**
 * @author yadda
 */
public interface UserService {
    /**
     * 第三方应用获取token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    String getToken(String appId, String appSecret);
}
