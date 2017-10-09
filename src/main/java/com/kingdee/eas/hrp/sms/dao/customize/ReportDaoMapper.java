package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

public interface ReportDaoMapper {
	/**
	 * 物料证件查询
	 * 
	 * @Title getItemLicense
	 * @param params
	 * @return
	 * @return List<Map<String,Object>>
	 * @date 2017-09-25 17:15:47 星期一
	 */
	List<Map<String, Object>> getItemLicense(Map<String, Object> params);

	/**
	 * 供应商资质查询
	 * 
	 * @Title getSupplierLicense
	 * @param join
	 * @return
	 * @return List<Map<String,Object>>
	 * @date 2017-09-26 17:41:00 星期二
	 */
	List<Map<String, Object>> getSupplierLicense(String SupplierIds);
}
