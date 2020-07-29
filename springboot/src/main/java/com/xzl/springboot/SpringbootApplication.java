package com.xzl.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/27 14:39
 * @version: v0.0.1
 * @history:
 */
@SpringBootApplication
@EnableStateMachine
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
