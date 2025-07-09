package com.codingtu.cooltu.lib4j.es.map;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class CoreMap<K, V, THIS extends CoreMap> {

    ///////////////////////////////////////////////////////
    //
    // 数据
    //
    ///////////////////////////////////////////////////////
    protected Map<K, V> map;

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public CoreMap() {
        this.map = new HashMap<>();
    }

    public CoreMap(Map<K, V> map) {
        if (map == null) {
            this.map = new HashMap<>();
        } else {
            this.map = map;
        }
    }

    ///////////////////////////////////////////////////////
    //
    // put方法
    //
    ///////////////////////////////////////////////////////
    public THIS put(K k, V v) {
        this.map.put(k, v);
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////
    public V get(K k) {
        return this.map.get(k);
    }

    public KV<K, V> getKV(K k) {
        V v = this.map.get(k);
        if (v != null) {
            return new KV<>(k, v);
        }
        return null;
    }

    ///////////////////////////////////////////////////////
    //
    // each
    //
    ///////////////////////////////////////////////////////
    public THIS ls(Es.MapEach<K, V> mapEach) {
        Set<K> ks = this.map.keySet();
        for (K k : ks) {
            if (mapEach.each(k, map.get(k))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // logs
    //
    ///////////////////////////////////////////////////////
    public THIS log() {
        ls(new Es.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                LibLogs.i("k:" + k + " v:" + JsonTool.toJson(v));
                return false;
            }
        });
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // delete
    //
    ///////////////////////////////////////////////////////
    public THIS delete(K k) {
        this.map.remove(k);
        return (THIS) this;
    }

    public List<V> toValueList() {
        ArrayList<V> list = new ArrayList<>();
        ls(new Es.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                list.add(v);
                return false;
            }
        });
        return list;
    }

    public BaseEs<V> toValueTs() {
        return Es.es(toValueList());
    }
}
