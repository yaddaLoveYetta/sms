package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurInWarehsEntry;
import com.kingdee.eas.hrp.sms.model.PurInWarehsEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurInWarehsEntryMapper {
    long countByExample(PurInWarehsEntryExample example);

    int deleteByExample(PurInWarehsEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurInWarehsEntry record);

    int insertSelective(PurInWarehsEntry record);

    List<PurInWarehsEntry> selectByExample(PurInWarehsEntryExample example);

    PurInWarehsEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurInWarehsEntry record, @Param("example") PurInWarehsEntryExample example);

    int updateByExample(@Param("record") PurInWarehsEntry record, @Param("example") PurInWarehsEntryExample example);

    int updateByPrimaryKeySelective(PurInWarehsEntry record);

    int updateByPrimaryKey(PurInWarehsEntry record);
}