package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SendcargoEntry;
import com.kingdee.eas.hrp.sms.model.SendcargoEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendcargoEntryMapper {
    long countByExample(SendcargoEntryExample example);

    int deleteByExample(SendcargoEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(SendcargoEntry record);

    int insertSelective(SendcargoEntry record);

    List<SendcargoEntry> selectByExample(SendcargoEntryExample example);

    SendcargoEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SendcargoEntry record, @Param("example") SendcargoEntryExample example);

    int updateByExample(@Param("record") SendcargoEntry record, @Param("example") SendcargoEntryExample example);

    int updateByPrimaryKeySelective(SendcargoEntry record);

    int updateByPrimaryKey(SendcargoEntry record);
}