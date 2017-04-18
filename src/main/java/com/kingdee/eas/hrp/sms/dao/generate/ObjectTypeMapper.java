package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.ObjectType;
import com.kingdee.eas.hrp.sms.model.ObjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ObjectTypeMapper {
    long countByExample(ObjectTypeExample example);

    int deleteByExample(ObjectTypeExample example);

    int insert(ObjectType record);

    int insertSelective(ObjectType record);

    List<ObjectType> selectByExample(ObjectTypeExample example);

    int updateByExampleSelective(@Param("record") ObjectType record, @Param("example") ObjectTypeExample example);

    int updateByExample(@Param("record") ObjectType record, @Param("example") ObjectTypeExample example);
}