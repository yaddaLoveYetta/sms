package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.TaxCategory;
import com.kingdee.eas.hrp.sms.model.TaxCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaxCategoryMapper {
    long countByExample(TaxCategoryExample example);

    int deleteByExample(TaxCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaxCategory record);

    int insertSelective(TaxCategory record);

    List<TaxCategory> selectByExample(TaxCategoryExample example);

    TaxCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaxCategory record, @Param("example") TaxCategoryExample example);

    int updateByExample(@Param("record") TaxCategory record, @Param("example") TaxCategoryExample example);

    int updateByPrimaryKeySelective(TaxCategory record);

    int updateByPrimaryKey(TaxCategory record);
}