package com.fifame.train.member.controller;

import com.fifame.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author fifame
 * @DATE 2023-09-25 20:25
 * @Version 1.0
 */
@RestController()
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/count")
    public int sayHello(){
        return memberService.count();
    }
}

