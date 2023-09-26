package com.fifame.train.member.service;

import com.fifame.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberService
 * @Description TODO
 * @Author fifame
 * @DATE 2023-09-26 17:31
 * @Version 1.0
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);

    }
}

