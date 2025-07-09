package com.codingtu.cooltu.lib4j.json.base;

import com.codingtu.cooltu.lib4j.es.BaseEs;

import java.util.Set;

public interface JO {
    String getString(String key);

    Integer getInteger(String key);

    Long getLong(String key);

    Boolean getBoolean(String key);

    Double getDouble(String key);

    Float getFloat(String key);

    JA getJA(String key);

    JO getJO(String key);

    <T> T getDataObj(String key, Class<T> tClass);

    <T> T toDataObj(Class<T> tClass);

    <T> BaseEs<T> getDataObjs(String datas, Class<T> tClass);

    JO put(String key, Object value);

    String toJson();

    Set<String> keys();
}