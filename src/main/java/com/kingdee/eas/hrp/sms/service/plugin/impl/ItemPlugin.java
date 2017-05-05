package com.kingdee.eas.hrp.sms.service.plugin.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.core.Is;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.FormFieldsMapper;
import com.kingdee.eas.hrp.sms.dao.generate.RoleMapper;
import com.kingdee.eas.hrp.sms.dao.generate.UserMapper;
import com.kingdee.eas.hrp.sms.dao.generate.UserTypeMapper;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.model.FormFieldsExample;
import com.kingdee.eas.hrp.sms.model.Role;
import com.kingdee.eas.hrp.sms.model.RoleExample;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.model.UserExample;
import com.kingdee.eas.hrp.sms.model.UserType;
import com.kingdee.eas.hrp.sms.model.UserTypeExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInAdpter;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;
import com.kingdee.eas.hrp.sms.util.Environ;

public class ItemPlugin extends PlugInAdpter {

	@Resource
	private ITemplateService templateService;

	@SuppressWarnings("unchecked")
	@Override
	public PlugInRet beforeDelete(int classId, Map<String, Object> formData, String data) {

		ITemplateService templateService = Environ.getBean(ITemplateService.class);
		// 主表资料描述信息
		FormClass formClass = (FormClass) formData.get("formClass");
		// String primaryKey = formClass.getPrimaryKey();
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
		Map<String, Object> errorMsg = new HashMap<String, Object>();

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
			Map<String, Object> result = templateService.getItems(citedClassId, "", orderBy, 1, 10, 1);
			List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("list");
			for (Map<String, Object> item : items) {
				// 如果此记录被引用，则不删除
				String id = (String) item.get(key);
				if (idList.contains(id)) {
					errorMsg.put(id, id);
					idList.remove(id);
				}
			}

		}
		PlugInRet result = new PlugInRet();
		if (!errorMsg.isEmpty()) {
			result.setCode(501);
			result.setMsg("以下数据已被引用，不能删除");
			result.setData(errorMsg);
		} else {
			result.setCode(200);
			result.setMsg("ok");
			errorMsg.put("-1", "-1");
			result.setData(errorMsg);
		}
		return result;
	}

	@Override
	public PlugInRet afterDelete(int classId, String items) {
		// TODO Auto-generated method stub
		return super.afterDelete(classId, items);
	}

	@Override
	public PlugInRet beforeModify(int classId, String id, Map<String, Object> formData, JSONObject data, int userType) {

		checkMustInput(classId, formData, data, userType);

		checkIfExistRecord(classId, id, formData, data, userType);

		return super.beforeModify(classId, id, formData, data, userType);
	}

	@Override
	public PlugInRet beforeSave(int classId, Map<String, Object> formData, JSONObject data, int userTyepe) {

		checkMustInput(classId, formData, data, userTyepe);

		String id = "-1";

		checkIfExistRecord(classId, id, formData, data, userTyepe);

		return super.beforeSave(classId, formData, data, userTyepe);
	}

	private void checkIfExistRecord(int classId, String id, Map<String, Object> formData, JSONObject data, int userTyepe) {
		SqlSession sqlSession = Environ.getBean(SqlSession.class);

		if (classId == 1001) {

			UserMapper mapper = sqlSession.getMapper(UserMapper.class);

			UserExample example = new UserExample();
			com.kingdee.eas.hrp.sms.model.UserExample.Criteria criteria = example.createCriteria();

			criteria.andNameEqualTo(data.getString("name"));
			criteria.andNumberEqualTo(data.getString("number"));

			criteria.andUserIdNotEqualTo(Integer.parseInt(id));// 排除自身

			List<User> list = mapper.selectByExample(example);
			if (list.size() > 0) {
				User user = list.get(0);
				if (!user.getUserId().equals(id)) {
					throw new PlugInRuntimeException("该用户已存在");
				}
			}

			if (data.getIntValue("type") == 2) {
				if (data.getString("supplier") == null || data.getString("supplier").equals("")) {
					throw new PlugInRuntimeException("业务用户必须选择一个供应商");
				}
			}
		}

		if (classId == 1002) {

			UserTypeMapper mapper = sqlSession.getMapper(UserTypeMapper.class);

			UserTypeExample example = new UserTypeExample();
			com.kingdee.eas.hrp.sms.model.UserTypeExample.Criteria criteria = example.createCriteria();

			criteria.andNameEqualTo(data.getString("name"));
			criteria.andNumberEqualTo(data.getString("number"));

			criteria.andTypeIdNotEqualTo(Integer.parseInt(id));// 排除自身

			List<UserType> list = mapper.selectByExample(example);
			if (list.size() > 0) {
				UserType userType = list.get(0);
				if (!userType.getTypeId().equals(data.getString("typeId"))) {
					throw new PlugInRuntimeException("该用户类别已存在");
				}
			}
		}

		if (classId == 1003) {

			RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);

			RoleExample example = new RoleExample();
			com.kingdee.eas.hrp.sms.model.RoleExample.Criteria criteria = example.createCriteria();

			criteria.andNameEqualTo(data.getString("name"));
			criteria.andNumberEqualTo(data.getString("number"));

			List<Role> list = mapper.selectByExample(example);
			if (list.size() > 0) {
				Role role = list.get(0);
				if (!role.getRoleId().equals(data.getString("roleId"))) {
					throw new PlugInRuntimeException("该角色已存在");
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void checkMustInput(int classId, Map<String, Object> formData, JSONObject data, int userTyepe) {

		boolean flag = false;
		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) formData.get("formFields")).get("0"); // 主表的字段模板
		Set<String> keySet = formFields.keySet();
		StringBuilder errMsg = new StringBuilder();
		for (String key : keySet) {
			flag = false;
			FormFields ff = formFields.get(key);
			int mustInput = ff.getMustInput();
			if (userTyepe == 1) {
				if ((mustInput & 4) == 1 && (mustInput & 8) == 1) {
					flag = true;
				}
			} else {
				if ((mustInput & 1) == 1 && (mustInput & 2) == 1) {
					flag = true;
				}
			}
			if (flag) {
				if (data.get(key) == null || data.get(key).equals("")) {
					errMsg.append(ff.getName()).append(",");
				}
			}
		}
		if (errMsg.length() > 0) {
			throw new PlugInRuntimeException(errMsg.toString() + "为必填值");
		}
	}

}
