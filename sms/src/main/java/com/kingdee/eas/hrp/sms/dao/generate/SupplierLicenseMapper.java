package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierLicense;
import com.kingdee.eas.hrp.sms.model.SupplierLicenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierLicenseMapper {
    long countByExample(SupplierLicenseExample example);

    int deleteByExample(SupplierLicenseExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierLicense record);

    int insertSelective(SupplierLicense record);

    List<SupplierLicense> selectByExample(SupplierLicenseExample example);

    SupplierLicense selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierLicense record, @Param("example") SupplierLicenseExample example);

    int updateByExample(@Param("record") SupplierLicense record, @Param("example") SupplierLicenseExample example);

    int updateByPrimaryKeySelective(SupplierLicense record);

    int updateByPrimaryKey(SupplierLicense record);
}