package com.kingdee.eas.hrp.sms.dao;

import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessControlMapper {
    long countByExample(AccessControlExample example);

    int deleteByExample(AccessControlExample example);

    int insert(AccessControl record);

    int insertSelective(AccessControl record);

    List<AccessControl> selectByExample(AccessControlExample example);

    int updateByExampleSelective(@Param("record") AccessControl record, @Param("example") AccessControlExample example);

    int updateByExample(@Param("record") AccessControl record, @Param("example") AccessControlExample example);
}