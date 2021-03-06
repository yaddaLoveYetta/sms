package com.kingdee.eas.hrp.sms.service.impl.purreturns;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurReturnsEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurReturnsMapper;
import com.kingdee.eas.hrp.sms.model.PurReturns;
import com.kingdee.eas.hrp.sms.model.PurReturnsEntry;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.purreturns.IPurReturnsService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class PurReturnsService extends BaseService implements IPurReturnsService {

	@Resource
	ITemplateService iTemplateService;

	@Override
	@Transactional
	public String purreturns(JSONArray jsonarray) {
		PurReturns purreturns = new PurReturns();
		PurReturnsEntry purReturnsEntry = new PurReturnsEntry();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			purreturns.setId(jsonObject.getString("id"));
			purreturns.setNumber(jsonObject.getString("number"));
			if (jsonObject.getDate("bizDate") != null) {
				purreturns.setBizDate(jsonObject.getDate("bizDate"));
			}
			purreturns.setBaseStatus(jsonObject.getByte("baseStatus"));
			if (jsonObject.getByte("baseStatus") != 4) {
				iTemplateService.delItem(2023, jsonObject.getString("id"));
			}
			purreturns.setSourceBillType(jsonObject.getString("sourceBillType"));
			purreturns.setSupplier(jsonObject.getString("supplier"));
			PurReturnsMapper purReturnsMapper = sqlSession.getMapper(PurReturnsMapper.class);
			if (jsonObject.getByte("baseStatus") == 4) {
				iTemplateService.delItem(2023, jsonObject.getString("id"));
				purReturnsMapper.insertSelective(purreturns);
			}
			JSONObject entry = (JSONObject) jsonObject.get("entry");
			JSONArray purEntryArray = (JSONArray) entry.get("1");
			for (int j = 0; j < purEntryArray.size(); j++) {
				JSONObject purEntryObject = purEntryArray.getJSONObject(j);
				purReturnsEntry.setId(purEntryObject.getString("id"));
				purReturnsEntry.setOrderId(purEntryObject.getString("orderId"));
				purReturnsEntry.setOrderSeq(purEntryObject.getString("orderSeq"));
				purReturnsEntry.setMaterial(purEntryObject.getString("material"));
				purReturnsEntry.setParent(purEntryObject.getString("parent"));
				purReturnsEntry.setUnit(purEntryObject.getString("unit"));
				purReturnsEntry.setReturnQty(purEntryObject.getBigDecimal("returnQty"));
				PurReturnsEntryMapper purReturnsEntryMapper = sqlSession.getMapper(PurReturnsEntryMapper.class);
				if (jsonObject.getByte("baseStatus") == 4) {
					purReturnsEntryMapper.insertSelective(purReturnsEntry);
				}
			}
		}
		return "success";
	}

}
