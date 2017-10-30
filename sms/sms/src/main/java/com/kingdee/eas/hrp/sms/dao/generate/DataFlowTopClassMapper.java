package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.DataFlowTopClass;
import com.kingdee.eas.hrp.sms.model.DataFlowTopClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataFlowTopClassMapper {
    long countByExample(DataFlowTopClassExample example);

    int deleteByExample(DataFlowTopClassExample example);

    int deleteByPrimaryKey(Integer topClassId);

    int insert(DataFlowTopClass record);

    int insertSelective(DataFlowTopClass record);

    List<DataFlowTopClass> selectByExample(DataFlowTopClassExample example);

    DataFlowTopClass selectByPrimaryKey(Integer topClassId);

    int updateByExampleSelective(@Param("record") DataFlowTopClass record, @Param("example") DataFlowTopClassExample example);

    int updateByExample(@Param("record") DataFlowTopClass record, @Param("example") DataFlowTopClassExample example);

    int updateByPrimaryKeySelective(DataFlowTopClass record);

    int updateByPrimaryKey(DataFlowTopClass record);
}