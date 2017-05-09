package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Supplier_License_Type;
import com.kingdee.eas.hrp.sms.model.Supplier_License_TypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Supplier_License_TypeMapper {
    long countByExample(Supplier_License_TypeExample example);

    int deleteByExample(Supplier_License_TypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Supplier_License_Type record);

    int insertSelective(Supplier_License_Type record);

    List<Supplier_License_Type> selectByExample(Supplier_License_TypeExample example);

    Supplier_License_Type selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Supplier_License_Type record, @Param("example") Supplier_License_TypeExample example);

    int updateByExample(@Param("record") Supplier_License_Type record, @Param("example") Supplier_License_TypeExample example);

    int updateByPrimaryKeySelective(Supplier_License_Type record);

    int updateByPrimaryKey(Supplier_License_Type record);
}