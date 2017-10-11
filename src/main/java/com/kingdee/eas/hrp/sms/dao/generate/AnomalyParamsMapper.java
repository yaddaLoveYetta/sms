package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.AnomalyParams;
import com.kingdee.eas.hrp.sms.model.AnomalyParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnomalyParamsMapper {
    long countByExample(AnomalyParamsExample example);

    int deleteByExample(AnomalyParamsExample example);

    int deleteByPrimaryKey(String key);

    int insert(AnomalyParams record);

    int insertSelective(AnomalyParams record);

    List<AnomalyParams> selectByExample(AnomalyParamsExample example);

    AnomalyParams selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") AnomalyParams record, @Param("example") AnomalyParamsExample example);

    int updateByExample(@Param("record") AnomalyParams record, @Param("example") AnomalyParamsExample example);

    int updateByPrimaryKeySelective(AnomalyParams record);

    int updateByPrimaryKey(AnomalyParams record);
}