package com.kingdee.eas.hrp.sms.controller.report;

import com.kingdee.eas.hrp.sms.service.api.report.IFixedRecordService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yadda<silenceisok@163.com>
 * @since 2017/12/8
 */
@RequestMapping(value = "/report/")
@Controller
public class FixRecordController {

    @Resource
    private IFixedRecordService fixedRecordService;

    @RequestMapping(value = "getFixedRecord")
    public void getFixedRecord(HttpServletRequest request, HttpServletResponse response) {

        int pageSize = ParameterUtils.getParameter(request, "pageSize", 50);
        int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);

        Map<String, Object> ret = fixedRecordService.getFixedRecord(pageSize, pageNo);

        ResponseWriteUtil.output(response, StatusCode.SUCCESS, ret);
        
    }
}
