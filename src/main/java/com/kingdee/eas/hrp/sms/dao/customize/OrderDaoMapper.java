package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface OrderDaoMapper {
	//发货单追踪查询
	List<Map<String, Object>> selectSendcargo(@Param("orderId")String orderId,@Param("number")String number,@Param("name")String name,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("supplierId")String supplierId);
	//收货单追踪查询
	List<Map<String, Object>> selectPurReceival(@Param("orderId")String orderId,@Param("number")String number,@Param("name")String name,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("supplierId")String supplierId);
	//入库单追踪查询
	List<Map<String, Object>> selectPurInwarehs(@Param("orderId")String orderId,@Param("number")String number,@Param("name")String name,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("supplierId")String supplierId);
	//退货单追踪查询
	List<Map<String, Object>> selectPurReturns(@Param("orderId")String orderId,@Param("number")String number,@Param("name")String name,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("supplierId")String supplierId);

}
