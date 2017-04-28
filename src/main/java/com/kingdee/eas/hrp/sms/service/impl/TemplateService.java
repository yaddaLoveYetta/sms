package com.kingdee.eas.hrp.sms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.plugin.PluginException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.customize.TemplateDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.FormClassMapper;
import com.kingdee.eas.hrp.sms.dao.generate.FormEntriesMapper;
import com.kingdee.eas.hrp.sms.dao.generate.FormFieldsMapper;
import com.kingdee.eas.hrp.sms.domain.DataTypeeEnum;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormClassExample;
import com.kingdee.eas.hrp.sms.model.FormClassExample.Criteria;
import com.kingdee.eas.hrp.sms.model.FormEntries;
import com.kingdee.eas.hrp.sms.model.FormEntriesExample;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.model.FormFieldsExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInFactory;
import com.kingdee.eas.hrp.sms.service.plugin.PlugInRet;
import com.kingdee.eas.hrp.sms.util.ValidateUtil;

@Service
public class TemplateService extends BaseService implements ITemplateService {

	@Override
	public Map<String, Object> getFormTemplate(int classId, int type) {

		Map<String, Object> retMap = new LinkedHashMap<String, Object>();

		// 主表模板
		Map<String, Object> formFields = new LinkedHashMap<String, Object>();
		// 子表模板
		Map<String, Object> formEntries = new LinkedHashMap<String, Object>();
		// 主表单据类别描述
		FormClass formClass = null;

		// 获取单据类别描述信息
		FormClassMapper classMapper = sqlSession.getMapper(FormClassMapper.class);
		FormClassExample classExample = new FormClassExample();
		Criteria classCriteria = classExample.createCriteria();
		classCriteria.andClassIdEqualTo(classId);
		List<FormClass> classByExample = classMapper.selectByExample(classExample);

		if (classByExample.isEmpty() || classByExample.size() > 1) {
			throw new BusinessLogicRunTimeException("基础资料模板不存在或不唯一!");
		}

		formClass = classByExample.get(0);

		// 获取单据模板page=0表头

		FormFieldsMapper fieldsMapper = sqlSession.getMapper(FormFieldsMapper.class);

		FormFieldsExample fieldsExample = new FormFieldsExample();
		com.kingdee.eas.hrp.sms.model.FormFieldsExample.Criteria fieldsCriteria = fieldsExample.createCriteria();

		fieldsCriteria.andClassIdEqualTo(classId);
		fieldsCriteria.andPageEqualTo(0);

		if (type == 1) {
			// 后端构建查询脚本时调用
			fieldsExample.setOrderByClause("page, ctlIndex");

		} else {
			// 前端处理显示顺序时调用
			fieldsExample.setOrderByClause("page, [index]");
		}

		List<FormFields> fieldsByExample = fieldsMapper.selectByExample(fieldsExample);

		// 子表单据类别描述
		FormEntriesMapper entriesMapper = sqlSession.getMapper(FormEntriesMapper.class);
		FormEntriesExample entriesExample = new FormEntriesExample();
		com.kingdee.eas.hrp.sms.model.FormEntriesExample.Criteria entriesCriteria = entriesExample.createCriteria();
		entriesCriteria.andClassIdEqualTo(classId);
		List<FormEntries> entriesByExample = entriesMapper.selectByExample(entriesExample);

		// 打包表头字段模板
		Map<String, FormFields> formFields0 = new LinkedHashMap<String, FormFields>();
		for (FormFields item : fieldsByExample) {
			formFields0.put(item.getKey(), item);
		}

		formFields.put("0", formFields0);

		// 循环打包子表字段模板
		for (FormEntries item : entriesByExample) {

			String entryIndex = item.getEntryIndex().toString();

			formEntries.put(entryIndex, item);

			fieldsCriteria.andPageEqualTo(item.getEntryIndex());

			List<FormFields> entryIndexFieldsByExample = fieldsMapper.selectByExample(fieldsExample);

			Map<String, FormFields> formFieldsEntryIndex = new LinkedHashMap<String, FormFields>();

			for (FormFields fields : entryIndexFieldsByExample) {
				formFieldsEntryIndex.put(fields.getKey(), fields);
			}

			formFields.put(entryIndex, formFieldsEntryIndex);
		}

		retMap.put("formClass", formClass);
		retMap.put("formEntries", formEntries);
		retMap.put("formFields", formFields);

		return retMap;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getItems(int classId, String condition, String orderBy, int pageNo, int pageSize, int userType) {

		Map<String, Object> ret = new HashMap<String, Object>();

		String conditionString = condition; // 过滤条件
		String orderByString = orderBy; // 排序字段

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);

		// 主表字段模板
		Map<String, Object> formFields0 = (Map<String, Object>) ((Map<String, Object>) template.get("formFields")).get("0"); // 主表的字段模板
		// 第一个子表字段模板(如果有)
		Map<String, Object> formFields1 = new HashMap<String, Object>(); // 第一个子表的字段模板
		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		if (null == formClass) {

			throw new BusinessLogicRunTimeException("资料模板不存在");
		}

		// 主表表名
		String primaryTableName = formClass.getTableName();
		// 主表主键
		String primaryKey = formClass.getPrimaryKey();

		boolean isChildTableExist = false; // 指示是否存在子表，存在时主表需要关联第一个子表查询

		if (!formEntries.isEmpty()) {
			// 存在关联字表-只关联第一个子表查询
			isChildTableExist = true;
			formFields1 = (Map<String, Object>) ((Map<String, Object>) template.get("formFields")).get("1");
		}

		// 动态构建select语句

		String select = ""; // 查询字段
		String from = "";// 查询表
		String where = ""; // 查询条件
		String orderByStr = ""; // 结果排序

		Map<String, Object> statement = getStatement(classId, userType);

		if (conditionString != null && !conditionString.equals("")) {

			// 客户端传入了查询条件
			JSONArray conditionArray = JSONArray.parseArray(conditionString);

			where = getWhereStr(classId, conditionArray); // 获取where条件

		}

		if (orderByString != null && !orderByString.equals("")) {

			// 客户端传入了排序字段
			JSONArray orderByArray = JSONArray.parseArray(orderByString);

			orderByStr = getOrderByStr(classId, orderByArray); // 获取where条件

		}

		if (!ValidateUtil.isMatchParenTheses(where.toString())) {
			throw new BusinessLogicRunTimeException("参数错误：请正确匹配condition中的左右括号（）！");
		}

		select = (String) statement.get("select");
		from = (String) statement.get("from");

		Map<String, Object> statementParam = new HashMap<String, Object>();
		statementParam.put("select", select.toString());
		statementParam.put("from", from.toString());
		statementParam.put("where", where);
		statementParam.put("orderby", orderByStr);
		statementParam.put("pageSize", pageSize);
		statementParam.put("pageNo", pageNo);

		TemplateDaoMapper templateDaoMapper = sqlSession.getMapper(TemplateDaoMapper.class);

		if (pageNo == 1) {
			PageHelper.startPage(pageNo, pageSize, true);
		}

		List<Map<String, Object>> data = templateDaoMapper.getItems(statementParam);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		// 将记录转换成返回接口的格式，将主表关联多行子表记录时，子表记录整合到返回结构"entry"中
		for (Map<String, Object> item : data) {
			// 循环每一行
			Map<String, Object> head = new HashMap<String, Object>();// 主表所有字段
			Map<String, Object> entries = new HashMap<String, Object>(); // 所有子表entry

			List<Map<String, Object>> formEntryRows = new ArrayList<Map<String, Object>>(); // 第一个子表所有行
			Map<String, Object> formEntryRow = new HashMap<String, Object>();// 子表每一行的元素

			for (Iterator<Map.Entry<String, Object>> it = item.entrySet().iterator(); it.hasNext();) {
				// 循环行中的列
				Map.Entry<String, Object> column = it.next();

				String cName = column.getKey();
				Object cValue = column.getValue();

				String cNameTrueKey = cName; // 真实的模板key

				if (cName.contains("_")) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					cNameTrueKey = cName.substring(0, cName.indexOf("_"));
				}

				if (formFields0.containsKey(cNameTrueKey)) {
					// formClass即主表字段
					// formClass.put(cNameTrueKey, cValue);

					// if (!cNameTrueKey.equals(cName)) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					head.put(cName, item.get(cName));
					// }

				} else if (isChildTableExist && formFields1.containsKey(cNameTrueKey)) {
					// formEntries1即第一个子表字段
					// formEntryRow.put(cNameTrueKey, cValue);

					// if (!cNameTrueKey.equals(cName)) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					formEntryRow.put(cName, item.get(cName));
					// }
				}
			}

			if (isChildTableExist) {
				formEntryRows.add(formEntryRow); // 这里formEntryRows也就一行，为了构造{[]}的结构
				entries.put("1", formEntryRows); // 第一个子表数据
			}

			head.put("entry", entries);// 将子表已关键字"entry"作为key插入到formClass中

			Map<String, Object> keyInList = isKeyInList(returnList, primaryKey, head.get(primaryKey));

			if (keyInList != null) {
				// 已存在该条记录，增加子表行
				((List<Map<String, Object>>) ((Map<String, Object>) keyInList.get("entry")).get("1")).add(formEntryRow);
			} else {
				// 不存在该行记录，新增行
				returnList.add(head);
			}
		}

		ret.put("list", returnList);

		if (pageNo == 1) {

			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(data);

			ret.put("count", pageInfo.getTotal());
		}

		return ret;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getItemById(Integer classId, Integer id, int userType) {

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);

		// 主表字段模板
		Map<String, Object> formFields0 = (Map<String, Object>) ((Map<String, Object>) template.get("formFields")).get("0"); // 主表的字段模板
		// 第一个子表字段模板(如果有)
		Map<String, Object> formFields1 = new HashMap<String, Object>(); // 第一个子表的字段模板
		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		if (null == formClass) {

			throw new BusinessLogicRunTimeException("资料模板不存在");
		}

		// 主表表名
		String primaryTableName = formClass.getTableName();
		// 主表主键
		String primaryKey = formClass.getPrimaryKey();

		boolean isChildTableExist = false; // 指示是否存在子表，存在时主表需要关联第一个子表查询

		if (!formEntries.isEmpty()) {
			// 存在关联字表-只关联第一个子表查询
			isChildTableExist = true;
			formFields1 = (Map<String, Object>) ((Map<String, Object>) template.get("formFields")).get("1");
		}

		// 动态构建select语句

		String select = ""; // 查询字段
		String from = "";// 查询表
		String where = ""; // 查询条件

		Map<String, Object> statement = getStatement(classId, userType);

		select = (String) statement.get("select");
		from = (String) statement.get("from");

		StringBuilder sbWhere = new StringBuilder(); // 查询条件
		sbWhere.append("WHERE").append(System.getProperty("line.separator")).append(primaryTableName).append("." + primaryKey + "=").append(id);

		where = sbWhere.toString();

		Map<String, Object> statementParam = new HashMap<String, Object>();
		statementParam.put("select", select.toString());
		statementParam.put("from", from.toString());
		statementParam.put("where", where);

		TemplateDaoMapper templateDaoMapper = sqlSession.getMapper(TemplateDaoMapper.class);

		List<Map<String, Object>> data = templateDaoMapper.getItems(statementParam);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		// 将记录转换成返回接口的格式，将主表关联多行子表记录时，子表记录整合到返回结构"entry"中
		for (Map<String, Object> item : data) {
			// 循环每一行
			Map<String, Object> head = new HashMap<String, Object>();// 主表所有字段
			Map<String, Object> entries = new HashMap<String, Object>(); // 所有子表entry

			List<Map<String, Object>> formEntryRows = new ArrayList<Map<String, Object>>(); // 第一个子表所有行
			Map<String, Object> formEntryRow = new HashMap<String, Object>();// 子表每一行的元素

			for (Iterator<Map.Entry<String, Object>> it = item.entrySet().iterator(); it.hasNext();) {
				// 循环行中的列
				Map.Entry<String, Object> column = it.next();

				String cName = column.getKey();
				Object cValue = column.getValue();

				String cNameTrueKey = cName; // 真实的模板key

				if (cName.contains("_")) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					cNameTrueKey = cName.substring(0, cName.indexOf("_"));
				}

				if (formFields0.containsKey(cNameTrueKey)) {
					// formClass即主表字段
					// formClass.put(cNameTrueKey, cValue);

					// if (!cNameTrueKey.equals(cName)) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					head.put(cName, item.get(cName));
					// }

				} else if (isChildTableExist && formFields1.containsKey(cNameTrueKey)) {
					// formEntries1即第一个子表字段
					// formEntryRow.put(cNameTrueKey, cValue);

					// if (!cNameTrueKey.equals(cName)) {
					// 关联查询字段时携带的_DspName,_NmbName等模板之外的key
					formEntryRow.put(cName, item.get(cName));
					// }
				}
			}

			if (isChildTableExist) {
				formEntryRows.add(formEntryRow); // 这里formEntryRows也就一行，为了构造{[]}的结构
				entries.put("1", formEntryRows); // 第一个子表数据
			}

			head.put("entry", entries);// 将子表已关键字"entry"作为key插入到formClass中

			Map<String, Object> keyInList = isKeyInList(returnList, primaryKey, head.get(primaryKey));

			if (keyInList != null) {
				// 已存在该条记录，增加子表行
				((List<Map<String, Object>>) ((Map<String, Object>) keyInList.get("entry")).get("1")).add(formEntryRow);
			} else {
				// 不存在该行记录，新增行
				returnList.add(head);
			}
		}

		if (!returnList.isEmpty()) {
			// 只可能是一条记录
			return returnList.get(0);
		}

		return null;

	}

	@Override
	public Map<String, FormFields> getFormFields(int classId, int page) {

		Map<String, FormFields> retMap = new LinkedHashMap<String, FormFields>();

		// 获取单据模板page=0表头

		FormFieldsMapper fieldsMapper = sqlSession.getMapper(FormFieldsMapper.class);

		FormFieldsExample fieldsExample = new FormFieldsExample();
		com.kingdee.eas.hrp.sms.model.FormFieldsExample.Criteria fieldsCriteria = fieldsExample.createCriteria();

		fieldsCriteria.andClassIdEqualTo(classId);

		if (page != -1) {
			fieldsCriteria.andPageEqualTo(page);
		}

		fieldsExample.setOrderByClause("page, [index]");

		List<FormFields> fieldsByExample = fieldsMapper.selectByExample(fieldsExample);

		// 打包字段模板
		for (FormFields item : fieldsByExample) {
			retMap.put(item.getKey(), item);
		}

		return retMap;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int addItem(Integer classId, String data) {

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);

		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) template.get("formFields")).get("0"); // 主表的字段模板
		// 第一个子表字段模板(如果有)
		// Map<String, Object> formFields1 = new HashMap<String, Object>(); // 第一个子表的字段模板
		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		JSONObject json = JSONObject.parseObject(data);

		// 保存前插件事件
		PlugInFactory factory = new PlugInFactory(classId);
		PlugInRet result = factory.beforeSave(classId, template, json);

		if (result != null && result.getCode() != 200) {
			throw new PluginException(result.getMsg());
		}

		// 模板参数
		String primaryTableName = formClass.getTableName();

		// 准备保存模板
		Map<String, Object> statement = prepareAddMap(json, formFields, primaryTableName);

		// 插入基础资料
		// int id = baseItemDao.addItem(statement);

		TemplateDaoMapper templateDaoMapper = sqlSession.getMapper(TemplateDaoMapper.class);

		int id = templateDaoMapper.add(statement);

		// 处理分录数据
		handleEntryData(classId, id, json);

		return id;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void editItem(Integer classId, Integer id, String data) {

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);

		// 主表字段模板
		Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) template.get("formFields")).get("0"); // 主表的字段模板

		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");

		// 模板参数

		JSONObject json = JSONObject.parseObject(data);

		// 修改前插件事件
		PlugInFactory factory = new PlugInFactory(classId);
		PlugInRet result = factory.beforeModify(classId, json);

		if (result != null && result.getCode() != 200) {
			throw new PlugInRuntimeException(result.getMsg());
		}

		String primaryTableName = formClass.getTableName();
		String primaryKey = formClass.getPrimaryKey();

		// 准备保存模板
		Map<String, Object> statement = prepareEditMap(json, formFields, primaryTableName, primaryKey, id);

		// 修改基础资料
		TemplateDaoMapper templateDaoMapper = sqlSession.getMapper(TemplateDaoMapper.class);
		templateDaoMapper.edit(statement);

		// 处理分录数据
		handleEntryData(classId, id, json);

	}

	/**
	 * 判断List<Map<String, Object>>中的Map中key为targetKey的元素的值是否为targetValue，是则并返回该元素，否则返回null
	 * 
	 * @param list
	 *            List<Map<String, Object>>
	 * @param targetKey
	 * @param targetValue
	 * @return
	 */
	private Map<String, Object> isKeyInList(List<Map<String, Object>> list, String targetKey, Object targetValue) {

		for (Map<String, Object> map : list) {
			if (map.containsKey(targetKey) && map.get(targetKey).equals(targetValue)) {
				return map;
			}
		}
		return null;
	}

	/**
	 * 构建查询脚本
	 * 
	 * @Title getStatement
	 * @param classId
	 * @param userType
	 * @return Map<String,Object>
	 * @throws SQLException
	 * @date 2017-04-20 14:15:58 星期四
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getStatement(int classId, int userType) {

		boolean isChildTableExist = false; // 指示是否存在子表，存在时主表需要关联第一个子表查询

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);
		// 所有字段模板
		Map<String, Object> formFieldsAll = (Map<String, Object>) template.get("formFields");
		// 主表资料描述信息
		FormClass formClass = (FormClass) template.get("formClass");
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		Map<String, String> dbDelimiter = getDBDelimiter();

		String bDelimiter = dbDelimiter.get("bDelimiter");// 数据库字段-关键字处理
		String eDelimiter = dbDelimiter.get("eDelimiter");

		if (null == formClass) {
			throw new BusinessLogicRunTimeException("资料模板不存在");
		}

		String primaryTableName = formClass.getTableName();
		String primaryKey = formClass.getPrimaryKey();

		String childTableName = "";
		String foreignKey = "";

		if (!formEntries.isEmpty()) {
			// 存在关联字表-只关联第一个子表查询
			childTableName = (String) ((Map<String, Object>) formEntries.get("1")).get("tableName"); // 子表物理表名
			foreignKey = (String) ((Map<String, Object>) formEntries.get("1")).get("foreignKey");// 子表与主表关联字段(INNER
																									// JOIN)

			if ("".equals(childTableName) || "".equals(foreignKey)) {
				// 存在子表但子表FormEntries中配置错误
				throw new BusinessLogicRunTimeException("子表FormEntries中配置错误");
			}

			isChildTableExist = true;
		}

		if (formFieldsAll.isEmpty()) {
			throw new BusinessLogicRunTimeException("没有模板数据");
		}

		StringBuilder sbSelect = new StringBuilder();
		StringBuilder sbFrom = new StringBuilder();

		String separator = System.getProperty("line.separator");

		sbSelect.append("SELECT").append(separator);
		sbFrom.append("FROM " + primaryTableName).append(separator);

		if (isChildTableExist) {
			sbFrom.append(
					String.format("INNER JOIN %s ON %s.%s%s%s = %s.%s%s%s", childTableName, bDelimiter, childTableName, eDelimiter, foreignKey, primaryTableName, bDelimiter, primaryKey, eDelimiter))
					.append(separator);
		}

		// 用户类别1：系统用户，2供应商用户
		int displayTypeList = 0;
		int displayTypeAdd = 0;
		int displayTypeEdit = 0;
		if (userType == 1) {
			displayTypeList = 1;
			displayTypeAdd = 4;
			displayTypeEdit = 16;
		} else if (userType == 2) {
			displayTypeList = 2;
			displayTypeAdd = 8;
			displayTypeEdit = 32;
		}

		for (Iterator<String> iterator = formFieldsAll.keySet().iterator(); iterator.hasNext();) {

			String tableIndex = iterator.next();
			Map<String, Object> formFields = (Map<String, Object>) formFieldsAll.get(tableIndex);

			String formFieldLinkedTable = ""; // 当前模板关联的物理表

			if ("0".equals(tableIndex)) {
				// 主表
				formFieldLinkedTable = primaryTableName;
			} else if ("1".equals(tableIndex)) {
				formFieldLinkedTable = childTableName;
			} else {
				// 主表不支持关联多个子表查询
				continue;
			}

			for (Iterator<String> it = formFields.keySet().iterator(); it.hasNext();) {

				String fieldKey = it.next();
				FormFields formField = (FormFields) formFields.get(fieldKey);

				String joinType = formField.getJoinType();
				if (joinType == null || joinType.trim().equals("")) {
					joinType = "INNER JOIN"; // 默认 INNER JOIN
				}

				joinType = " " + joinType.trim() + " "; // 两边加一空格，防止模板配置时两边无空格，链接脚本错误

				String filter = formField.getFilter(); // 过滤条件-用于关联表时的附加条件

				Integer page = formField.getPage();
				// Integer FItemClassID = (Integer) formField.get("FItemClassID");
				String name = formField.getName();
				String sqlColumnName = formField.getSqlColumnName();
				String key = formField.getKey();
				Integer dataType = formField.getDataType();
				Integer index = formField.getIndex();
				Integer display = formField.getDisplay();
				Integer lookUpType = formField.getLookUpType();
				Integer lookUpClassId = formField.getLookUpClassID();
				String srcTable = formField.getSrcTable();
				String srcTableAlisAs = formField.getSrcTableAlisAs();
				String srcField = formField.getSrcField();
				String disPlayField = formField.getDisPlayField();
				// String disPlayFieldAlisAs = formField.getdis
				String disPlayNum = formField.getDisPlayNum();
				String srcTableAlis = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;

				if (display > 0 && ((display & displayTypeList) != displayTypeList && (display & displayTypeAdd) != displayTypeAdd && (display & displayTypeEdit) != displayTypeEdit)) {
					// 因为该函数需要兼容列表及编辑时查询，此处权限放开： 具有列表显示OR新增时显示OR编辑时显示的任意权限即可
					continue; // 当前用户类别无权限查看该字段
				}

				if (lookUpType != null && (lookUpType == 1 || lookUpType == 2)) {
					// 基础资料引用类型
					// 强制显示关联字段名称
					sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", formFieldLinkedTable, bDelimiter, sqlColumnName, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);

					sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, disPlayField, eDelimiter, bDelimiter, key + "_DspName", eDelimiter)).append(separator);

					if (disPlayNum != null && !disPlayNum.trim().equals("")) {
						// 代码显示字段
						sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, disPlayNum, eDelimiter, bDelimiter, key + "_NmbName", eDelimiter)).append(separator);
					}

					// from 中同时增加关联表
					sbFrom.append(joinType).append(srcTable);

					// 关联表的别名
					if (srcTableAlisAs != null && !srcTableAlisAs.equals("")) {
						sbFrom.append(" as " + srcTableAlisAs);
					}

					sbFrom.append(String.format(" ON %s.%s%s%s = %s.%s%s%s", formFieldLinkedTable, bDelimiter, sqlColumnName, eDelimiter, srcTableAlis, bDelimiter, srcField, eDelimiter))
							.append(separator);

					if (filter != null && !filter.trim().equals("")) {
						// 表链接有附加条件
						sbFrom.append(filter).append(separator);
					}

				} else if (lookUpType != null && lookUpType == 3) {

					// 引用基础资料的附加属性
					// lookUpType == 3 即引用基础资料属性的模板中，disPlayField的配置统一认为是被引用基础资料模板中的key，需要二次验证引用资料模板确认查询字段

					// 举例：目标，在车辆信息中显示其车辆类别的车辆付费类型
					// 基础资料车辆信息中引用另一个基础资料车辆类别，还需要显示基础资料车辆类别的另一个属性"车辆付费类型"，而"车辆付费类型"在车辆类别模板中又是辅助资料引用类型，
					// 此时在车辆信息的模板中配置携带车辆类别基础资料的附属属性"车辆付费类型"的模板时，模板中disPlayField应配置为"车辆类别"模板中"车辆付费类型"的key(payType),
					// srcField应该配置为"车辆类别"表与辅助资料的关联字段(即车辆类别表t_CarType与辅助资料车辆付费类型的关联字段payType),
					// srcTableAlisAs必须配置，可随意，但需保证在车辆信息模板中不重复。

					FormFields lookUpTypeformField = getFormField(lookUpClassId, disPlayField);

					String nameEx = lookUpTypeformField.getName();
					Integer lookUpTypeEx = lookUpTypeformField.getLookUpType();
					String joinTypeEx = lookUpTypeformField.getJoinType();
					String srcTableEx = lookUpTypeformField.getSrcTable();
					String srcFieldEx = lookUpTypeformField.getSrcField();

					if (lookUpTypeEx != null && lookUpTypeEx > 0) {
						// 基础资料的附加属性又是引用类型的情况--取显示字段并关联表

						sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, nameEx, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);

						// from 中同时增加关联表
						sbFrom.append(joinTypeEx).append(srcTableEx);

						// 关联表的别名

						sbFrom.append(" as " + srcTableAlis);

						sbFrom.append(String.format(" ON %s.%s%s%s = %s.%s%s%s", srcTableEx, bDelimiter, srcFieldEx, eDelimiter, srcTableAlis, bDelimiter, srcFieldEx, eDelimiter)).append(separator);
					} else {
						// 普通属性
						sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, disPlayField, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);
					}

				} else if (lookUpType != null && lookUpType == 4) {
					// 普通引用-引用其他表数据
					// sbSelect.append(String.format("%s.%s AS %s,", primaryTableName, FSQLColumnName, FKey))
					// .append(separator);

					if (dataType != null && dataType == 2) {
						// 文本类的关联字段，未防止关联表中无记录，此处取主表字段值-如订单查询FCarNo字段取数
						sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", formFieldLinkedTable, bDelimiter, sqlColumnName, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);
					} else {
						sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, disPlayField, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);
					}

					// from 中同时增加关联表
					sbFrom.append(joinType).append(srcTable);

					// 关联表的别名
					if (srcTableAlisAs != null && !srcTableAlisAs.equals("")) {
						sbFrom.append(" as " + srcTableAlisAs);
					}

					sbFrom.append(String.format(" ON %s.%s%s%s = %s.%s%s%s", formFieldLinkedTable, bDelimiter, sqlColumnName, eDelimiter, srcTableAlis, bDelimiter, srcField, eDelimiter))
							.append(separator);
				} else if (lookUpType != null && lookUpType == 5) {

					// 普通引用其他表的其他字段-主要为了避免为4即引用他表数据时，需引用多个字段时关联表重复问题。依附于=4时存在,即模板中肯定存在FLookUpType=4的字段模板

					sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", srcTableAlis, bDelimiter, disPlayField, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);

				} else {
					sbSelect.append(String.format("%s.%s%s%s AS %s%s%s,", formFieldLinkedTable, bDelimiter, sqlColumnName, eDelimiter, bDelimiter, key, eDelimiter)).append(separator);
				}
			}
		}

		Map<String, Object> statement = new HashMap<String, Object>();
		String select = sbSelect.toString().trim();
		select = select.substring(0, select.length() - 1);
		String from = sbFrom.toString().trim();

		statement.put("select", select);
		statement.put("from", from);

		return statement;
	}

	/**
	 * 获取数据库字段关键字处理符号
	 * 
	 * @Title getDBDelimiter
	 * @return String
	 * @date 2017-04-21 15:48:28 星期五
	 */
	private Map<String, String> getDBDelimiter() {

		Map<String, String> ret = new HashMap<String, String>() {

			private static final long serialVersionUID = -2157281653097860908L;

			{
				put("bDelimiter", "");
				put("eDelimiter", "");
			}
		};
		Connection connection = null;

		SqlSessionTemplate st = (SqlSessionTemplate) sqlSession;
		// Connection connection = SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(),
		// st.getPersistenceExceptionTranslator()).getConnection();
		connection = st.getSqlSessionFactory().openSession().getConnection();
		try {
			String dbName = connection.getMetaData().getDatabaseProductName();

			if (dbName.contains("Microsoft")) {
				// Microsoft SQL Server
				ret.put("bDelimiter", "[");
				ret.put("eDelimiter", "]");

			} else if (dbName.contains("MySql")) {
				// MySql
				ret.put("bDelimiter", "`");
				ret.put("eDelimiter", "`");

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return ret;

	}

	/**
	 * 解析查询条件
	 * 
	 * @Title getWhereStr
	 * @param classId
	 * @param conditoinArray
	 * @return String 查询条件语句
	 * @date 2017-04-20 14:25:15 星期四
	 */
	@SuppressWarnings("unchecked")
	private String getWhereStr(Integer classId, JSONArray conditoinArray) {

		StringBuilder sbWhere = new StringBuilder();
		String separator = System.getProperty("line.separator");

		// 基础资料模板
		Map<String, Object> templateMap = getFormTemplate(classId, 1);// template
		// 所有字段模板
		Map<String, FormFields> itemClassTemplateMap = getFormFields(classId, -1);
		// 主表资料描述信息
		FormClass formClass = (FormClass) templateMap.get("formClass");// formClass
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) templateMap.get("formEntries");// formEntries

		if (null == formClass) {
			throw new BusinessLogicRunTimeException("没有模板数据");
		}

		Map<String, String> dbDelimiter = getDBDelimiter();

		String bDelimiter = dbDelimiter.get("bDelimiter");// 数据库字段-关键字处理
		String eDelimiter = dbDelimiter.get("eDelimiter");

		String primaryTableName = formClass.getTableName();
		String primaryKey = formClass.getPrimaryKey();
		String childTableName = "";
		String foreignKey = "";

		if (!formEntries.isEmpty()) {

			// 存在关联字表-只关联第一个子表查询
			childTableName = (String) ((Map<String, Object>) formEntries.get("1")).get("tableName"); // 子表物理表名
			foreignKey = (String) ((Map<String, Object>) formEntries.get("1")).get("foreignKey");// 子表与主表关联字段(INNER JOIN

			if ("".equals(childTableName) || "".equals(foreignKey)) {
				// 存在子表但子表FormEntries中配置错误
				throw new BusinessLogicRunTimeException("没有模板数据");
			}

		}

		sbWhere.append("WHERE");

		for (int i = 0; i < conditoinArray.size(); i++) {

			JSONObject condition = conditoinArray.getJSONObject(i);

			String andOr = "AND";// AND OR 条件链接符号-默认AND

			if (condition.containsKey("andOr")) {
				andOr = condition.getString("andOr");
			}

			String leftParenTheses = "("; // 左括号-可能有多个，如 "(("，甚至"((("等复杂查询,默认"("

			if (condition.containsKey("leftParenTheses")) {
				leftParenTheses = condition.getString("leftParenTheses");
			}

			String fieldKey = "";// 比较字段名

			if (condition.containsKey("fieldKey")) {
				fieldKey = condition.getString("fieldKey");
			} else {
				throw new BusinessLogicRunTimeException("参数错误：condition必须包括fieldKey");
			}

			String logicOperator = "="; // 比较符号

			if (condition.containsKey("logicOperator")) {
				logicOperator = condition.getString("logicOperator");
			} else {
				throw new BusinessLogicRunTimeException("参数错误：condition必须包括logicOperator");
			}

			String value = ""; // 比较值

			if (condition.containsKey("value")) {
				value = condition.getString("value");
				value = handleSqlInjection(value);
			} else {
				throw new BusinessLogicRunTimeException("参数错误：condition必须包括value");
			}

			String rightParenTheses = ")"; // 右括号

			if (condition.containsKey("rightParenTheses")) {
				rightParenTheses = condition.getString("rightParenTheses");
			}

			boolean needConvert = true;// 是否需要转换条件字段，用于传入引用他表字段时过滤，例如传入引用基础资料key是否需要转换为名称条件，用户输入时通常需要转换成名称查询，而代码中调用不需要转换，直接用ID匹配

			if (condition.containsKey("needConvert")) {
				needConvert = condition.getBoolean("needConvert");
			}

			if (!itemClassTemplateMap.containsKey(fieldKey)) {
				// 没有定义模板-忽略
				continue;
			}

			@SuppressWarnings("unchecked")

			FormFields formfield = itemClassTemplateMap.get(fieldKey);

			Integer page = (Integer) formfield.getPage();
			String sqlColumnName = formfield.getSqlColumnName();
			Integer lookUpType = formfield.getLookUpType();
			Integer lookUpClassID = formfield.getLookUpClassID();
			String srcTable = formfield.getSrcTable();
			String srcTableAlisAs = formfield.getSrcTableAlisAs();
			String disPlayField = formfield.getDisPlayField();
			String srcField = formfield.getSrcField();
			Integer dataType = formfield.getDataType();

			String formFieldLinkedTable = ""; // 确定当前字段是属于哪个表

			if (page == 0) {
				formFieldLinkedTable = primaryTableName;
			} else if (page == 1) {
				formFieldLinkedTable = childTableName;
			} else {
				// 主表不支持关联多个子表查询
				continue;
			}

			String tableName = formFieldLinkedTable;
			String fieldName = sqlColumnName;

			DataTypeeEnum dataTypeeEnum = DataTypeeEnum.getTypeEnum(dataType);

			if (needConvert && lookUpType > 0) {
				// 需要转换为名称查询的引用类型的查询条件，dataType可能不是文本类型，但条件值是文本，需要文本格式化，此处修正值格式化类型
				dataTypeeEnum = DataTypeeEnum.TEXT;
			}

			switch (dataTypeeEnum) {

			case NUMBER:
				// 一般数字类型的不可能用like，但考虑到一种情况：根据FPark来搜索t_Park表的FName
				if (logicOperator.equalsIgnoreCase("like")) {
					value = "'%" + value + "%'";
					break;
				}
				break;
			case TEXT:
				if (logicOperator.equalsIgnoreCase("like")) {
					value = "'%" + value + "%'";
					break;
				}
				value = String.format("'%s'", value);
				break;
			case TIME:
				if (logicOperator.equalsIgnoreCase("<=")) {
					value = value + " 23:59:59"; // 小于等于结束时间
				}
				value = String.format("'%s'", value);
				break;
			case BOOLEAN:
				break;
			default:
				break;
			}
			if (lookUpType != null && (lookUpType == 1 || lookUpType == 2)) {
				// 基础资料-辅助资料引用类型

				// 引用字段查询-使用关联表显示字段作为条件
				tableName = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;

				if (needConvert) {
					fieldName = disPlayField;
				} else {
					fieldName = srcField;
				}

			} else if (lookUpType > 0 && lookUpType == 3) {

				// 引用字段查询-使用关联表显示字段作为条件
				tableName = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;

				// 携带基础资料属性的字段过滤
				// FLookUpType == 3 即引用基础资料属性的模板中，FDisPlayField的配置统一认为是引用基础资料模板中的key，需要二次验证引用资料模板确认查询字段

				FormFields formField = getFormField(lookUpClassID, disPlayField);

				String name = (String) formField.getName();
				Integer lookUpTypeEx = formField.getLookUpType();
				String srcTableEx = formField.getSrcTable();
				String srcFieldEx = formField.getSrcField();

				if (lookUpTypeEx != null && lookUpTypeEx > 0) {
					// 基础资料的附加属性又是引用类型的情况--取显示字段并关联表

					if (needConvert) {
						fieldName = name;
					} else {
						fieldName = srcFieldEx;
					}
				} else {
					if (needConvert) {
						fieldName = disPlayField;
					} else {
						fieldName = srcFieldEx;
					}
				}

			} else if (lookUpType != null && lookUpType == 4) {

				if (dataType != null && dataType == 2) {
					// 文本类的关联字段，未防止关联表中无记录，此处取主表字段值-如订单查询FCarNo字段取数
					tableName = formFieldLinkedTable;
				} else {
					// 引用字段查询-使用关联表显示字段作为条件
					tableName = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;
				}

				if (needConvert) {
					fieldName = disPlayField;
				} else {
					fieldName = srcField;
				}

			} else if (lookUpType > 0 && lookUpType > 4) {

				// 引用字段查询-使用关联表显示字段作为条件
				tableName = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;
				if (needConvert) {
					fieldName = disPlayField;
				} else {
					fieldName = srcField;
				}

			}

			if (i == 0) {
				// 第一个条件舍弃前面andOr条件链接符号
				sbWhere.append(separator).append(String.format("%s %s.%s%s%s %s %s %s", leftParenTheses, tableName, bDelimiter, fieldName, eDelimiter, logicOperator, value, rightParenTheses));
			} else {
				sbWhere.append(separator)
						.append(String.format("%s %s %s.%s%s%s %s %s %s", andOr, leftParenTheses, tableName, bDelimiter, fieldName, eDelimiter, logicOperator, value, rightParenTheses));
			}

		}

		String whereStr = sbWhere.toString();
		if (whereStr.equals("WHERE")) {
			return "";
		}

		return whereStr;
	}

	/**
	 * 防注入特殊符号处理
	 * 
	 * @Title handleSqlInjection
	 * @param str
	 * @return String
	 * @date 2017-04-21 09:26:55 星期五
	 */
	private String handleSqlInjection(String str) {
		return str.replace("'", "").replace("\\", "\\\\").replace("--", "");
	}

	/**
	 * 解析排序条件
	 * 
	 * @Title getOrderByStr
	 * @param classId
	 * @param orderByArray
	 * @return String 排序条件语句
	 * @date 2017-04-20 14:26:25 星期四
	 */
	@SuppressWarnings("unchecked")
	private String getOrderByStr(Integer classId, JSONArray orderByArray) {

		StringBuilder sbOrderBy = new StringBuilder();
		String separator = System.getProperty("line.separator");

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);
		// 所有字段模板
		Map<String, FormFields> formFieldsAll = getFormFields(classId, -1);
		// 主表资料描述信息
		FormClass itemClass = (FormClass) template.get("formClass");
		// 子表资料描述信息
		Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

		if (null == itemClass) {
			throw new BusinessLogicRunTimeException("没有模板数据");
		}

		Map<String, String> dbDelimiter = getDBDelimiter();

		String bDelimiter = dbDelimiter.get("bDelimiter");// 数据库字段-关键字处理
		String eDelimiter = dbDelimiter.get("eDelimiter");

		String primaryTableName = itemClass.getTableName();
		String primaryKey = itemClass.getPrimaryKey();

		sbOrderBy.append("ORDER BY");

		for (int i = 0; i < orderByArray.size(); i++) {

			JSONObject orderByItem = orderByArray.getJSONObject(i);

			String fieldKey = "";

			if (orderByItem.containsKey("fieldKey")) {
				fieldKey = orderByItem.getString("fieldKey");
			}

			if (fieldKey.equals("")) {
				// 没有传递fieldKey,忽略
				continue;
			}

			String orderDirection = "ASC"; // 默认ASC排序

			if (orderByItem.containsKey("orderDirection")) {
				orderDirection = orderByItem.getString("orderDirection");
			}

			if (!formFieldsAll.containsKey(fieldKey)) {
				// 没有定义模板-忽略
				continue;
			}

			@SuppressWarnings("unchecked")

			FormFields formFields = formFieldsAll.get(fieldKey);

			String sqlColumnName = formFields.getSqlColumnName();
			Integer lookUpType = formFields.getLookUpType();
			String srcTable = formFields.getSrcTable();
			String srcTableAlisAs = formFields.getSrcTableAlisAs();
			String disPlayField = formFields.getDisPlayField();

			String tableName = primaryTableName;
			String fieldName = sqlColumnName;

			if (lookUpType > 0) {
				// 引用类型字段-找到真实的表，字段
				tableName = srcTableAlisAs == null || srcTableAlisAs.equals("") ? srcTable : srcTableAlisAs;
				fieldName = disPlayField;
			}

			sbOrderBy.append(separator).append(String.format("%s.%s%s%s %s,", tableName, bDelimiter, fieldName, eDelimiter, orderDirection));

		}

		String orderByStr = sbOrderBy.toString();
		if (orderByStr.equals("ORDER BY")) {
			return "";
		}

		return orderByStr.substring(0, orderByStr.length() - 1);
	}

	/**
	 * 根据classId,key查找特定单据指定模板信息
	 * 
	 * @Title getFormField
	 * @param classId
	 *            业务类型
	 * @param key
	 *            模板key
	 * @return Map<String,Object>
	 * @date 2017-04-20 15:40:29 星期四
	 */
	private FormFields getFormField(int classId, String key) {

		FormFieldsMapper fieldsMapper = sqlSession.getMapper(FormFieldsMapper.class);

		FormFieldsExample example = new FormFieldsExample();

		com.kingdee.eas.hrp.sms.model.FormFieldsExample.Criteria criteria = example.createCriteria();

		criteria.andClassIdEqualTo(classId);
		criteria.andKeyEqualTo(key);

		List<FormFields> formFields = fieldsMapper.selectByExample(example);

		if (!formFields.isEmpty()) {
			return formFields.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> prepareAddMap(JSONObject data, Map<String, FormFields> formFields, String primaryTableName) {

		StringBuffer fieldNames = new StringBuffer("");
		StringBuffer fieldValues = new StringBuffer("");

		for (Iterator<String> iterator = data.keySet().iterator(); iterator.hasNext();) {

			String key = iterator.next();
			if (key.equals("entry"))
				continue;

			FormFields formField = formFields.get(key);

			if (formField == null)
				continue;
			Integer lookUpType = formField.getLookUpType();
			if (lookUpType == 3)// 引用基础资料的附加属性，无需保存
				continue;

			String value = data.getString(key);
			value = handleSqlInjection(value);

			if (value == null || value.equals("")) {
				continue;
			}
			String fieldName = formField.getSqlColumnName();
			fieldNames.append(",").append(fieldName);

			int dataType = formField.getDataType();

			DataTypeeEnum typeEnum = DataTypeeEnum.getTypeEnum(dataType);

			switch (typeEnum) {
			case NUMBER:
				fieldValues.append(",").append(value);
				break;
			case TEXT:
				fieldValues.append(",'").append(value).append("'");
				break;
			case BOOLEAN:
				fieldValues.append(",").append(value);
				break;
			case TIME:
				fieldValues.append(",'").append(value).append("'");
				break;
			default:
				fieldValues.append(",'").append(value).append("'");
				break;

			}
		}

		String fieldStr = fieldNames.length() > 0 ? fieldNames.toString().substring(1) : "";
		String valueStr = fieldValues.length() > 0 ? fieldValues.toString().substring(1) : "";

		Map<String, Object> ret = new HashMap<String, Object>();

		ret.put("tableName", primaryTableName);
		ret.put("fieldStr", fieldStr);
		ret.put("valueStr", valueStr);

		return ret;
	}

	/**
	 * 保存或修改表体数据
	 * 
	 * @Title handleEntryData
	 * @param classId
	 * @param id
	 * @param data
	 *            void
	 * @date 2017-04-27 14:51:05 星期四
	 */
	@SuppressWarnings("unchecked")
	private void handleEntryData(int classId, int id, JSONObject data) {

		// 基础资料模板
		Map<String, Object> template = getFormTemplate(classId, 1);

		if (data.containsKey("entry")) {

			JSONObject jsonEntry = data.getJSONObject("entry");
			Map<String, Object> formEntries = (Map<String, Object>) template.get("formEntries");

			for (Iterator<String> iterator = formEntries.keySet().iterator(); iterator.hasNext();) {

				String key = iterator.next(); // key 等于1或2或3...
				Map<String, FormFields> formFields = (Map<String, FormFields>) ((Map<String, Object>) template.get("formFields")).get(key);
				JSONArray entryData = jsonEntry.getJSONArray(key);
				Map<String, Object> formEntry = (Map<String, Object>) formEntries.get(key);
				// 保存或删除分录数据
				saveEntry(entryData, formEntry, formFields, id);

			}
		}
	}

	/**
	 * 保存或删除分录数据
	 * 
	 * @param entryData
	 *            分录数据 如：[{
	 *            'data':{FParkID:3},'flag':'1'},{'data':{FEntryID:4,FParkID:11},'flag':'2'},{'data':{FEntryID:3 , F P a
	 *            r k I D : 1 } , ' f l a g ' : ' 0 ' } ]
	 * @param formEntry
	 *            分录表描述 { "FEntryIndex": 1, "FPrimaryKey": "FEntryID", "FForeignKey": "FID", "FClassID": 13001,
	 *            "FTableName": "t_PropertyCompanyEntry" }
	 * @param formFields
	 *            分录表配置的字段
	 * @param id
	 *            分录表外键值，关联主表的主键
	 * @param userType
	 *            TODO
	 */
	private void saveEntry(JSONArray entryData, Map<String, Object> formEntry, Map<String, FormFields> formFields, int id) {

		String primaryTableName = (String) formEntry.get("FTableName");
		String primaryKey = (String) formEntry.get("FPrimaryKey");
		String foreignKey = (String) formEntry.get("FForeignKey");
		String items = ""; // 记录删除id，一次性删除

		TemplateDaoMapper templateDaoMapper = sqlSession.getMapper(TemplateDaoMapper.class);

		for (int i = 0; i < entryData.size(); i++) {

			JSONObject entry = entryData.getJSONObject(i);
			JSONObject data = entry.getJSONObject("data");

			int flag = entry.getInteger("flag");

			if (flag == 1) { // --增加

				// 检查字段
				// checkFields(formFields, data, primaryKey, flag, userType);
				// 准备保存模板
				Map<String, Object> statement = prepareAddMap(data, formFields, primaryTableName);
				// 外键：模板配置中不需要配置该外键，因为该外键的值在前端时无法获取，只有主表保存后才能在后台获取
				String fieldStr = statement.get("fieldStr").toString();
				if (!fieldStr.equals("")) {

					if (fieldStr.indexOf("," + foreignKey) < 0) {
						fieldStr += "," + foreignKey;
						String valueStr = statement.get("valueStr").toString() + "," + id;
						statement.put("fieldStr", fieldStr);
						statement.put("valueStr", valueStr);
					}
					// 插入基础资料分录
					templateDaoMapper.add(statement);

				}
			} else if (flag == 2) { // --修改

				// 检查字段
				// checkFields(formFields, data, primaryKey, flag, userType);
				// 模板参数
				int FID = data.getInteger(primaryKey);
				// 准备保存模板
				Map<String, Object> statement = prepareEditMap(data, formFields, primaryTableName, primaryKey, FID);

				// 修改基础资料分录
				templateDaoMapper.edit(statement);

			} else if (flag == 0) { // --删除，组装items

				int FID = data.getInteger(primaryKey);
				items += "," + FID;

			}
		}

		if (!items.equals("")) { // --items不为空，则执行删除
			Map<String, Object> statement = new HashMap<String, Object>();
			statement.put("tableName", primaryTableName);
			statement.put("primaryKey", primaryKey);
			statement.put("items", items.substring(1));
			templateDaoMapper.del(statement);
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> prepareEditMap(JSONObject data, Map<String, FormFields> formFields, String primaryTableName, String primaryKey, int id) {
		StringBuffer kvBuffer = new StringBuffer("");

		for (Iterator<String> iterator = data.keySet().iterator(); iterator.hasNext();) {

			String key = iterator.next();
			if (key.equals("entry"))
				continue;
			FormFields formField = formFields.get(key);
			if (formField == null)
				continue;

			Integer lookUpType = formField.getLookUpType();

			if (lookUpType == 3 || lookUpType == 5)// 引用基础资料的附加属性OR关联普通表携带字段，无需保存
				continue;

			String value = data.getString(key);
			value = handleSqlInjection(value);

			String fieldName = formField.getSqlColumnName();
			kvBuffer.append(",").append(fieldName).append("=");

			if (value == null || value.equals("")) {
				kvBuffer.append("null");
			} else {
				int dataType = formField.getDataType();
				DataTypeeEnum typeEnum = DataTypeeEnum.getTypeEnum(dataType);
				switch (typeEnum) {
				case NUMBER:
					kvBuffer.append(value);
					break;
				case TEXT:
					kvBuffer.append("'").append(value).append("'");
					break;
				case BOOLEAN:
					kvBuffer.append(value);
					break;
				case TIME:
					kvBuffer.append("'").append(value).append("'");
					break;
				default:
					kvBuffer.append("'").append(value).append("'");
					break;

				}
			}
		}

		String kvStr = kvBuffer.length() > 0 ? kvBuffer.toString().substring(1) : "";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", primaryTableName);
		map.put("kvStr", kvStr);
		map.put("primaryKey", primaryKey);
		map.put("FID", id);

		return map;
	}

}
