package com.codingtu.cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.json.base.JA;
import com.codingtu.cooltu.lib4j.json.base.JO;

import java.util.Set;

public class FastJO implements JO {
    private final JSONObject jo;

    public FastJO(JSONObject jo) {
        this.jo = jo;
    }

    @Override
    public String getString(String key) {
        return jo.getString(key);
    }

    @Override
    public Integer getInteger(String key) {
        return jo.getInteger(key);
    }

    @Override
    public Long getLong(String key) {
        return jo.getLong(key);
    }

    @Override
    public Boolean getBoolean(String key) {
        return jo.getBoolean(key);
    }

    @Override
    public Double getDouble(String key) {
        return jo.getDouble(key);
    }

    @Override
    public Float getFloat(String key) {
        return jo.getFloat(key);
    }

    @Override
    public JA getJA(String key) {
        return new FastJA(jo.getJSONArray(key));
    }

    @Override
    public JO getJO(String key) {
        return new FastJO(jo.getJSONObject(key));
    }

    @Override
    public <T> T getDataObj(String key, Class<T> tClass) {
        return this.jo.getObject(key, tClass);
    }

    @Override
    public <T> T toDataObj(Class<T> tClass) {
        return this.jo.toJavaObject(tClass);
    }

    @Override
    public <T> BaseEs<T> getDataObjs(String key, Class<T> tClass) {
        return getJA(key).toDataObjs(tClass);
    }

    @Override
    public JO put(String key, Object value) {
        jo.put(key, value);
        return this;
    }

    @Override
    public String toJson() {
        return jo.toJSONString();
    }

    @Override
    public Set<String> keys() {
        return jo.keySet();
    }
}