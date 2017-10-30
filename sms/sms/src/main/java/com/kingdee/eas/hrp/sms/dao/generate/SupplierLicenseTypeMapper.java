package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierLicenseType;
import com.kingdee.eas.hrp.sms.model.SupplierLicenseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierLicenseTypeMapper {
    long countByExample(SupplierLicenseTypeExample example);

    int deleteByExample(SupplierLicenseTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierLicenseType record);

    int insertSelective(SupplierLicenseType record);

    List<SupplierLicenseType> selectByExample(SupplierLicenseTypeExample example);

    SupplierLicenseType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierLicenseType record, @Param("example") SupplierLicenseTypeExample example);

    int updateByExample(@Param("record") SupplierLicenseType record, @Param("example") SupplierLicenseTypeExample example);

    int updateByPrimaryKeySelective(SupplierLicenseType record);

    int updateByPrimaryKey(SupplierLicenseType record);
}