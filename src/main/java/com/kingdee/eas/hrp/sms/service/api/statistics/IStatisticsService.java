package com.kingdee.eas.hrp.sms.service.api.statistics;

import java.util.Map;

public interface IStatisticsService {

	/**
	 * 获取订单统计记录（物料编码，物料名称，规格型号，单位，订单数量，发货数量，退货数量，入库数量）
	 * @param itemId
	 * @param supplier
	 * @param orderStartDate
	 * @param orderEndDate
	 * @return Map<String, Object>
	 *
	 * 2017年6月14日上午11:58:16
	 */
	Map<String, Object> getRecord(String itemId, String supplier, String orderStartDate, String orderEndDate);

}
