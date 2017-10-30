package com.kingdee.eas.hrp.sms.service.impl.supplier;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.weaver.JoinPointSignature;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class FileUploadService extends BaseService implements IFileUploadService {

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
			if ((long) items.get("count") > 0)
				continue;
			
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

}
