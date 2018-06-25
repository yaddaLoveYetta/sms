package com.kingdee.eas.hrp.sms.service.app.api;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.service.app.model.ConfirmOrderModel;

import java.util.List;
import java.util.Map;

/**
 * @author yadda
 */
public interface HrpToSmsBusinessService {

    /**
     * 同步资料
     *
     * @param classId 基础资料类别
     * @param data    数据
     * @return 成功/失败记录详情
     */
    Map<String, Object> synchronizeBaseData(Integer classId, JSONArray data);


    /**
     * hrp上传订单到sms
     *
     * @param orders 订单列表
     * @return 成功/失败记录详情
     */
    Map<String, Object> synchronizationOrder(JSONArray orders);

    /**
     * hrp确认接单信息
     *
     * @param orders
     */
    void confirmOrder(List<ConfirmOrderModel> orders);

    /**
     * hrp同步入库单到sms
     *
     * @param warehouses 入库单集合
     * @return 成功/失败记录详情
     */
    Map<String, Object> synchronizeInWarehouse(JSONArray warehouses);

    /**
     * hrp同步收货单到sms
     *
     * @param receipts 收货单集合
     * @return 成功/失败记录详情
     */
    Map<String, Object> synchronizeReceipt(JSONArray receipts);

    /**
     * hrp同步退货单到hrp
     *
     * @param returns 退货单集合
     * @return 成功/失败记录详情
     */
    Map<String, Object> synchronizeReturns(JSONArray returns);
}