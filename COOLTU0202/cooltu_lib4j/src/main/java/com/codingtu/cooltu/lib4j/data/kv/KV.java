package com.codingtu.cooltu.lib4j.data.kv;

import com.codingtu.cooltu.lib4j.data.data.LibDataObj;

public class KV<K, V> extends LibDataObj {
    public K k;
    public V v;

    public KV() {
    }

    public KV(K k, V v) {
        this.k = k;
        this.v = v;
    }
}
