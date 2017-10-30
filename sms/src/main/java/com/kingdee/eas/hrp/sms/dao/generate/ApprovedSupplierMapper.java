package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.ApprovedSupplier;
import com.kingdee.eas.hrp.sms.model.ApprovedSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApprovedSupplierMapper {
    long countByExample(ApprovedSupplierExample example);

    int deleteByExample(ApprovedSupplierExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApprovedSupplier record);

    int insertSelective(ApprovedSupplier record);

    List<ApprovedSupplier> selectByExample(ApprovedSupplierExample example);

    ApprovedSupplier selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApprovedSupplier record, @Param("example") ApprovedSupplierExample example);

    int updateByExample(@Param("record") ApprovedSupplier record, @Param("example") ApprovedSupplierExample example);

    int updateByPrimaryKeySelective(ApprovedSupplier record);

    int updateByPrimaryKey(ApprovedSupplier record);
}