package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurReturns;
import com.kingdee.eas.hrp.sms.model.PurReturnsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurReturnsMapper {
    long countByExample(PurReturnsExample example);

    int deleteByExample(PurReturnsExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurReturns record);

    int insertSelective(PurReturns record);

    List<PurReturns> selectByExample(PurReturnsExample example);

    PurReturns selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurReturns record, @Param("example") PurReturnsExample example);

    int updateByExample(@Param("record") PurReturns record, @Param("example") PurReturnsExample example);

    int updateByPrimaryKeySelective(PurReturns record);

    int updateByPrimaryKey(PurReturns record);
}