package com.kingdee.eas.hrp.sms.service.plugin;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 插件适配器
 * 
 * @ClassName PlugInAdpter
 * @author yadda
 * @date 2017-04-27 17:32:12 星期四
 */
public abstract class PlugInAdpter implements IPlugIn {

	PlugInRet result = new PlugInRet();

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, int userTyepe) {
		return result;
	}

	@Override
	public PlugInRet afterSave(int classId, int id, JSONObject data) {
		return result;
	}

	@Override
	public PlugInRet beforeModify(int classId, Map<String, Object> formData, JSONObject data, int userType) {
		return result;
	}

	@Override
	public PlugInRet afterModify(int classId, JSONObject data) {
		return result;
	}

	@Override
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String items) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlugInRet afterDelete(int classId, String items) {
		return result;
	}

	@Override
	public PlugInRet beforeQuery(int classId, Map<String, Object> param) {
		return result;
	}

	@Override
	public PlugInRet afterQuery(int classId, List<Map<String, Object>> list) {
		return result;
	}

}
