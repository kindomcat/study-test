package com.xzl.tdengine.tdengine.service;
import com.xzl.tdengine.tdengine.dao.WeatherMapper;
import com.xzl.tdengine.tdengine.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherMapper weatherMapper;

    public boolean init() {
        weatherMapper.createDB();
        weatherMapper.createTable();
        return true;
    }

    public List<Weather> query(Long limit, Long offset) {
        return weatherMapper.select(limit, offset);
    }

    public int save(int temperature, float humidity,String name) {
        Weather weather = new Weather();
        weather.setTemperature(temperature);
        weather.setHumidity(humidity);
        weather.setName(name);
        return weatherMapper.insert(weather);
    }

    public int save(List<Weather> weatherList) {
        return weatherMapper.batchInsert(weatherList);
    }

}
