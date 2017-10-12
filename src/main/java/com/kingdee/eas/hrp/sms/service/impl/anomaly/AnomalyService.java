package com.kingdee.eas.hrp.sms.service.impl.anomaly;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.AnomalyParamsMapper;
import com.kingdee.eas.hrp.sms.model.AnomalyParams;
import com.kingdee.eas.hrp.sms.service.api.Anomaly.IAnomalyService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class AnomalyService extends BaseService implements IAnomalyService {

	@Override
	@Transactional
	public String getUserNameEncode() {

		AnomalyParamsMapper mapper = sqlSession.getMapper(AnomalyParamsMapper.class);

		AnomalyParams currentRecord = mapper.selectByPrimaryKey("1001-userName");

		AnomalyParams newRecord = new AnomalyParams();

		String value;

		if (null == currentRecord) {
			value = "0";
		} else if (null == currentRecord.getValue()) {
			value = "0";
		} else {
			value = currentRecord.getValue();
		}

		int currentCode = Integer.parseInt(value) + 1;

		newRecord.setKey("1001-userName");
		newRecord.setValue(String.valueOf(currentCode));

		if (null == currentRecord) {
			mapper.insert(newRecord);
		} else {
			mapper.updateByPrimaryKey(newRecord);
		}

		DecimalFormat df = new DecimalFormat("00000");

		return "hc-" + df.format(currentCode);
	}

}
