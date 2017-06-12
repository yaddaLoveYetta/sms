package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierLicenseEntry;
import com.kingdee.eas.hrp.sms.model.SupplierLicenseEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierLicenseEntryMapper {
    long countByExample(SupplierLicenseEntryExample example);

    int deleteByExample(SupplierLicenseEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierLicenseEntry record);

    int insertSelective(SupplierLicenseEntry record);

    List<SupplierLicenseEntry> selectByExample(SupplierLicenseEntryExample example);

    SupplierLicenseEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierLicenseEntry record, @Param("example") SupplierLicenseEntryExample example);

    int updateByExample(@Param("record") SupplierLicenseEntry record, @Param("example") SupplierLicenseEntryExample example);

    int updateByPrimaryKeySelective(SupplierLicenseEntry record);

    int updateByPrimaryKey(SupplierLicenseEntry record);
}