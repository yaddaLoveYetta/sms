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

    @Test
    public void fact() {

        int n = 90;
        int ret = 1;

        for (int i = 1; i <= n; i++) {
            ret *= i;
        }
        System.out.println(ret);
        System.out.println(10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1);
    }

    @Test
    public void fact2() {

        int n = 30;

        // 数组从地位到高位表示结果的个十百千万……位上的数字
        int[] ret = new int[50];

        ret[0] = 1;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < ret.length; j++) {
                //数组个位置上数字操作
                ret[j] = ret[j] * i;
            }

            for (int j = 1; j < ret.length; j++) {
                int up = ret[j - 1] / 10;
                ret[j - 1] = ret[j - 1] % 10;
                ret[j] = ret[j] + up;
            }

        }

        String result = "";
        //去掉开头的0
        boolean frontZero = true;
        for (int i = ret.length - 1; i >= 0; i--) {
            if (ret[i] != 0 || !frontZero) {
                frontZero = false;
                result += ret[i];
            }
        }

        System.out.println(result);
    }

}