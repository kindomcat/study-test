package com.xzl.springboot.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/28 9:41
 * @version: v0.0.1
 * @history:
 */
@RestController
public class TestController {

    @Autowired
    private StateMachine<BookStates, BookEvents> stateMachine;

    @GetMapping("/test")
    public void test(){
        stateMachine.start();
        stateMachine.sendEvent(BookEvents.RETURN);
        stateMachine.sendEvent(BookEvents.BORROW);
        stateMachine.stop();
    }
}
