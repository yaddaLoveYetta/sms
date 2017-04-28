package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInAdpter;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;

public class ItemPlugin extends PlugInAdpter {

	@Override
	public PlugInRet beforeModify(int classId, JSONObject data) {
		// TODO Auto-generated method stub
		return super.beforeModify(classId, data);
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data) {
		// TODO Auto-generated method stub
		return super.beforeSave(classId, formData, data);
	}

}
