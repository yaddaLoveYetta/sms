package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.IndustryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndustryMapper {
    long countByExample(IndustryExample example);

    int deleteByExample(IndustryExample example);

    int deleteByPrimaryKey(String id);

    int insert(Industry record);

    int insertSelective(Industry record);

    List<Industry> selectByExample(IndustryExample example);

    Industry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Industry record, @Param("example") IndustryExample example);

    int updateByExample(@Param("record") Industry record, @Param("example") IndustryExample example);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);
}