package com.kingdee.eas.hrp.sms.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.customize.SysDaoMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;

@Service
public class BaseService {

	@Resource
	protected SqlSession sqlSession;

	/**
	 * 新增单据时获取单据主键</br>
	 * 
	 * 该方法不可重复调用，即任何情况下两次调用的结果都不一致
	 * 
	 * <P>
	 * 参考EAS单据内码产生规则
	 * </p>
	 * 
	 * @Title getId
	 * @param bosType
	 *            formClass中定义的bostype 长度必须为4或8,建议统一用8位，否则返回空
	 * @return String 32位唯一字符串
	 * @date 2017-05-06 14:42:39 星期六
	 */
	protected String getId(String bosType) {

		SysDaoMapper mapper = sqlSession.getMapper(SysDaoMapper.class);

		if (StringUtils.isEmpty(bosType) || bosType.trim().isEmpty() || (bosType.trim().length() != 4 && bosType.trim().length() < 8)) {
			// null,"",length!=4 and length<8 is not allowed
			throw new BusinessLogicRunTimeException(String.format("生成id不成功:bosType[%s]不符合规则", bosType));
		}
		return mapper.getId(bosType.trim());

	}

}
