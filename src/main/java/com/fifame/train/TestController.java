package com.fifame.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author fifame
 * @DATE 2023-09-25 20:25
 * @Version 1.0
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello worldd12";
    }
}

