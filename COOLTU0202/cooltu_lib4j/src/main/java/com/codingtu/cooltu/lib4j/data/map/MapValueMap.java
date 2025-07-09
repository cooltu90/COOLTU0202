package com.codingtu.cooltu.lib4j.data.map;

import java.util.HashMap;
import java.util.Map;

public class MapValueMap<K0, K1, V> extends ValueMap<K0, Map<K1, V>> {
    @Override
    protected Map<K1, V> newValue() {
        return new HashMap<>();
    }
}
