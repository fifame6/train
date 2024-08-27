package com.fifame.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import com.fifame.train.common.context.LoginMemberContext;
import com.fifame.train.common.util.SnowUtil;
import com.fifame.train.member.domain.Passenger;
import com.fifame.train.member.mapper.PassengerMapper;
import com.fifame.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName PassengerService
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 18:23
 * @Version 1.0
 */
@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void savePassenger(PassengerSaveReq req) {
        //一般新增表中的数据,会对 id, createTime,updateTime进行新增
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setMemberId(LoginMemberContext.getId());
        LocalDateTime dateTime = LocalDateTime.now();
        passenger.setCreateTime(dateTime);
        passenger.setUpdateTime(dateTime);
        passengerMapper.insert(passenger);
    }
}

