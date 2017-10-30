package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.PurInWarehs;
import com.kingdee.eas.hrp.sms.model.PurInWarehsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurInWarehsMapper {
    long countByExample(PurInWarehsExample example);

    int deleteByExample(PurInWarehsExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurInWarehs record);

    int insertSelective(PurInWarehs record);

    List<PurInWarehs> selectByExample(PurInWarehsExample example);

    PurInWarehs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurInWarehs record, @Param("example") PurInWarehsExample example);

    int updateByExample(@Param("record") PurInWarehs record, @Param("example") PurInWarehsExample example);

    int updateByPrimaryKeySelective(PurInWarehs record);

    int updateByPrimaryKey(PurInWarehs record);
}