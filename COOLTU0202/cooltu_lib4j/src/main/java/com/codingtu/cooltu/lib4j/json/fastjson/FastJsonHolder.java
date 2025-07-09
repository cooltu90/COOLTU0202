package com.codingtu.cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.json.base.JA;
import com.codingtu.cooltu.lib4j.json.base.JO;
import com.codingtu.cooltu.lib4j.json.base.JsonHolder;
import com.codingtu.cooltu.lib4j.log.LibLogs;

import java.util.List;

public class FastJsonHolder implements JsonHolder {
    @Override
    public <T> T toDataObj(Class<T> tClass, String json) {
        try {
            return JSON.parseObject(json, tClass);
        } catch (Throwable e) {
            if (LibConfigs.configs().isLogJsonException())
                LibLogs.w(e);
            return null;
        }
    }

    @Override
    public String toJson(Object obj) {
        if (obj == null)
            return null;
        return JSON.toJSONString(obj);
    }

    @Override
    public <T> BaseEs<T> toDataObjs(Class<T> tClass, String json) {
        List<T> ts = null;
        try {
            ts = JSON.parseArray(json, tClass);
        } catch (Throwable e) {
        }
        return Es.es(ts);
    }

    @Override
    public JO toJO(String json) {
        try {
            return new FastJO(JSON.parseObject(json));
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public JA toJA(String json) {
        try {
            return new FastJA(JSON.parseArray(json));
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public JO createJO() {
        return new FastJO(new JSONObject());
    }


}