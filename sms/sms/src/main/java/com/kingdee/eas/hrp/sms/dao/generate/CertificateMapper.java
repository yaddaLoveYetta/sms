package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.CertificateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateMapper {
    long countByExample(CertificateExample example);

    int deleteByExample(CertificateExample example);

    int deleteByPrimaryKey(String id);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    List<Certificate> selectByExample(CertificateExample example);

    Certificate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByExample(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);
}