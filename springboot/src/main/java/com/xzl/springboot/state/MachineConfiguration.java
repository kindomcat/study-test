package com.xzl.springboot.state;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/27 15:39
 * @version: v0.0.1
 * @history:
 */
@Configuration
@EnableStateMachine
public class MachineConfiguration extends EnumStateMachineConfigurerAdapter<BookStates,BookEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<BookStates, BookEvents> states)
            throws Exception {
        states.withStates().initial(BookStates.AVAILABLE).states(EnumSet.allOf(BookStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BookStates, BookEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(BookStates.AVAILABLE)
                .target(BookStates.BORROWED)
                .event(BookEvents.BORROW)
                .and()
                .withExternal()
                .source(BookStates.BORROWED)
                .target(BookStates.AVAILABLE)
                .event(BookEvents.RETURN)
                .and()
                .withExternal()
                .source(BookStates.AVAILABLE)
                .target(BookStates.IN_REPAIR)
                .event(BookEvents.START_REPAIR)
                .and()
                .withExternal()
                .source(BookStates.IN_REPAIR)
                .target(BookStates.AVAILABLE)
                .event(BookEvents.END_REPAIR);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BookStates, BookEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true);
    }
}
