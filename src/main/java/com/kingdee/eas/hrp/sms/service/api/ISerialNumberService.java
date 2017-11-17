package com.kingdee.eas.hrp.sms.service.api;

public interface ISerialNumberService {
    /**
     * 创建发货单订单号
     *
     * @param classId 业务类别
     * @return
     */
    String getSerialNumber(int classId);
}
