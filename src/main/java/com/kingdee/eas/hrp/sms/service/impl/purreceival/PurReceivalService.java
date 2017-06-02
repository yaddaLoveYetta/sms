package com.kingdee.eas.hrp.sms.service.impl.purreceival;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurReceivalMapper;
import com.kingdee.eas.hrp.sms.model.PurReceival;
import com.kingdee.eas.hrp.sms.service.api.purreceival.IPurReceivalService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;


@Service
public class PurReceivalService extends BaseService implements IPurReceivalService{
	
	
	/**
	 * 
	 * 收货单同步接口
	 * 
	 */
	@Override
	public String purReceival(JSONArray jsonarray) {
		PurReceival purReceival = new PurReceival();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			purReceival.setId(jsonObject.getString("id"));
			purReceival.setMaterial(jsonObject.getString("material"));
			purReceival.setNumber(jsonObject.getString("number"));
			purReceival.setOrderId(jsonObject.getString("orderId"));
			purReceival.setQty(jsonObject.getBigDecimal("qty"));
			purReceival.setUnit(jsonObject.getString("unit"));
			PurReceivalMapper purReceivalMapper = sqlSession.getMapper(PurReceivalMapper.class);
			purReceivalMapper.insert(purReceival);
		}
		return "success";
	}

}
