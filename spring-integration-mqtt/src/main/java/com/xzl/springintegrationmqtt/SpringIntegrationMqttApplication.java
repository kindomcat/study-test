package com.xzl.springintegrationmqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAsync
public class SpringIntegrationMqttApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationMqttApplication.class, args);
    }

}
