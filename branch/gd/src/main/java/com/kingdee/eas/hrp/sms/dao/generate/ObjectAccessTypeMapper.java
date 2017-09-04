package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.ObjectAccessType;
import com.kingdee.eas.hrp.sms.model.ObjectAccessTypeExample;
import com.kingdee.eas.hrp.sms.model.ObjectAccessTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ObjectAccessTypeMapper {
    long countByExample(ObjectAccessTypeExample example);

    int deleteByExample(ObjectAccessTypeExample example);

    int deleteByPrimaryKey(ObjectAccessTypeKey key);

    int insert(ObjectAccessType record);

    int insertSelective(ObjectAccessType record);

    List<ObjectAccessType> selectByExample(ObjectAccessTypeExample example);

    ObjectAccessType selectByPrimaryKey(ObjectAccessTypeKey key);

    int updateByExampleSelective(@Param("record") ObjectAccessType record, @Param("example") ObjectAccessTypeExample example);

    int updateByExample(@Param("record") ObjectAccessType record, @Param("example") ObjectAccessTypeExample example);

    int updateByPrimaryKeySelective(ObjectAccessType record);

    int updateByPrimaryKey(ObjectAccessType record);
}