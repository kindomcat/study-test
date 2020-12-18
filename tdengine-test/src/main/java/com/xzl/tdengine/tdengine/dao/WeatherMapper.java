package com.xzl.tdengine.tdengine.dao;

import com.xzl.tdengine.tdengine.domain.Weather;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherMapper {

    int insert(Weather weather);

    int batchInsert(List<Weather> weatherList);

    List<Weather> select(@Param("limit") Long limit, @Param("offset") Long offset);

    void createDB();

    void createTable();
}
