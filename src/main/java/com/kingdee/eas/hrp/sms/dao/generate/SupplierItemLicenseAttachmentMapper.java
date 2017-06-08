package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierItemLicenseAttachment;
import com.kingdee.eas.hrp.sms.model.SupplierItemLicenseAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierItemLicenseAttachmentMapper {
    long countByExample(SupplierItemLicenseAttachmentExample example);

    int deleteByExample(SupplierItemLicenseAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierItemLicenseAttachment record);

    int insertSelective(SupplierItemLicenseAttachment record);

    List<SupplierItemLicenseAttachment> selectByExample(SupplierItemLicenseAttachmentExample example);

    SupplierItemLicenseAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierItemLicenseAttachment record, @Param("example") SupplierItemLicenseAttachmentExample example);

    int updateByExample(@Param("record") SupplierItemLicenseAttachment record, @Param("example") SupplierItemLicenseAttachmentExample example);

    int updateByPrimaryKeySelective(SupplierItemLicenseAttachment record);

    int updateByPrimaryKey(SupplierItemLicenseAttachment record);
}