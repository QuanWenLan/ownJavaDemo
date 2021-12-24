package com.ett.report;

import com.google.common.collect.Maps;

import java.util.Map;

public class MapBuilder {

    private Map<String, Object> dataMap = Maps.newHashMap();

    public MapBuilder with(String key, Object value) {
        dataMap.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return dataMap;
    }

}
