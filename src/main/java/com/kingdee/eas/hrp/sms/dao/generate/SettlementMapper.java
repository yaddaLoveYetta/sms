package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.SettlementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettlementMapper {
    long countByExample(SettlementExample example);

    int deleteByExample(SettlementExample example);

    int deleteByPrimaryKey(String id);

    int insert(Settlement record);

    int insertSelective(Settlement record);

    List<Settlement> selectByExample(SettlementExample example);

    Settlement selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Settlement record, @Param("example") SettlementExample example);

    int updateByExample(@Param("record") Settlement record, @Param("example") SettlementExample example);

    int updateByPrimaryKeySelective(Settlement record);

    int updateByPrimaryKey(Settlement record);
}