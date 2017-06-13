package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurReturnsEntry;
import com.kingdee.eas.hrp.sms.model.PurReturnsEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurReturnsEntryMapper {
    long countByExample(PurReturnsEntryExample example);

    int deleteByExample(PurReturnsEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurReturnsEntry record);

    int insertSelective(PurReturnsEntry record);

    List<PurReturnsEntry> selectByExample(PurReturnsEntryExample example);

    PurReturnsEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurReturnsEntry record, @Param("example") PurReturnsEntryExample example);

    int updateByExample(@Param("record") PurReturnsEntry record, @Param("example") PurReturnsEntryExample example);

    int updateByPrimaryKeySelective(PurReturnsEntry record);

    int updateByPrimaryKey(PurReturnsEntry record);
}