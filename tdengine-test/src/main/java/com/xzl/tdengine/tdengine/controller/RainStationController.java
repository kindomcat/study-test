package com.xzl.tdengine.tdengine.controller;
import com.xzl.tdengine.tdengine.domain.Rainfall;
import com.xzl.tdengine.tdengine.service.RainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rainstation")
public class RainStationController {

    @Autowired
    private RainStationService service;

    @GetMapping("/init")
    public boolean init() {
        service.init();
        service.createTable();
        return true;
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Rainfall rainfall){
        return service.insert(rainfall);
    }

}
