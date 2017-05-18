package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.FormFieldsMapper;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.model.FormFieldsExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInAdpter;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;
import com.kingdee.eas.hrp.sms.util.Environ;

public class ItemPlugin extends PlugInAdpter {

	@Resource
	private ITemplateService templateService;

	@Override
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String data, String userType) {

		ITemplateService templateService = Environ.getBean(ITemplateService.class);
		// 装配待删除ID
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));

		// 查找引用待删除资料的模板
		SqlSession sqlSession = Environ.getBean(SqlSession.class);
		FormFieldsMapper mapper = sqlSession.getMapper(FormFieldsMapper.class);
		FormFieldsExample example = new FormFieldsExample();
		com.kingdee.eas.hrp.sms.model.FormFieldsExample.Criteria criteria = example.createCriteria();

		criteria.andLookUpClassIDEqualTo(classId);

		List<FormFields> list = mapper.selectByExample(example);

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

		for (FormFields ff : list) {
			Integer citedClassId = ff.getClassId();
			String key = ff.getKey();

			for (String id : idList) {
				JSONArray conditionArry = new JSONArray();
				JSONObject condition = new JSONObject(true);
				condition.put("fieldKey", key);
				condition.put("logicOperator", "=");
				condition.put("value", id);
				condition.put("needConvert", false);
				conditionArry.add(condition);

				Map<String, Object> result = templateService.getItems(citedClassId, conditionArry.toString(), orderBy, 1, 10, userType, "");

				if ((long) result.get("count") > 0) {
					Map<String, Object> errData = templateService.getItemById(classId, id, userType);
					throw new PlugInRuntimeException("该记录(" + errData.get("number") + ")已被引用，无法删除");
				}
			}
		}

		return super.beforeDelete(classId, formData, data, userType);
	}

	@Override
	public PlugInRet afterDelete(int classId, String items) {
		return super.afterDelete(classId, items);
	}

	@Override
	public PlugInRet beforeModify(int classId, String id, Map<String, Object> formData, JSONObject data, String userType) {

		checkMustInput(classId, formData, data, userType);

		checkIfExistRecord(classId, id, formData, data, userType);

		return super.beforeModify(classId, id, formData, data, userType);
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		checkMustInput(classId, formData, data, userTyepe);

		String id = "-1";

		checkIfExistRecord(classId, id, formData, data, userTyepe);

		return super.beforeSave(classId, formData, data, userTyepe);
	}

	private void checkIfExistRecord(int classId, String id, Map<String, Object> formData, JSONObject data, String userType) {

		// 主表资料描述信息
		FormClass formClass = (FormClass) formData.get("formClass");
		// 主表主键
		String primaryKey = formClass.getPrimaryKey();

		ITemplateService templateService = Environ.getBean(ITemplateService.class);
		JSONArray orderByArray = new JSONArray();
		JSONObject orderByItem = new JSONObject(true);

		orderByItem.put("fieldKey", "number");
		orderByItem.put("orderDirection", "ASC");
		orderByArray.add(orderByItem);

		String orderBy = JSON.toJSONString(orderByArray);

		JSONArray conditionArry = new JSONArray();
		JSONObject condition = new JSONObject(true);
		
		condition.put("andOr", "and");
		condition.put("fieldKey", "number");
		condition.put("logicOperator", "=");
		condition.put("value", data.get("number"));
		conditionArry.add(condition);

		condition = new JSONObject();
		condition.put("fieldKey", primaryKey);
		condition.put("logicOperator", "!=");
		condition.put("value", data.get(primaryKey));
		conditionArry.add(condition);

		Map<String, Object> result = templateService.getItems(classId, conditionArry.toString(), orderBy, 1, 10, userType, "");

		if ((long) result.get("count") > 0) {
			throw new PlugInRuntimeException("该记录已存在");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkMustInput(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		// 用户特殊业务判断，当用户类型是系统用户时，该用户不能选择供应商
		if (classId == 1001) {
			if ("QpXq24FxxE6c3lvHMPyYCxACEAI=".equals(data.getString("type"))) {
				if (data.getString("supplier") != null && !"".equals(data.getString("supplier"))) {
					throw new PlugInRuntimeException("系统用户不能选择供应商");
				}
			}
		}

		// 如果flag是true，表明这个字段需要验证是否非空
		boolean flag = false;
		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) formData.get("formFields")).get("0"); // 主表的字段模板
		Set<String> keySet = formFields.keySet();
		StringBuilder errMsg = new StringBuilder();
		for (String key : keySet) {
			flag = false;
			FormFields ff = formFields.get(key);
			int mustInput = ff.getMustInput();
			if (("QpXq24FxxE6c3lvHMPyYCxACEAI=").equals(userTyepe)) {
				if ((mustInput & 4) == 1 && (mustInput & 8) == 1) {
					flag = true;
				}
			} else {
				if ((mustInput & 1) == 1 && (mustInput & 2) == 1) {
					flag = true;
				}
			}
			if (flag) {
				if (data.getString(key) == null || data.getString(key).equals("")) {
					errMsg.append(ff.getName()).append(",");
				}
			}
		}

		if (errMsg.length() > 0) {
			throw new PlugInRuntimeException(errMsg.toString() + "为必填值");
		}
	}

	@Override
	public PlugInRet beforeQuery(int classId, Map<String, Object> param, String userType) {

		// 当业务用户查询时，相关item需做数据隔离
		List<Integer> classIdList = new ArrayList<Integer>(Arrays.asList(2019, 1001, 1005, 2020, 1019, 1020, 1022, 1020));
		if (classIdList.contains(classId)) {
			if ("B3sMo22ZLkWApjO/oEeDOxACEAI=".equals(userType)) {
				String id = (String) param.get("userId");
				Map<String, Object> user = templateService.getItemById(1001, id, userType);
				String supplierId = (String) user.get("supplier");
				JSONArray conditionArry = new JSONArray();
				JSONObject condition = new JSONObject(true);
				if (classId == 1005)
					condition.put("fieldKey", "id");
				else
					condition.put("fieldKey", "supplier");
				condition.put("logicOperator", "=");
				condition.put("value", supplierId);
				condition.put("needConvert", false);
				conditionArry.add(condition);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("condition", conditionArry.toString());
				PlugInRet ret = new PlugInRet();
				ret.setData(data);
				ret.setCode(200);
				return ret;
			}
		}
		return super.beforeQuery(classId, param, userType);
	}
}
