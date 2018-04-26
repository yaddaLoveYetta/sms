package com.kingdee.eas.hrp.sms.service.api.sendcargo;

import java.util.List;
import java.util.Map;

public interface ISendcargoService {


    List<Map<String, Object>> getCode(String items);

    void sendCargoHrp(String items);

    /**
     * 撤回发送到医院的发货单
     *
     * @param id 发货单id
     */
    void undoSendHrp(String id);

}
