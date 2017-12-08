package com.kingdee.eas.hrp.sms.service.api.report;

import java.util.Map;

/**
 * @author yadda<silenceisok@163.com>
 * @since 2017/12/8
 */
public interface IFixedRecordService {

    /**
     * 获取设备维修记录报表数据
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    Map<String, Object> getFixedRecord(Integer pageSize, Integer pageNo);
}
