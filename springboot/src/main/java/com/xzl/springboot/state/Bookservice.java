package com.xzl.springboot.state;

import lombok.Getter;
import lombok.Setter;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/28 9:58
 * @version: v0.0.1
 * @history:
 */
@WithStateMachine
public class Bookservice {
    @Setter
    @Getter
    private String status = BookStates.AVAILABLE.name();

    @OnTransition(target = "BORROWED")
    public void online() {
        System.out.println(BookStates.BORROWED);
        setStatus(BookStates.BORROWED.name());
    }



}
