package com.kingdee.eas.hrp.sms.service.impl.purreceival;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurInWarehsEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurInWarehsMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurReceivalEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurReceivalMapper;
import com.kingdee.eas.hrp.sms.model.PurInWarehs;
import com.kingdee.eas.hrp.sms.model.PurInWarehsEntry;
import com.kingdee.eas.hrp.sms.model.PurReceival;
import com.kingdee.eas.hrp.sms.model.PurReceivalEntry;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.purreceival.IPurReceivalService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class PurReceivalService extends BaseService implements IPurReceivalService {

	@Resource
	ITemplateService iTemplateService;

	/**
	 * 
	 * 收货单同步接口
	 * 
	 */
	@Override
	@Transactional
	public String purReceival(JSONArray jsonarray) {
		PurReceival purReceival = new PurReceival();
		PurReceivalEntry purReceivalEntry = new PurReceivalEntry();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			purReceival.setId(jsonObject.getString("id"));
			purReceival.setNumber(jsonObject.getString("number"));
			if (jsonObject.getDate("bizDate") != null) {
				purReceival.setBizDate(jsonObject.getDate("bizDate"));
			}
			purReceival.setBaseStatus(jsonObject.getByte("baseStatus"));
			if (jsonObject.getByte("baseStatus") != 4) {
				iTemplateService.delItem(2021, jsonObject.getString("id"));
			}
			purReceival.setSourceBillType(jsonObject.getString("sourceBillType"));
			purReceival.setSupplier(jsonObject.getString("supplier"));
			PurReceivalMapper purReceivalMapper = sqlSession.getMapper(PurReceivalMapper.class);
			if (jsonObject.getByte("baseStatus") == 4) {
				iTemplateService.delItem(2021, jsonObject.getString("id"));
				purReceivalMapper.insertSelective(purReceival);
			}
			JSONObject entry = (JSONObject) jsonObject.get("entry");
			JSONArray purEntryArray = (JSONArray) entry.get("1");
			for (int j = 0; j < purEntryArray.size(); j++) {
				JSONObject purEntryObject = purEntryArray.getJSONObject(j);
				purReceivalEntry.setId(purEntryObject.getString("id"));
				purReceivalEntry.setParent(purEntryObject.getString("parent"));
				purReceivalEntry.setSeq(purEntryObject.getInteger("seq"));
				purReceivalEntry.setOrderId(purEntryObject.getString("orderId"));
				purReceivalEntry.setOrderSeq(purEntryObject.getString("orderSeq"));
				purReceivalEntry.setMaterial(purEntryObject.getString("material"));
				purReceivalEntry.setLot(purEntryObject.getString("lot"));
				purReceivalEntry.setInnercode(purEntryObject.getString("innercode"));
				purReceivalEntry.setUnit(purEntryObject.getString("unit"));
				purReceivalEntry.setPrice(purEntryObject.getBigDecimal("price"));
				purReceivalEntry.setQty(purEntryObject.getLong("qty"));
				purReceivalEntry.setActualQty(purEntryObject.getLong("actualQty"));
				if (purEntryObject.getDate("dyProDate") != null) {
					purReceivalEntry.setDyProDate(purEntryObject.getDate("dyProDate"));
				}
				purReceivalEntry.setDyManufacturer(purEntryObject.getString("dyManufacturer"));
				purReceivalEntry.setRegistrationNo(purEntryObject.getString("registrationNo"));
				purReceivalEntry.setAmount(purEntryObject.getBigDecimal("amount"));
				if (purEntryObject.getDate("effectiveDate") != null) {
					purReceivalEntry.setEffectiveDate(purEntryObject.getDate("effectiveDate"));
				}
				purReceivalEntry.setQualifiedQty(purEntryObject.getLong("qualifiedQty"));
				purReceivalEntry.setUnqualifiedQty(purEntryObject.getLong("unqualifiedQty"));
				PurReceivalEntryMapper purReceivalEntryMapper = sqlSession.getMapper(PurReceivalEntryMapper.class);
				if (jsonObject.getByte("baseStatus") == 4) {
					purReceivalEntryMapper.insertSelective(purReceivalEntry);
				}

			}
		}
		return "success";
	}

}
