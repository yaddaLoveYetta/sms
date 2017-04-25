package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import com.kingdee.eas.hrp.sms.model.AccessControlKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessControlMapper {
    long countByExample(AccessControlExample example);

    int deleteByExample(AccessControlExample example);

    int deleteByPrimaryKey(AccessControlKey key);

    int insert(AccessControl record);

    int insertSelective(AccessControl record);

    List<AccessControl> selectByExample(AccessControlExample example);

    AccessControl selectByPrimaryKey(AccessControlKey key);

    int updateByExampleSelective(@Param("record") AccessControl record, @Param("example") AccessControlExample example);

    int updateByExample(@Param("record") AccessControl record, @Param("example") AccessControlExample example);

    int updateByPrimaryKeySelective(AccessControl record);

    int updateByPrimaryKey(AccessControl record);
}