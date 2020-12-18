package com.xzl.tdengine.tdengine.dao;

import java.util.Map;

public interface DatabaseMapper {

    int createDatabase(String dbname);

    int dropDatabase(String dbname);

    int creatDatabaseWithParameters(Map<String, String> map);

    int useDatabase(String dbname);

}
