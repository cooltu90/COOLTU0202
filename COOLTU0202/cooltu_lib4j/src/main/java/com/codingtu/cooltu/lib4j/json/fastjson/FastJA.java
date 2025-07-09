package com.codingtu.cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.json.base.JA;
import com.codingtu.cooltu.lib4j.json.base.JO;

public class FastJA implements JA {
    private final JSONArray ja;

    public FastJA(JSONArray ja) {
        this.ja = ja;
    }

    public JO getJO(int index) {
        return new FastJO(this.ja.getJSONObject(index));
    }

    @Override
    public <T> BaseEs<T> toDataObjs(Class<T> tClass) {
        return Es.es(this.ja.toJavaList(tClass));
    }

    @Override
    public int size() {
        return ja.size();
    }

}