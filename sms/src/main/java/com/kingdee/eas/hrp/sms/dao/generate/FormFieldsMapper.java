package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.model.FormFieldsExample;
import com.kingdee.eas.hrp.sms.model.FormFieldsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormFieldsMapper {
    long countByExample(FormFieldsExample example);

    int deleteByExample(FormFieldsExample example);

    int deleteByPrimaryKey(FormFieldsKey key);

    int insert(FormFields record);

    int insertSelective(FormFields record);

    List<FormFields> selectByExample(FormFieldsExample example);

    FormFields selectByPrimaryKey(FormFieldsKey key);

    int updateByExampleSelective(@Param("record") FormFields record, @Param("example") FormFieldsExample example);

    int updateByExample(@Param("record") FormFields record, @Param("example") FormFieldsExample example);

    int updateByPrimaryKeySelective(FormFields record);

    int updateByPrimaryKey(FormFields record);
}