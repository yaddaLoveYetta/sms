package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Sendcargo;
import com.kingdee.eas.hrp.sms.model.SendcargoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendcargoMapper {
    long countByExample(SendcargoExample example);

    int deleteByExample(SendcargoExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sendcargo record);

    int insertSelective(Sendcargo record);

    List<Sendcargo> selectByExample(SendcargoExample example);

    Sendcargo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sendcargo record, @Param("example") SendcargoExample example);

    int updateByExample(@Param("record") Sendcargo record, @Param("example") SendcargoExample example);

    int updateByPrimaryKeySelective(Sendcargo record);

    int updateByPrimaryKey(Sendcargo record);
}