package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface InvoiceDaoMapper {

	/**
	 * 
	 * 获取个体码
	 * 
	 */
	 List<Map<String, Object>> selectInvoiceById(@Param("id")List id);

}