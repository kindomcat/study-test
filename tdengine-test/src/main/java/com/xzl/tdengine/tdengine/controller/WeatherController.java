package com.xzl.tdengine.tdengine.controller;
import com.xzl.tdengine.tdengine.domain.Weather;
import com.xzl.tdengine.tdengine.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/weather")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * create database and table
     *
     * @return
     */
    @GetMapping("/init")
    public boolean init() {
        return weatherService.init();
    }

    /**
     * Pagination Query
     *
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/{limit}/{offset}")
    public List<Weather> queryWeather(@PathVariable Long limit, @PathVariable Long offset) {
        return weatherService.query(limit, offset);
    }

    /**
     * upload single weather info
     *
     * @param temperature
     * @param humidity
     * @return
     */
    @PostMapping("/{temperature}/{humidity}/{name}")
    public int saveWeather(@PathVariable int temperature, @PathVariable float humidity,@PathVariable String name) {

        return weatherService.save(temperature, humidity,name);
    }

    /**
     * upload multi weather info
     *
     * @param weatherList
     * @return
     */
    @PostMapping("/batch")
    public int batchSaveWeather(@RequestBody List<Weather> weatherList) {

        return weatherService.save(weatherList);
    }

}
