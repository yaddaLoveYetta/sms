package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInAdpter;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;

/**
 * 单据插件
 * 
 * @ClassName BillPlugin
 * @author yadda
 * @date 2017-05-18 17:49:52 星期四
 */
public class BillPlugin extends PlugInAdpter {

	@Override
	public PlugInRet afterSave(int classId, String id, JSONObject data) {
	
		return super.afterSave(classId, id, data);
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		if (classId == 2020) {
			// 检验单据数据包
		}

		return super.beforeSave(classId, formData, data, userTyepe);
	}

}
