package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.SupplierLicenseAttachment;
import com.kingdee.eas.hrp.sms.model.SupplierLicenseAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierLicenseAttachmentMapper {
    long countByExample(SupplierLicenseAttachmentExample example);

    int deleteByExample(SupplierLicenseAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(SupplierLicenseAttachment record);

    int insertSelective(SupplierLicenseAttachment record);

    List<SupplierLicenseAttachment> selectByExample(SupplierLicenseAttachmentExample example);

    SupplierLicenseAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SupplierLicenseAttachment record, @Param("example") SupplierLicenseAttachmentExample example);

    int updateByExample(@Param("record") SupplierLicenseAttachment record, @Param("example") SupplierLicenseAttachmentExample example);

    int updateByPrimaryKeySelective(SupplierLicenseAttachment record);

    int updateByPrimaryKey(SupplierLicenseAttachment record);
}