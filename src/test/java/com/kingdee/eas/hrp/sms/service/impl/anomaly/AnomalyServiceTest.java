package com.kingdee.eas.hrp.sms.service.impl.anomaly;

import com.kingdee.eas.hrp.sms.BaseJunitTest;
import com.kingdee.eas.hrp.sms.service.api.Anomaly.IAnomalyService;
import org.junit.Test;

import javax.annotation.Resource;

public class AnomalyServiceTest extends BaseJunitTest {
    @Resource
    IAnomalyService anomalyService;
    @Test
    public void getUserNameEncode() throws Exception {
    }

    @Test
    public void getDeliverOrderCodeEncode() throws Exception {
        String code=anomalyService.getDeliverOrderCodeEncode();
        System.out.println(code);
    }


}