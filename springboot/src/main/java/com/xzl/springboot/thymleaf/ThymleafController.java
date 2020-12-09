package com.xzl.springboot.thymleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/8/6 13:45
 * @version: v0.0.1
 * @history:
 */
@RequestMapping("/thymeleaf")
@Controller
public class ThymleafController {

    @GetMapping("/test")
    public String test(){
        return "index";
    }

    public static void main(String[] args) {
        System.out.println((int)((new SecureRandom().nextDouble()*9+1)*100000));
    }


}
