package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.FormEntries;
import com.kingdee.eas.hrp.sms.model.FormEntriesExample;
import com.kingdee.eas.hrp.sms.model.FormEntriesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormEntriesMapper {
    long countByExample(FormEntriesExample example);

    int deleteByExample(FormEntriesExample example);

    int deleteByPrimaryKey(FormEntriesKey key);

    int insert(FormEntries record);

    int insertSelective(FormEntries record);

    List<FormEntries> selectByExample(FormEntriesExample example);

    FormEntries selectByPrimaryKey(FormEntriesKey key);

    int updateByExampleSelective(@Param("record") FormEntries record, @Param("example") FormEntriesExample example);

    int updateByExample(@Param("record") FormEntries record, @Param("example") FormEntriesExample example);

    int updateByPrimaryKeySelective(FormEntries record);

    int updateByPrimaryKey(FormEntries record);
}