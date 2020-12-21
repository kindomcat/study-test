package com.example.security.securitytest.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/12/18 15:36
 * @version: v0.0.1
 * @history:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "111";
    }
}
