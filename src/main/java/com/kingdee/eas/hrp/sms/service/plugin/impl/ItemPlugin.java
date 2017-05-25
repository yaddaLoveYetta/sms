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

	// 当业务用户查询时，相关item需做数据隔离
	List<Integer> classIdList = new ArrayList<Integer>(
			Arrays.asList(2019, 2020, 1001, 1005, 3010, 3020, 3030, 1023, 1007));

	@Override
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String data, String userType) {

		ITemplateService templateService = Environ.getBean(ITemplateService.class);
		// 装配待删除ID
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));

		// 检查数据是否为未审核状态
		for (String id : idList) {
			checkIfReview(classId, id, userType);
		}

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

				Map<String, Object> result = templateService.getItems(citedClassId, conditionArry.toString(), orderBy,
						1, 10, userType, "");

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
	public PlugInRet beforeModify(int classId, String id, Map<String, Object> formData, JSONObject data,
			String userType) {

		checkIfReview(classId, id, userType);

		checkMustInput(classId, formData, data, userType);

		if (classId / 100 == 10) {

			checkIfExistRecord(classId, id, formData, data, userType);

		}

		// 如果字段含有同步到HRP的字段syncStatus，设置同步状态
		List<Integer> classIdList = new ArrayList<Integer>(Arrays.asList(1005, 3010, 3020, 3030, 1023, 1007));
		if (classIdList.contains(classId)) {
			if (data.isEmpty()) { // 构造的json为空即同步到HRP的记录需将同步状态标记为已同步
				data.put("syncStatus", "1");
			} else { // 构造的json不为空即为修改记录，需将同步状态标记为未同步
				data.put("syncStatus", "0");
			}
		}

		PlugInRet ret = new PlugInRet();
		ret.setCode(200);
		ret.setData(data);
		return ret;

	}

	private void checkIfReview(int classId, String id, String userType) {

		Map<String, Object> result = templateService.getItemById(classId, id, userType);
		short review;
		if (null == result.get("review")) {
			review = 1;
		} else {
			review = (short) result.get("review");
		}
		if (1 == review) {
			throw new PlugInRuntimeException("记录" + result.get("number") + "已审核，无法进行操作！");
		}
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		checkMustInput(classId, formData, data, userTyepe);

		if (classId / 100 == 10) {

			String id = "-1";
			checkIfExistRecord(classId, id, formData, data, userTyepe);
		}

		return super.beforeSave(classId, formData, data, userTyepe);
	}

	private void checkIfExistRecord(int classId, String id, Map<String, Object> formData, JSONObject data,
			String userType) {

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
		condition.put("fieldKey", "name");
		condition.put("logicOperator", "=");
		condition.put("value", data.get("name"));
		conditionArry.add(condition);

		condition = new JSONObject(true);
		condition.put("andOr", "and");
		condition.put("fieldKey", "number");
		condition.put("logicOperator", "=");
		condition.put("value", data.get("number"));
		conditionArry.add(condition);

		condition = new JSONObject();
		condition.put("fieldKey", primaryKey);
		condition.put("logicOperator", "!=");
		condition.put("value", id);
		conditionArry.add(condition);

		Map<String, Object> result = templateService.getItems(classId, conditionArry.toString(), orderBy, 1, 10,
				userType, "");

		if ((long) result.get("count") > 0) {
			throw new PlugInRuntimeException("该记录已存在");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkMustInput(int classId, Map<String, Object> formData, JSONObject data, String userTyepe) {

		// 用户特殊业务判断，当用户类型是系统用户时，该用户不能选择供应商
		if (classId == 1001) {
			if ("QpXq24FxxE6c3lvHMPyYCxACEAI=".equals(data.getString("type"))) {
				if (data.getString("supplier") != null && !"".equals(data.getString("supplier"))
						&& !"0".equals(data.getString("supplier"))) {
					throw new PlugInRuntimeException("系统用户不能选择供应商");
				}
			}
		}
		// 主表资料描述信息
		FormClass formClass = (FormClass) formData.get("formClass");
		String primaryKey = formClass.getPrimaryKey();

		// 如果flag是true，表明这个字段需要验证是否非空
		boolean flag = false;
		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) formData
				.get("formFields")).get("0"); // 主表的字段模板
		formFields.remove(primaryKey);
		Set<String> keySet = formFields.keySet();
		StringBuilder errMsg = new StringBuilder();
		for (String key : keySet) {
			flag = false;
			FormFields ff = formFields.get(key);
			int mustInput = ff.getMustInput();
			if (("QpXq24FxxE6c3lvHMPyYCxACEAI=").equals(userTyepe)) {
				if ((mustInput & 1) == 1 && (mustInput & 2) == 2) {
					flag = true;
				}
			} else {
				if ((mustInput & 4) == 4 && (mustInput & 8) == 8) {
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

		return super.beforeQuery(classId, param, userType);
	}

	@Override
	public String getConditions(int classId, Map<String, Object> formData, String conditon, String userType,
			String userId) {
		if (userId == "")
			return conditon;
		// 当业务用户查询时，相关item需做数据隔离，增加condition条件
		if (classIdList.contains(classId)) {
			if ("B3sMo22ZLkWApjO/oEeDOxACEAI=".equals(userType)) {
				Map<String, Object> user = templateService.getItemById(1001, userId, userType);
				String supplierId = (String) user.get("supplier");
				JSONObject con = new JSONObject(true);
				if (classId == 1005) {
					con.put("fieldKey", "id");
				} else {
					con.put("fieldKey", "supplier");
				}
				con.put("logicOperator", "=");
				con.put("value", supplierId);
				con.put("needConvert", false);
				JSONArray conditionArray = JSONArray.parseArray(conditon);
				if (null == conditionArray) {
					conditionArray = new JSONArray();
				}
				conditionArray.add(con);

				return conditionArray.toString();
			}
		}
		return conditon;
	}
}
