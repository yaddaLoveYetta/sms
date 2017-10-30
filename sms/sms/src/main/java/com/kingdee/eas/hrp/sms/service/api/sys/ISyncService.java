package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 * @author Sunny
 *
 *         2017年4月25日
 */
public interface ISyncService {

	/**
	 * 同步基础资料
	 * 
	 * @Title sync
	 * @param classId
	 *            业务类型
	 * @param list
	 *            同步数据列表
	 * @param userType
	 *            用户类别
	 * @return List<Map<String,Object>> 同步失败记录
	 * @date 2017-05-18 09:53:06 星期四
	 */
	List<Map<String, Object>> sync(int classId, JSONArray list);

}
