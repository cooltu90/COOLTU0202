package com.codingtu.cooltu.lib4j.json;

import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.json.base.JA;
import com.codingtu.cooltu.lib4j.json.base.JO;
import com.codingtu.cooltu.lib4j.json.base.JsonHolder;
import com.codingtu.cooltu.lib4j.json.fastjson.FastJsonHolder;

public class JsonTool {
    private static JsonHolder JSON;

    private static JsonHolder getJSON() {
        if (JSON == null) {
            JSON = LibConfigs.configs().createJsonHolder();
            if (JSON == null)
                JSON = new FastJsonHolder();
        }
        return JSON;
    }

    public static <T> T toDataObj(Class<T> tClass, String json) {
        return getJSON().toDataObj(tClass, json);
    }

    public static <T> BaseEs<T> toDataObjs(Class<T> tClass, String json) {
        return getJSON().toDataObjs(tClass, json);
    }

    public static String toJson(Object obj) {
        return getJSON().toJson(obj);
    }

    public static JO toJO(String json) {
        return getJSON().toJO(json);
    }

    public static JA toJA(String json) {
        return getJSON().toJA(json);
    }

    public static JO createJO() {
        return getJSON().createJO();
    }
}
