package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierItemLicenseEntry;
import com.kingdee.eas.hrp.sms.model.SupplierItemLicenseEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierItemLicenseEntryMapper {
    long countByExample(SupplierItemLicenseEntryExample example);

    int deleteByExample(SupplierItemLicenseEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierItemLicenseEntry record);

    int insertSelective(SupplierItemLicenseEntry record);

    List<SupplierItemLicenseEntry> selectByExample(SupplierItemLicenseEntryExample example);

    SupplierItemLicenseEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierItemLicenseEntry record, @Param("example") SupplierItemLicenseEntryExample example);

    int updateByExample(@Param("record") SupplierItemLicenseEntry record, @Param("example") SupplierItemLicenseEntryExample example);

    int updateByPrimaryKeySelective(SupplierItemLicenseEntry record);

    int updateByPrimaryKey(SupplierItemLicenseEntry record);
}