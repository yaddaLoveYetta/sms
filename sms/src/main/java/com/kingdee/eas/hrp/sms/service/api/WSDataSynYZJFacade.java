package com.kingdee.eas.hrp.sms.service.api;

import java.util.List;
import java.util.Map;

public interface WSDataSynYZJFacade {

	/**
	 * 第三方应用登录hrp获取session
	 * 
	 * @Title login
	 * @param userId
	 *            用户id
	 * @param pwd
	 *            密码
	 * @return
	 * @return String sessionId
	 * @date 2017-08-28 17:39:40 星期一
	 */
	String login(String userId, String pwd);

	/**
	 * 获取用户信息
	 * 
	 * @Title getUserInfo
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @return
	 * @return Map<String,Object> 用户信息集合
	 * @date 2017-08-28 17:41:18 星期一
	 */
	Map<String, Object> getUserInfo(String sessionId);

	/**
	 * 获取设备信息
	 * 
	 * @Title yzj2hrpFaCurCard
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @param barCode
	 *            条形码
	 * @param faCurCardNumber
	 *            设备编码
	 * @return
	 * @return Map<String,Object> 设备基本信息
	 * @date 2017-08-28 17:43:46 星期一
	 */
	Map<String, Object> yzj2hrpFaCurCard(String sessionId, String barCode, String faCurCardNumber);

	/**
	 * 获取基础信息
	 * 
	 * @Title getBaseData
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @param classId
	 *            基础信息类别
	 * @return
	 * @return List<Map<String,Object>> 基础信息集合
	 * @date 2017-08-28 17:45:53 星期一
	 */
	List<Map<String, Object>> getBaseData(String sessionId, int classId);

	/**
	 * 提交一张报修申请单
	 * 
	 * @Title yzj2hrpMaintainRequestSave
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @param deptment
	 *            部门id
	 * @param faCurCardId
	 *            设备id
	 * @param urgentDegree
	 *            紧急程度
	 * @param repairLocation
	 *            报修地点
	 * @param faultTypesId
	 *            故障类型
	 * @param description
	 *            故障描述
	 * @param attachment
	 *            附件列表
	 * @return void
	 * @date 2017-08-28 17:49:02 星期一
	 */
	void yzj2hrpMaintainRequestSave(String sessionId, String deptment, String faCurCardId, int urgentDegree, String repairLocation, int faultTypesId, String description,
			List<Map<String, Object>> attachment);

	/**
	 * 获取报修单列表
	 * 
	 * @Title yzj2hrpMaintainRequestSelect
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @param status
	 *            单据状态
	 * @param pageSize
	 *            分页大小
	 * @param pageNo
	 *            当前页码
	 * @return
	 * @return Map<String,Object>
	 * @date 2017-08-28 17:51:16 星期一
	 */
	Map<String, Object> yzj2hrpMaintainRequestSelect(String sessionId, int status, int pageSize, int pageNo);

	/**
	 * 获取一张报修单详情
	 * 
	 * @Title yzj2hrpMaintainRequestDetailSelect
	 * @param sessionId
	 *            登录接口获取的用户身份标识
	 * @param id
	 *            报修单id
	 * @return
	 * @return Map<String,Object>
	 * @date 2017-08-28 17:52:17 星期一
	 */
	Map<String, Object> yzj2hrpMaintainRequestDetailSelect(String sessionId, String id);

	/**
	 * 报修单评价
	 * 
	 * @Title yzj2hrpMaintainRequestEvaluate
	 * @param sessionId
	 * @param id
	 * @param score
	 * @param description
	 * @return void
	 * @date 2017-08-28 17:53:50 星期一
	 */
	void yzj2hrpMaintainRequestEvaluate(String sessionId, String id, float score, String description);
}
