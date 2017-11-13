package com.kingdee.eas.hrp.sms.service.app.impl;

import com.alibaba.fastjson.JSON;
import com.kingdee.eas.hrp.sms.service.app.api.HrpToSmsBusinessService;
import com.kingdee.eas.hrp.sms.util.Common;
import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;
import com.yadda.api.common.Md5Util;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HrpToSmsBusinessServiceImplTest {

    @Autowired
    HrpToSmsBusinessService hrpToSmsBusinessService;

    private static final String appId = "test";
    private static final String appSecret = "123";
    private static final String BASE_URL = "http://127.0.0.1:8080/sms/api/";

    String token;

    @Before
    public void getToken() {


        Map<String, Object> businessParams = new HashMap<>(10);
        businessParams.put("appId", appId);
        businessParams.put("appSecret", appSecret);

        Map<String, String> commParams = new HashMap<>(10);
        commParams.put("method", "kingdee.eas.hrp.sms.user.getToken");
        commParams.put("params", JSON.toJSONString(businessParams));


        HttpParam param = HttpParam.init();
        param.setCommonParams(commParams);

        Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL, param);

        if (!ret.isEmpty()) {
            token = JSON.parseObject(JSON.toJSONString(ret.get("data"))).getString("value");
        }
        System.out.println(token);
    }

    @Test
    public void synchronizeBaseData() throws Exception {

        String ss = "[{'id':'1','number':'qazwsx','name':'test industry'},{'id':'2','number':'qadfssx','name':'test industry'},{'id':'3','number':'qazwsx','name':'test industry'}]";
        Map<String, Object> businessParams = new HashMap<>(10);
        businessParams.put("classId", 1012);
        businessParams.put("data", ss);

        businessParams = Common.sortMapByKey(businessParams);

        long timestamp = new Date().getTime();

        String sign = Md5Util.md5Encode(appSecret + timestamp + businessParams);

        Map<String, String> commParams = new HashMap<>(10);
        commParams.put("token", token);
        commParams.put("method", "kingdee.eas.hrp.sms.bz.synchronizeBaseData");
        commParams.put("params", JSON.toJSONString(businessParams));
        commParams.put("timestamp", String.valueOf(timestamp));
        commParams.put("sign", sign);


        HttpParam param = HttpParam.init();
        param.setCommonParams(commParams);

        Map<String, Object> ret = HttpUtil.sendGetForMap(BASE_URL, param);

        System.out.println(ret.toString());

    }

}