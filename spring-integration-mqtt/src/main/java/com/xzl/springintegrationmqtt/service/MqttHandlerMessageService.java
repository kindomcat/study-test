package com.xzl.springintegrationmqtt.service;

import com.xzl.springintegrationmqtt.handler.MqttReceive;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/12/9 9:50
 * @version: v0.0.1
 * @history:
 */
@Service
@Slf4j
public class MqttHandlerMessageService {

    @Autowired
    @Qualifier("mqttHandlerMessage")
    private AsyncTaskExecutor asyncTaskExecutor;

    private int total = 0;


    @Async(value = "mqttHandlerMessage")
    public void test(String msg, LocalDateTime old) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) this.asyncTaskExecutor;
        int size = taskExecutor.getThreadPoolExecutor().getQueue().size();
        total++;
        log.info("===========开始时间：{}==============结束时间：{}=======size:{}======total:{}======",old,LocalDateTime.now(),size,total);
    }


}
