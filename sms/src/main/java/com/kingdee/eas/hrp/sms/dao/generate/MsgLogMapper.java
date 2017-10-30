package com.kingdee.eas.hrp.sms.dao.generate;

import com.kingdee.eas.hrp.sms.model.MsgLog;
import com.kingdee.eas.hrp.sms.model.MsgLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgLogMapper {
    long countByExample(MsgLogExample example);

    int deleteByExample(MsgLogExample example);

    int deleteByPrimaryKey(String seqid);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    List<MsgLog> selectByExample(MsgLogExample example);

    MsgLog selectByPrimaryKey(String seqid);

    int updateByExampleSelective(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByExample(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByPrimaryKeySelective(MsgLog record);

    int updateByPrimaryKey(MsgLog record);
}