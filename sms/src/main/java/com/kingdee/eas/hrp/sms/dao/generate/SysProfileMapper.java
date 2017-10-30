package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SysProfile;
import com.kingdee.eas.hrp.sms.model.SysProfileExample;
import com.kingdee.eas.hrp.sms.model.SysProfileKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysProfileMapper {
    long countByExample(SysProfileExample example);

    int deleteByExample(SysProfileExample example);

    int deleteByPrimaryKey(SysProfileKey key);

    int insert(SysProfile record);

    int insertSelective(SysProfile record);

    List<SysProfile> selectByExample(SysProfileExample example);

    SysProfile selectByPrimaryKey(SysProfileKey key);

    int updateByExampleSelective(@Param("record") SysProfile record, @Param("example") SysProfileExample example);

    int updateByExample(@Param("record") SysProfile record, @Param("example") SysProfileExample example);

    int updateByPrimaryKeySelective(SysProfile record);

    int updateByPrimaryKey(SysProfile record);
}