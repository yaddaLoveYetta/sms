package com.kingdee.eas.hrp.sms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.generate.FormClassMapper;
import com.kingdee.eas.hrp.sms.dao.generate.FormEntriesMapper;
import com.kingdee.eas.hrp.sms.dao.generate.FormFieldsMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormClassExample;
import com.kingdee.eas.hrp.sms.model.FormClassExample.Criteria;
import com.kingdee.eas.hrp.sms.model.FormEntries;
import com.kingdee.eas.hrp.sms.model.FormEntriesExample;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.model.FormFieldsExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;

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

		List<Map<String, Object>> itemClassTemplate = null;

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
			fieldsExample.setOrderByClause("FPage, FCtlIndex");

			// itemClassTemplate = baseItemDao.getFormFieldsTemplateForPlatform(itemClassID, 0);
		} else {

			fieldsExample.setOrderByClause("FPage, FIndex");
			// itemClassTemplate = baseItemDao.getFormFieldsTemplate(itemClassID, 0);
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

}
