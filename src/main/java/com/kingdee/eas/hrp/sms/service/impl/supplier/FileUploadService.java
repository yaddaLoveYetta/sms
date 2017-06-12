package com.kingdee.eas.hrp.sms.service.impl.supplier;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public void saveUrlToDb(int classId, String itemId, List<String> urls) {

		for (String url : urls) {
			//判断是否有该附件路径已存在，是则不添加新的附记录
			JSONArray conditionArry = new JSONArray();
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
			JSONObject json = new JSONObject(true);
			JSONObject entry = new JSONObject(true);
			entry.put("parent", itemId);
			entry.put("url", url);
			json.put("entry", entry.toJSONString());
			templateService.checkItem(classId, json.toString());
		}

	}
	
	@Override
	public JSONObject delete(Integer classId, String data) {

		String[] ids = data.split(",");
		StringBuilder targetId = new StringBuilder("");
		JSONObject err = new JSONObject(true);
		for (String id : ids) {
			Map<String, Object> item = templateService.getItemById(classId, id);
			String fileName = (String) item.get("path");
			File file = new File(fileName);
			// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				if (file.delete()) {
					targetId.append(id).append(",");
				} else {
					err.put(fileName, "删除失败");
					continue;
				}
			} else {
				err.put(fileName, "文件不存在");
				continue;
			}
		}
		targetId.deleteCharAt(targetId.length() - 1);
		templateService.delItem(classId, targetId.toString());
		return err;
	}

}
