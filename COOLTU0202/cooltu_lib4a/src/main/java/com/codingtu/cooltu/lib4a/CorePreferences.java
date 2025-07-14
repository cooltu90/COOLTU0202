package com.codingtu.cooltu.lib4a;

import android.content.SharedPreferences;

import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.lib4j.data.data.LibDataObj;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.util.List;
import java.util.Set;

public class CorePreferences {
    private SharedPreferences pf;

    public SharedPreferences sp() {
        if (pf == null) {
            pf = CoreConfigs.configs().pf();
        }
        return pf;
    }

    /**************************************************
     *
     * put
     *
     **************************************************/

    public void putString(String key, String value) {
        sp().edit().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        sp().edit().putInt(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        sp().edit().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        sp().edit().putFloat(key, value).commit();
    }

    public void putLong(String key, long value) {
        sp().edit().putLong(key, value).commit();
    }

    public void putStringSet(String key, Set<String> values) {
        sp().edit().putStringSet(key, values).commit();
    }

    public void putBean(String key, LibDataObj value) {
        sp().edit().putString(key, value == null ? null : value.toJson()).commit();
    }

    public <T> void putTs(String key, List<T> groups) {
        putString(key, JsonTool.toJson(groups));
    }

    /**************************************************
     *
     * get
     *
     **************************************************/

    public String getString(String key) {
        return sp().getString(key, null);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return sp().getInt(key, defValue);
    }

    public float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public float getFloat(String key, float defValue) {
        return sp().getFloat(key, defValue);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp().getBoolean(key, defValue);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defValue) {
        return sp().getLong(key, defValue);
    }

    public Set<String> getStringSet(String key) {
        return sp().getStringSet(key, null);
    }

    public <T> T getT(Class<T> tClass, String key) {
        String json = getString(key);
        return JsonTool.toDataObj(tClass, json);
    }

    public <T> BaseEs<T> getTs(Class<T> tClass, String key) {
        String json = getString(key);
        return JsonTool.toDataObjs(tClass, json);
    }

    /**************************************************
     *
     * 特别方法
     *
     **************************************************/

    public void putPermissionChecker(String key) {

    }

    public boolean getPermissionChecked(String key) {
        return getBoolean(key);
    }

    public void putPermissionChecked(String key) {
        putBoolean(key, true);
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public void remove(String key) {
        sp().edit().remove(key).commit();
    }


}
