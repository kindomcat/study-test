package com.xzl.tdengine.tdengine.dao;

import com.xzl.tdengine.tdengine.domain.TableMetadata;

public interface TableMapper {

    boolean createSTable(TableMetadata tableMetadata);
}
