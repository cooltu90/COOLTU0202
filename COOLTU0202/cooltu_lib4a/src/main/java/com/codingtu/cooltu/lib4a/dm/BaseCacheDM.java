package com.codingtu.cooltu.lib4a.dm;

import com.codingtu.cooltu.lib4a.tool.PfTool;
import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.util.HashMap;
import java.util.Map;

public class BaseCacheDM {

    public static Map<String, Object> map;

    private static Map<String, Object> getMap() {
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    public static void cache(String key, Object obj) {
        //一级缓存
        getMap().put(key, obj);
        //二级缓存
        if (obj == null) {
            PfTool.pf().remove(key);
            return;
        }
        PfTool.pf().putString(key, JsonTool.toJson(obj));
    }

    public static <T> T getCache(Class<T> clazz, String key) {
        T t = (T) getMap().get(key);
        if (t == null) {
            t = JsonTool.toDataObj(clazz, PfTool.pf().getString(key));
            if (t != null) {
                getMap().put(key, t);
            }
        }
        return t;
    }
}
