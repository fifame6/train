package com.fifame.train.member.controller;

import com.fifame.train.common.response.CommonResp;
import com.fifame.train.member.req.PassengerSaveReq;
import com.fifame.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

