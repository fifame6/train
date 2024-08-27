package com.fifame.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.fifame.train.common.exception.BusinessException;
import com.fifame.train.common.exception.BusinessExceptionEnum;
import com.fifame.train.common.util.JwtUtil;
import com.fifame.train.common.util.SnowUtil;
import com.fifame.train.member.domain.Member;
import com.fifame.train.member.domain.MemberExample;
import com.fifame.train.member.domain.SMSVerification;
import com.fifame.train.member.mapper.MemberMapper;
import com.fifame.train.member.mapper.SMSVerificationMapper;
import com.fifame.train.member.req.MemberLoginReq;
import com.fifame.train.member.req.MemberRegisterReq;
import com.fifame.train.member.req.MemberSendCodeReq;
import com.fifame.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @ClassName MemberService
 * @Description TODO
 * @Author fifame
 * @DATE 2023-09-26 17:31
 * @Version 1.0
 */
@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private SMSVerificationMapper verificationMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);
    }
//    public int count() {
//        return memberMapper.count();
//    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        //创建条件
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isNotEmpty(list)) {
            // return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        //使用雪花算法
        member.setId(SnowUtil.getSnowflakeNextId());
//        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        //查找数据库中是否有该手机号
        Member member = selectByMobile(mobile);
        // 如果手机号不存在,即member is null 则插入记录
//        if (member == null) {
        if (ObjectUtil.isNull(member)) {
            LOG.info("手机号不存在，插入一条记录");
            Member newMember = new Member();
            newMember.setId(SnowUtil.getSnowflakeNextId());
            newMember.setMobile(mobile);
            memberMapper.insert(newMember);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

        // 生成验证码
//        String randomString = RandomUtil.randomString(4);
        String randomString = "8888";
        LOG.info("生成的验证码: {}",randomString);


        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信记录表");

        SMSVerification smsVerification = new SMSVerification();
        smsVerification.setId(SnowUtil.getSnowflakeNextId());
        smsVerification.setVerificationCode(randomString);
        smsVerification.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        smsVerification.setIsUsed(true);
        smsVerification.setPhoneNumber(mobile);
        smsVerification.setUsedAt(LocalDateTime.now());
        smsVerification.setSendAt(LocalDateTime.now());
        smsVerification.setBusinessType("登录业务");
        verificationMapper.insert(smsVerification);
        // 对接短信通道，发送短信
        LOG.info("对接短信通道");

    }


    public MemberLoginResp login(@Valid MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        //一般不会直接将该对象返回出去,该对象是有密码的
        Member memberDB = selectByMobile(mobile);

        //手机号不存在则,插入手机号到数据库
        if (ObjectUtil.isNull(memberDB)) {
            Member member = new Member();
            member.setMobile(mobile);
            member.setId(SnowUtil.getSnowflakeNextId());
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //手机号存在,校验code,在缓存中/数据库中,获取
        if (!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }


    public Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(members)) {
            return null;
        } else {
            return members.get(0);
        }
    }



}

