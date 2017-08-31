package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.OrderEntry;
import com.kingdee.eas.hrp.sms.model.OrderEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderEntryMapper {
    long countByExample(OrderEntryExample example);

    int deleteByExample(OrderEntryExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderEntry record);

    int insertSelective(OrderEntry record);

    List<OrderEntry> selectByExample(OrderEntryExample example);

    OrderEntry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderEntry record, @Param("example") OrderEntryExample example);

    int updateByExample(@Param("record") OrderEntry record, @Param("example") OrderEntryExample example);

    int updateByPrimaryKeySelective(OrderEntry record);

    int updateByPrimaryKey(OrderEntry record);
}