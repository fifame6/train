package com.fifame.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fifame.train.common.context.LoginMemberContext;
import com.fifame.train.common.response.CommonResp;
import com.fifame.train.common.response.PageResp;
import com.fifame.train.common.util.SnowUtil;
import com.fifame.train.member.domain.Passenger;
import com.fifame.train.member.domain.PassengerExample;
import com.fifame.train.member.mapper.PassengerMapper;
import com.fifame.train.member.req.PassengerQueryReq;
import com.fifame.train.member.req.PassengerSaveReq;
import com.fifame.train.member.resp.PassengerQueryResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName PassengerService
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 18:23
 * @Version 1.0
 */
@Service
public class PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;


    //保存和更新
    public void savePassenger(PassengerSaveReq req) {
        //一般新增表中的数据,会对 id, createTime,updateTime进行新增
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        LocalDateTime dateTime = LocalDateTime.now();
        if (ObjectUtil.isNull(passenger.getId())) {
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setCreateTime(dateTime);
            passenger.setUpdateTime(dateTime);
            passengerMapper.insert(passenger);
        } else {
            //更新
            passenger.setUpdateTime(dateTime);
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }


    //根据memberId条件查询passenger列表

    public  PageResp<PassengerQueryResp> queryPassengerList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.createCriteria().andMemberIdEqualTo(req.getMemberId());
        // TODO

        //分页
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        //查询总页数,总行数
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setList(passengerQueryResps);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    //根据id删除passenger
    public void delete(Long id) {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.createCriteria().andIdEqualTo(id);
        passengerMapper.deleteByExample(passengerExample);
    }

}

