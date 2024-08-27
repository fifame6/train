package com.fifame.train.member.controller;

import com.fifame.train.common.response.CommonResp;
import com.fifame.train.member.req.MemberLoginReq;
import com.fifame.train.member.req.MemberRegisterReq;
import com.fifame.train.member.req.MemberSendCodeReq;
import com.fifame.train.member.resp.MemberLoginResp;
import com.fifame.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author fifame
 * @DATE 2023-09-25 20:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

//    @GetMapping("/count")
//    public Integer count() {
//        return memberService.count();
//    }
    @GetMapping("/count")
    public CommonResp<Integer> sayHello(){
        int count = memberService.count();
        CommonResp<Integer> integerCommonResp = new CommonResp<>();
        integerCommonResp.setContent(count);
        return integerCommonResp;
    }

    @PostMapping("/register")
    public CommonResp register(@Valid MemberRegisterReq req){
        long register = memberService.register(req);
        CommonResp<Long> integerCommonResp = new CommonResp<>();
        integerCommonResp.setContent(register);
        return integerCommonResp;
    }


    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody MemberLoginReq req){
        MemberLoginResp memberLoginResp = memberService.login(req);
        CommonResp<MemberLoginResp> integerCommonResp = new CommonResp<>();
        integerCommonResp.setContent(memberLoginResp);
        return integerCommonResp;
    }


    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp();
    }



}

