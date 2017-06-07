package com.kingdee.eas.hrp.sms.service.impl.purinwarehs;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurInWarehsEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurInWarehsMapper;
import com.kingdee.eas.hrp.sms.model.PurInWarehs;
import com.kingdee.eas.hrp.sms.model.PurInWarehsEntry;
import com.kingdee.eas.hrp.sms.service.api.purinwarehs.IPurInWarehsService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class PurInWarehsService extends BaseService implements IPurInWarehsService {

	/**
	 * 
	 * 入库单同步接口
	 * 
	 */
	@Override
	public String purInWarehsService(JSONArray jsonarray) {
		PurInWarehs purInWarehs = new PurInWarehs();
		PurInWarehsEntry purInWarehsEntry = new PurInWarehsEntry();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			purInWarehs.setId(jsonObject.getString("id"));
			purInWarehs.setNumber(jsonObject.getString("number"));
			if (jsonObject.getDate("bizDate") != null) {
				purInWarehs.setBizDate(jsonObject.getDate("bizDate"));
			}
			purInWarehs.setBaseStatus(jsonObject.getByte("baseStatus"));
			purInWarehs.setSourceBillType(jsonObject.getByte("sourceBillType"));
			purInWarehs.setSupplier(jsonObject.getString("supplier"));
			PurInWarehsMapper purInWarehsMapper = sqlSession.getMapper(PurInWarehsMapper.class);
			purInWarehsMapper.insertSelective(purInWarehs);
			JSONArray purEntryArray = JSONArray.parseArray(jsonObject.getString("entries"));
			for (int j = 0; j < purEntryArray.size(); j++) {
				JSONObject purEntryObject = purEntryArray.getJSONObject(j);
				purInWarehsEntry.setId(purEntryObject.getString("id"));
				purInWarehsEntry.setParent(purEntryObject.getString("parent"));
				purInWarehsEntry.setSeq(purEntryObject.getInteger("seq"));
				purInWarehsEntry.setOrderId(purEntryObject.getString("orderId"));
				purInWarehsEntry.setOrderSeq(purEntryObject.getString("orderSeq"));
				purInWarehsEntry.setMaterial(purEntryObject.getString("material"));
				purInWarehsEntry.setLot(purEntryObject.getString("lot"));
				purInWarehsEntry.setInnercode(purEntryObject.getString("innercode"));
				purInWarehsEntry.setUnit(purEntryObject.getString("unit"));
				purInWarehsEntry.setPrice(purEntryObject.getBigDecimal("price"));
				purInWarehsEntry.setQty(purEntryObject.getLong("qty"));
				purInWarehsEntry.setActualQty(purEntryObject.getLong("actualQty"));
				if (purEntryObject.getDate("dyProDate") != null) {
					purInWarehsEntry.setDyProDate(purEntryObject.getDate("dyProDate"));
				}
				purInWarehsEntry.setDyManufacturer(purEntryObject.getString("dyManufacturer"));
				purInWarehsEntry.setRegistrationNo(purEntryObject.getString("registrationNo"));
				purInWarehsEntry.setAmount(purEntryObject.getBigDecimal("amount"));
				if (purEntryObject.getDate("effectiveDate") != null) {
					purInWarehsEntry.setEffectiveDate(purEntryObject.getDate("effectiveDate"));
				}
				PurInWarehsEntryMapper purInWarehsEntryMapper = sqlSession.getMapper(PurInWarehsEntryMapper.class);
				purInWarehsEntryMapper.insertSelective(purInWarehsEntry);
				
			}
		}
		return "success";
	}

}
