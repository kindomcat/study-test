package com.xzl.springintegrationmqtt.handler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzl.common.util.TimeUtil;
import com.xzl.springintegrationmqtt.config.MqttConfig;
import com.xzl.springintegrationmqtt.event.MqttReceiveEvent;
import com.xzl.springintegrationmqtt.service.MqttHandlerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 获取消息
 *
 * @author wtf
 */
@Component
@Slf4j
public class MqttReceive {

/*    @Autowired
    private QueueChannel queueChannel;*/
    @Autowired
    private MqttHandlerMessageService mqttHandlerMessageService;
    @Autowired
    private ApplicationContext applicationContext;


    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

        /**
         * 处理mq消息
         * @return
         */
    @Bean
    //@ServiceActivator(inputChannel = MqttConfig.CHANNEL_NAME_IN,poller = @Poller(fixedRate = "50", maxMessagesPerPoll = "20"))
    @ServiceActivator(inputChannel = MqttConfig.CHANNEL_NAME_IN)
    public MessageHandler boxReceiveHandler(){
        return message -> {
            String msg =message.getPayload().toString();
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            long timestamp = (long)message.getHeaders().get("timestamp");
            LocalDateTime old = getDateTimeOfTimestamp(timestamp);
            log.error("发送的时间为:{},接收到的时间为：{}",old,LocalDateTime.now());
            //int size = queueChannel.getQueueSize();
            //log.info("现在堆积的消息尺寸：{}",size);

            mqttHandlerMessageService.test(msg,old);
            //MqttReceiveEvent mqttReceiveEvent = new MqttReceiveEvent(this, topic, msg);
            //applicationContext.publishEvent(mqttReceiveEvent);
            //JSONObject jsonObject = JSON.parseObject(msg);
           /* List<JSONObject> results = JSONObject.parseArray(String.valueOf(jsonObject.get("detects")), JSONObject.class);
            for (JSONObject result : results) {
                long timestamp = (long)result.get("long_timestamp");
                int size = queueChannel.getQueueSize();
                log.info("现在堆积的消息尺寸：{}",size);
                log.info("现在的时间是：{},接收到消息的时间是：{}", LocalDateTime.now(), TimeUtil.getDateTimeOfTimestamp(timestamp));
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        };
    }
}
