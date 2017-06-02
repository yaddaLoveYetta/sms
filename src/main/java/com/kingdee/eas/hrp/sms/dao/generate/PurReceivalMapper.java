package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurReceival;
import com.kingdee.eas.hrp.sms.model.PurReceivalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurReceivalMapper {
    long countByExample(PurReceivalExample example);

    int deleteByExample(PurReceivalExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurReceival record);

    int insertSelective(PurReceival record);

    List<PurReceival> selectByExample(PurReceivalExample example);

    PurReceival selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurReceival record, @Param("example") PurReceivalExample example);

    int updateByExample(@Param("record") PurReceival record, @Param("example") PurReceivalExample example);

    int updateByPrimaryKeySelective(PurReceival record);

    int updateByPrimaryKey(PurReceival record);
}