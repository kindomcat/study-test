package com.xzl.springintegrationmqtt.service;

import com.xzl.springintegrationmqtt.handler.MqttReceive;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/12/9 10:14
 * @version: v0.0.1
 * @history:
 */
@Component
@Slf4j
public class MqttListenter {


    @EventListener
    @Async(value = "mqttHandlerMessage")
    @SneakyThrows
    public void eventListener(MqttReceive mqttReceive){
        log.info("正在处理消息中=======================================",mqttReceive);
        //Thread.sleep(1000);
    }
}
