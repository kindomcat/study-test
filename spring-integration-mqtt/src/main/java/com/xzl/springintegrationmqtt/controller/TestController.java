package com.xzl.springintegrationmqtt.controller;

import com.xzl.springintegrationmqtt.service.IMqttSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/11/24 15:42
 * @version: v0.0.1
 * @history:
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private IMqttSendService mqttSendService;

    @GetMapping("/test")
    public void test(){
        mqttSendService.sendToMqtt("1111");
    }
}
