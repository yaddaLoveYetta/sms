package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Plugins;
import com.kingdee.eas.hrp.sms.model.PluginsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PluginsMapper {
    long countByExample(PluginsExample example);

    int deleteByExample(PluginsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Plugins record);

    int insertSelective(Plugins record);

    List<Plugins> selectByExample(PluginsExample example);

    Plugins selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Plugins record, @Param("example") PluginsExample example);

    int updateByExample(@Param("record") Plugins record, @Param("example") PluginsExample example);

    int updateByPrimaryKeySelective(Plugins record);

    int updateByPrimaryKey(Plugins record);
}