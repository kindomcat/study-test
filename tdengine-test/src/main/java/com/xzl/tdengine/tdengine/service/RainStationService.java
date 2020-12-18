package com.xzl.tdengine.tdengine.service;
import com.xzl.tdengine.tdengine.dao.DatabaseMapper;
import com.xzl.tdengine.tdengine.dao.RainfallMapper;
import com.xzl.tdengine.tdengine.dao.TableMapper;
import com.xzl.tdengine.tdengine.domain.FieldMetadata;
import com.xzl.tdengine.tdengine.domain.Rainfall;
import com.xzl.tdengine.tdengine.domain.TableMetadata;
import com.xzl.tdengine.tdengine.domain.TagMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RainStationService {

    @Autowired
    private DatabaseMapper databaseMapper;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private RainfallMapper rainfallMapper;

    public boolean init() {
        databaseMapper.dropDatabase("rainstation");

        Map<String, String> map = new HashMap<>();
        map.put("dbname", "rainstation");
        map.put("keep", "36500");
        map.put("days", "30");
        map.put("blocks", "4");
        databaseMapper.creatDatabaseWithParameters(map);

        databaseMapper.useDatabase("rainstation");
        return true;
    }

    public boolean createTable() {
        TableMetadata tableMetadata = new TableMetadata();
        tableMetadata.setDbname("rainstation");
        tableMetadata.setTablename("monitoring");

        List<FieldMetadata> fields = new ArrayList<>();
        fields.add(new FieldMetadata("ts", "timestamp"));
        fields.add(new FieldMetadata("name", "NCHAR(10)"));
        fields.add(new FieldMetadata("code", " BINARY(8)"));
        fields.add(new FieldMetadata("rainfall", "float"));
        tableMetadata.setFields(fields);

        List<TagMetadata> tags = new ArrayList<>();
        tags.add(new TagMetadata("station_code", "BINARY(8)"));
        tags.add(new TagMetadata("station_name", "NCHAR(10)"));
        tableMetadata.setTags(tags);

        tableMapper.createSTable(tableMetadata);
        return true;
    }


    public int insert(Rainfall rainfall) {
        Map<String, Object> map = new HashMap<>();
        map.put("dbname", "rainstation");
        map.put("table", "S_53646");
        map.put("stable", "monitoring");
        map.put("values", rainfall);
        return rainfallMapper.save(map);
    }
}
