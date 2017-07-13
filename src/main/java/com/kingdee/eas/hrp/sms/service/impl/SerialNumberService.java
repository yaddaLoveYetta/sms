package com.kingdee.eas.hrp.sms.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingdee.eas.hrp.sms.dao.generate.SerialNumberMapper;
import com.kingdee.eas.hrp.sms.model.SerialNumber;
import com.kingdee.eas.hrp.sms.model.SerialNumberExample;
import com.kingdee.eas.hrp.sms.service.api.ISerialNumberService;
import com.kingdee.eas.hrp.sms.util.Environ;


@Service
public class SerialNumberService extends BaseService implements ISerialNumberService {


/**
 * 
 * 创建发货单订单号
 * 
 */
	@Override
	@Transactional
	public String getSerialNumber(int classId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
		int year = Integer.parseInt(sdf.format(new Date()));
		String code = null;
		// 根据classId获取流水号
		SerialNumber sn = new SerialNumber();
		SqlSession sqlSession = (SqlSession) Environ.getBean("sqlSession");
		SerialNumberMapper serialNumberMapper = sqlSession.getMapper(SerialNumberMapper.class);
		SerialNumberExample e = new SerialNumberExample();
		com.kingdee.eas.hrp.sms.model.SerialNumberExample.Criteria c = e.createCriteria();
		c.andClassIdEqualTo(classId);
		c.andYearEqualTo(year);
		List<SerialNumber> serialNumber = serialNumberMapper.selectByExample(e);
		DecimalFormat df = new DecimalFormat("000000");
		sn.setClassId(classId);
		sn.setYear(year);
		serialNumberMapper.updateByPrimaryKey(sn);
		if (serialNumber.size() > 0) {
			// 将获得的获得随机数转化为字符串
			code = df.format(serialNumber.get(0).getNumber() + 1);
			sn.setId(serialNumber.get(0).getId());
			sn.setNumber(serialNumber.get(0).getNumber() + 1);
			serialNumberMapper.updateByPrimaryKey(sn);
		} else {
			sn.setNumber(1);
			serialNumberMapper.insert(sn);
			code = df.format(1);
		}
		return "Pur" + year + code;
	}

}
