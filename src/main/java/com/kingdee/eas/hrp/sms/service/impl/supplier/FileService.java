package com.kingdee.eas.hrp.sms.service.impl.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.customize.TemplateDaoMapper;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormEntries;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class FileService extends BaseService implements IFileService {

	@Resource
	ITemplateService templateService;

	@Override
	@Transactional
	public void saveUrlToDb(int classId, String itemId, List<String> urls) {

		for (String url : urls) {
			// 判断是否有该附件路径已存在，是则不添加新的附记录
			JSONArray jsonArray3 = new JSONArray();
			JSONArray jsonArray2 = jsonArray3;
			JSONArray jsonArray = jsonArray2;
			JSONArray conditionArry = jsonArray;
			JSONObject condition = new JSONObject(true);
			condition.put("fieldKey", "url");
			condition.put("logicOperator", "=");
			condition.put("value", url);
			conditionArry.add(condition);
			condition = new JSONObject(true);
			condition.put("fieldKey", "parent");
			condition.put("logicOperator", "=");
			condition.put("value", itemId);
			conditionArry.add(condition);

			Map<String, Object> items = templateService.getItems(classId, conditionArry.toString(), "", 1, 1000);
			if ((long) items.get("count") > 0){
				continue;}

			JSONObject json = new JSONObject();
			JSONObject entry = new JSONObject();
			JSONObject data = new JSONObject();
			JSONObject entryData = new JSONObject();
			JSONArray array = new JSONArray();
			data.put("url", url);
			entryData.put("flag", "1");
			entryData.put("data", data);
			array.add(entryData);
			entry.put("1", array);
			json.put("entry", entry);
			json.put("id", itemId);

			templateService.editItem(classId, itemId, json.toString());
		}

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	
	public boolean checkAttachment(int classId, String id, String entryId, int type) {

		Map<String, Object> template = templateService.getFormTemplate(classId, 1);
		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");
		// 子表资料描述信息

		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		FormEntries formEntry = (FormEntries) formEntries.get("1");

		String entryTableName = formEntry.getTableName();

		String sql = "UPDATE t1 SET t1.[check] = " + type + " FROM " + entryTableName + " t1 WHERE t1.id = '" + entryId + "'";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sql", sql);

		TemplateDaoMapper mapper = sqlSession.getMapper(TemplateDaoMapper.class);
		mapper.edit(params);

		return true;
	}

}
