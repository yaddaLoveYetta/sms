package com.kingdee.eas.hrp.sms.service.impl.purinwarehs;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurInWarehsMapper;
import com.kingdee.eas.hrp.sms.model.PurInWarehs;
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
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			purInWarehs.setId(jsonObject.getString("id"));
			PurInWarehsMapper purInWarehsMapper = sqlSession.getMapper(PurInWarehsMapper.class);
			purInWarehsMapper.insert(purInWarehs);
		}
		return "success";
	}

}
