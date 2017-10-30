package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.CurrencyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CurrencyMapper {
    long countByExample(CurrencyExample example);

    int deleteByExample(CurrencyExample example);

    int deleteByPrimaryKey(String id);

    int insert(Currency record);

    int insertSelective(Currency record);

    List<Currency> selectByExample(CurrencyExample example);

    Currency selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Currency record, @Param("example") CurrencyExample example);

    int updateByExample(@Param("record") Currency record, @Param("example") CurrencyExample example);

    int updateByPrimaryKeySelective(Currency record);

    int updateByPrimaryKey(Currency record);
}