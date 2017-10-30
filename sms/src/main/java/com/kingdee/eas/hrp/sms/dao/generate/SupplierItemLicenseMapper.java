package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierItemLicense;
import com.kingdee.eas.hrp.sms.model.SupplierItemLicenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierItemLicenseMapper {
    long countByExample(SupplierItemLicenseExample example);

    int deleteByExample(SupplierItemLicenseExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierItemLicense record);

    int insertSelective(SupplierItemLicense record);

    List<SupplierItemLicense> selectByExample(SupplierItemLicenseExample example);

    SupplierItemLicense selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierItemLicense record, @Param("example") SupplierItemLicenseExample example);

    int updateByExample(@Param("record") SupplierItemLicense record, @Param("example") SupplierItemLicenseExample example);

    int updateByPrimaryKeySelective(SupplierItemLicense record);

    int updateByPrimaryKey(SupplierItemLicense record);
}