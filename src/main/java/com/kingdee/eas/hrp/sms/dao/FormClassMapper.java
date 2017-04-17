package com.kingdee.eas.hrp.sms.dao;

import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormClassMapper {
    long countByExample(FormClassExample example);

    int deleteByExample(FormClassExample example);

    int deleteByPrimaryKey(Integer classId);

    int insert(FormClass record);

    int insertSelective(FormClass record);

    List<FormClass> selectByExample(FormClassExample example);

    FormClass selectByPrimaryKey(Integer classId);

    int updateByExampleSelective(@Param("record") FormClass record, @Param("example") FormClassExample example);

    int updateByExample(@Param("record") FormClass record, @Param("example") FormClassExample example);

    int updateByPrimaryKeySelective(FormClass record);

    int updateByPrimaryKey(FormClass record);
}