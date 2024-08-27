package com.fifame.train.member.mapper;

import com.fifame.train.member.domain.SMSVerification;
import com.fifame.train.member.domain.SMSVerificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SMSVerificationMapper {
    long countByExample(SMSVerificationExample example);

    int deleteByExample(SMSVerificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SMSVerification record);

    int insertSelective(SMSVerification record);

    List<SMSVerification> selectByExample(SMSVerificationExample example);

    SMSVerification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SMSVerification record, @Param("example") SMSVerificationExample example);

    int updateByExample(@Param("record") SMSVerification record, @Param("example") SMSVerificationExample example);

    int updateByPrimaryKeySelective(SMSVerification record);

    int updateByPrimaryKey(SMSVerification record);
}