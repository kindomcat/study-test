package com.xzl.tdengine.tdengine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xzl.tdengine.tdengine.dao"})
public class TdengineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdengineTestApplication.class, args);
    }

}
