package com.fifame.train.member.controller;

import com.fifame.train.common.context.LoginMemberContext;
import com.fifame.train.common.response.CommonResp;
import com.fifame.train.common.response.PageResp;
import com.fifame.train.member.req.PassengerQueryReq;
import com.fifame.train.member.req.PassengerSaveReq;
import com.fifame.train.member.resp.PassengerQueryResp;
import com.fifame.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PassengerController
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 18:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;


    @RequestMapping("/save")
    public CommonResp savePassenger(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.savePassenger(req);
        return new CommonResp();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryPassengerList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> passengerQueryResps = passengerService.queryPassengerList(req);
        return new CommonResp<PageResp<PassengerQueryResp>>(passengerQueryResps);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp deleteByID(@PathVariable Long id) {
        passengerService.delete(id);
        return new CommonResp();
    }

}

