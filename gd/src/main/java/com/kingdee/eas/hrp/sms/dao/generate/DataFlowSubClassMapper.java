package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.DataFlowSubClass;
import com.kingdee.eas.hrp.sms.model.DataFlowSubClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataFlowSubClassMapper {
    long countByExample(DataFlowSubClassExample example);

    int deleteByExample(DataFlowSubClassExample example);

    int deleteByPrimaryKey(Integer subSysId);

    int insert(DataFlowSubClass record);

    int insertSelective(DataFlowSubClass record);

    List<DataFlowSubClass> selectByExample(DataFlowSubClassExample example);

    DataFlowSubClass selectByPrimaryKey(Integer subSysId);

    int updateByExampleSelective(@Param("record") DataFlowSubClass record, @Param("example") DataFlowSubClassExample example);

    int updateByExample(@Param("record") DataFlowSubClass record, @Param("example") DataFlowSubClassExample example);

    int updateByPrimaryKeySelective(DataFlowSubClass record);

    int updateByPrimaryKey(DataFlowSubClass record);
}