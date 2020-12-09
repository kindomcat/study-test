package com.xzl.springintegrationmqtt.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/2/19 15:46
 * @version: v0.0.1
 * @history:
 */
@Getter
public class MqttReceiveEvent extends ApplicationEvent {


    private String topic;
    private String message;

    public MqttReceiveEvent(Object source, String topic, String message) {
        super(source);
        this.topic = topic;
        this.message = message;
    }
}
