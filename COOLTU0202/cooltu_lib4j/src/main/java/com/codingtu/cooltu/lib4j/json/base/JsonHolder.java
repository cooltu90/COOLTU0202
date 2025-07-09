package com.codingtu.cooltu.lib4j.json.base;

import com.codingtu.cooltu.lib4j.es.BaseEs;

public interface JsonHolder {
    <T> T toDataObj(Class<T> tClass, String json);

    <T> BaseEs<T> toDataObjs(Class<T> tClass, String json);

    String toJson(Object obj);

    JO toJO(String json);

    JO createJO();

    JA toJA(String json);
}