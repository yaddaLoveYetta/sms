package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.ObjectType;
import com.kingdee.eas.hrp.sms.model.ObjectTypeExample;
import com.kingdee.eas.hrp.sms.model.ObjectTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ObjectTypeMapper {
    long countByExample(ObjectTypeExample example);

    int deleteByExample(ObjectTypeExample example);

    int deleteByPrimaryKey(ObjectTypeKey key);

    int insert(ObjectType record);

    int insertSelective(ObjectType record);

    List<ObjectType> selectByExample(ObjectTypeExample example);

    ObjectType selectByPrimaryKey(ObjectTypeKey key);

    int updateByExampleSelective(@Param("record") ObjectType record, @Param("example") ObjectTypeExample example);

    int updateByExample(@Param("record") ObjectType record, @Param("example") ObjectTypeExample example);

    int updateByPrimaryKeySelective(ObjectType record);

    int updateByPrimaryKey(ObjectType record);
}