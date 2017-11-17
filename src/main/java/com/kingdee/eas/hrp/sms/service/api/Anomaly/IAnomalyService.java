package com.kingdee.eas.hrp.sms.service.api.Anomaly;

public interface IAnomalyService {

	/**
	 * 新增用户，用户名按规则生成
	 * 
	 * @Title getUserNameEncode
	 * @return
	 * @return String
	 * @date 2017-10-11 16:23:53 星期三
	 */
	String getUserNameEncode();

	/**
	 * 订单生成发货单时，发货单个体码生成后5位用当天的递增流水号
	 * @return
	 */
	String getDeliverOrderCodeEncode();

}
