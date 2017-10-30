package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class SyncService extends BaseService implements ISyncService {

	@Resource
	ITemplateService templateService;

	@Transactional
	public List<Map<String, Object>> sync_old(int classId, JSONArray list) {

		// 同步失败的记录S
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Object item : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				String id = ((JSONObject) item).getString("id");
				if (templateService.getItemById(classId, id) == null) {
					templateService.addItem(classId, item.toString());
				} else {
					templateService.editItem(classId, id, item.toString());
				}
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", item.toString());
				errList.add(errItem);
			}
		}
		return errList;
	}

	/**
	 * 同步基础资料 (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.api.sys.ISyncService#sync(int, com.alibaba.fastjson.JSONArray,
	 *      java.lang.String)
	 * @param classId
	 * @param list
	 * @param userType
	 * @return
	 * @date 2017-05-18 09:52:21 星期四
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Map<String, Object>> sync(int classId, JSONArray list) {

		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

		ITemplateService templateService = Environ.getBean(ITemplateService.class);

		// 基础资料模板
		Map<String, Object> formTemplate = templateService.getFormTemplate(classId, 1);
		// 主表资料描述信息
		FormClass formClass = (FormClass) formTemplate.get("formClass");

		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) formTemplate.get("formFields")).get("0");

		String primaryKey = formClass.getPrimaryKey(); // 主表主键key

		if (list.size() > 1000) {
			throw new BusinessLogicRunTimeException("你提交的数据太多：每次最多同步1000条数据");
		}

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> errItem = new HashMap<String, Object>();

			String baseItemStr = list.getString(i);

			JSONObject baseItem = JSON.parseObject(baseItemStr); // 一条基础资料数据

			if (!baseItem.containsKey(primaryKey)) {
				// 同步的数据必须包含主键
				errItem.put("msg", "同步的数据必须包含主键");
				errItem.put("item", baseItem);
				ret.add(errItem);

				continue; // 忽略该条记录
			}

			if (formFields.containsKey("review")) {
				baseItem.put("review", "true"); // 默认已审核
			}
			if (formFields.containsKey("syncStatus")) {
				baseItem.put("syncStatus", "true");// 默认已同步
			}

			String interId = baseItem.getString(primaryKey);// 主键值

			if (templateService.getItemById(classId, interId) == null) {
				templateService.addItem(classId, baseItem.toJSONString());
			} else {
				templateService.editItem(classId, interId, baseItem.toJSONString());
			}

		}

		return ret;
	}

}
