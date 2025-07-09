package com.codingtu.cooltu.lib4j.es.map;

import java.util.Map;

public class BaseMap<K, V> extends CoreMap<K, V, BaseMap> {

    public BaseMap() {
    }

    public BaseMap(Map<K, V> map) {
        super(map);
    }
}
