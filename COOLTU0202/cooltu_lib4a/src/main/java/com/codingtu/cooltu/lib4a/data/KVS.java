package com.codingtu.cooltu.lib4a.data;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KVS {

    private List<String> keys;
    private List<String> values;

    public KVS() {
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<String>();
    }

    public KVS add(String key, String value) {
        keys.add(key);
        values.add(value);
        return this;
    }

    public int size() {
        return CountTool.count(keys);
    }

    public String key(int index) {
        return keys.get(index);
    }

    public String value(int index) {
        return values.get(index);
    }

    public String value(String name) {
        return values.get(keys.indexOf(name));
    }

    public KVS add(KVS kvs) {
        if (kvs != null && kvs.size() > 0) {
            this.keys.addAll(kvs.keys);
            this.values.addAll(kvs.values);
        }
        return this;
    }

    @Override
    public String toString() {
        return "KVS{" +
                "names=" + keys +
                ", values=" + values +
                '}';
    }

    //转换一下。防止原list的顺序变化
    public BaseEs<String> keys() {
        BaseEs<String> keyEs = Es.es();
        for (int i = 0; i < CountTool.count(keys); i++) {
            keyEs.add(keys.get(i));
        }
        return keyEs;
    }

    public String orderKvs() {
        BaseEs<String> keyEs = keys();
        keyEs.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        keyEs.ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int i, String key) {
                if (i != 0) {
                    sb.append("&");
                }
                sb.append(key + "=" + value(key));
                return false;
            }
        });
        return sb.toString();
    }

    public String orderKvsWithEncode() throws UnsupportedEncodingException {
        BaseEs<String> keyEs = keys();
        keyEs.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        int count = keyEs.count();
        for (int i = 0; i < count; i++) {
            String key = keyEs.getByIndex(i);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(key + "=" + URLEncoder.encode(value(key), "utf-8").replaceAll("\\+", "%20"));
        }
        return sb.toString();
    }

    public String kvs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(keys.get(i) + "=" + values.get(i));
        }
        return sb.toString();
    }

}
