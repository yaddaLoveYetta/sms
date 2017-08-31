package com.kingdee.eas.hrp.sms.service.api.sys;

import java.util.List;
import java.util.Map;

import com.kingdee.eas.hrp.sms.model.SysProfile;

public interface ISysParamService {

	/**
	 * 获取单个系统参数
	 * 
	 * @param category
	 *            参数类别
	 * @param key
	 *            参数key
	 * @return
	 */
	public SysProfile getSysParam(String category, String key);

	/**
	 * 取得系统配置参数列表
	 * 
	 * @return
	 */
	public List<SysProfile> getSysParams();

	/**
	 * 设置参数的值
	 * 
	 * @param category
	 *            参数类别
	 * @param key
	 *            参数key
	 * @param value
	 *            参数值
	 */
	public void setSysParam(String category, String key, String value);

}
