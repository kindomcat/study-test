package com.xzl.tdengine.tdengine.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Weather {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")
    private Timestamp ts;

    private int temperature;

    private float humidity;

    private String name;
}
