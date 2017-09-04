package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurReceivalEntry;
import com.kingdee.eas.hrp.sms.model.PurReceivalEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurReceivalEntryMapper {
    long countByExample(PurReceivalEntryExample example);

    int deleteByExample(PurReceivalEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurReceivalEntry record);

    int insertSelective(PurReceivalEntry record);

    List<PurReceivalEntry> selectByExample(PurReceivalEntryExample example);

    PurReceivalEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurReceivalEntry record, @Param("example") PurReceivalEntryExample example);

    int updateByExample(@Param("record") PurReceivalEntry record, @Param("example") PurReceivalEntryExample example);

    int updateByPrimaryKeySelective(PurReceivalEntry record);

    int updateByPrimaryKey(PurReceivalEntry record);
}