package com.kingdee.eas.hrp.sms.service.api.report;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IReportService {

	/**
	 * 获取订单统计记录（物料编码，物料名称，规格型号，单位，订单数量，发货数量，退货数量，入库数量）
	 * 
	 * @param itemId
	 * @param supplier
	 * @param orderStartDate
	 * @param orderEndDate
	 * @return JSONObject
	 *
	 *         2017年6月14日上午11:58:16
	 */
	JSONObject getRecord(String itemId, String supplier, String orderStartDate, String orderEndDate, int pageNo, int pageSize);

	/**
	 * 物料证件查询
	 * 
	 * @Title getItemLicense
	 * @param params
	 *            参数集合
	 * @return Map<String,Object>
	 * @date 2017-09-25 16:48:52 星期一
	 */
	Map<String, Object> getItemLicense(Map<String, Object> params);

}
