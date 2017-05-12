package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class SyncHRPService extends BaseService implements ISyncHRPService {

	@Resource
	ITemplateService templateService;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> sendItem(int classId, String data, String userType) {
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		JSONArray orderByArray = new JSONArray();
		JSONObject orderByItem = new JSONObject(true);

		orderByItem.put("fieldKey", "number");
		orderByItem.put("orderDirection", "ASC");
		orderByArray.add(orderByItem);

		orderByItem = new JSONObject();
		orderByItem.put("fieldKey", "name");
		orderByItem.put("orderDirection", "ASC");
		orderByArray.add(orderByItem);

		String orderBy = JSON.toJSONString(orderByArray);

		Map<String, Object> temp = templateService.getItems(classId, "", orderBy, 1, 10, userType);
		int count = (int) temp.get("count");
		JSONArray targetList = new JSONArray();
		for (int i = 1; i <= count; i++) {
			Map<String, Object> temp1 = templateService.getItems(classId, "", orderBy, i, 100, userType);
			List<Map<String, Object>> items = (List<Map<String, Object>>) temp1.get("list");
			for (Map<String, Object> item : items) {
				// 如果此记录被引用，则不删除
				String id = (String) item.get("id");
				if (idList.contains(id) && item.get("syncStatus").equals("0")) {
					JSONObject targetItem = (JSONObject) JSONObject.toJSON(item);
					targetList.add(targetItem);
					String syncStatus = "\"syncStatus\":\"0\"";
					templateService.editItem(classId, id, syncStatus, userType);
					System.out.println(targetItem.toJSONString());
					System.out.println(targetList.toString());
				}
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("size", String.valueOf(targetList.size()));
		result.put("list", targetList.toString());

		return result;
	}

	@Override
	public Map<String, String> sendItem1(int classId, String data, String userType) {

		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		JSONArray targetList = new JSONArray();

		for (int i = 0; i < idList.size(); i++) {
			Map<String, Object> item = templateService.getItemById(classId, idList.get(i), userType);
			String id = (String) item.get("id");
			if (idList.contains(id) && ("0".equals(item.get("syncStatus")) || item.get("syncStatus") == null)
					|| item.get("syncStatus").equals("")) {
				JSONObject targetItem = (JSONObject) JSONObject.toJSON(item);
				//HRP不需要此数据
				targetItem.remove("syncStatus");
				targetItem.remove("entry");
				targetList.add(targetItem);
				templateService.editItem(classId, id, "{}", userType);
				System.out.println(targetItem.toJSONString());
				System.out.println(targetList.toString());
			}

		}
		List<Supplier> list = JSONObject.parseArray(targetList.toString(), Supplier.class);
		Map<String, String> result = new HashMap<String, String>();
		result.put("size", String.valueOf(targetList.size()));
		result.put("list", targetList.toString());

		return result;
	}

}
