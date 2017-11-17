package com.kingdee.eas.hrp.sms.controller.anomaly;

import com.kingdee.eas.hrp.sms.service.api.Anomaly.IAnomalyService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yadda
 */
@Controller
@RequestMapping(value = "/anomaly/")
public class AnomalyController {

    /**
     * 新增用户，用户名按流水号生成
     * <p>
     * 生成流水号
     *
     * @param request
     * @param response
     * @return void
     * @Title getEncode
     * @date 2017-10-11 15:38:57 星期三
     */
    @RequestMapping(value = "getUserNameEncode")
    public void getUserNameEncode(HttpServletRequest request, HttpServletResponse response) {

        IAnomalyService anomalyService = Environ.getBean(IAnomalyService.class);

        String userNameEncode = anomalyService.getUserNameEncode();

        Map<String, Object> ret = new HashMap<String, Object>();

        ret.put("userNameEncode", userNameEncode);

        ResponseWriteUtil.output(response, ret);

    }

    public static void main(String[] args) {
        // psvm
        for (int i = 0; i < 10; i++) {
            //sout
            System.out.println(i);
        }
    }
}
