package com.codingtu.cooltu.lib4a.tool;

import com.codingtu.cooltu.constant.PfName;
import com.codingtu.cooltu.lib4j.json.JsonTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

public class MeTool {

    private static Object me;

    public static <T> boolean isLogin(Class<T> clazz) {
        return me(clazz) != null;
    }


    public static <T> T me(Class<T> clazz) {
        if (me == null) {
            me = getMeFromStore(clazz);
        }
        return (T) me;
    }

    private static <T> T getMeFromStore(Class<T> clazz) {
        String json = PfTool.pf().getString(PfName.ME);
        if (StringTool.isBlank(json)) {
            return null;
        } else {
            return JsonTool.toDataObj(clazz, json);
        }
    }

    public static void setMe(Object me) {
        MeTool.me = me;
        storeMe(me);
    }

    private static void storeMe(Object me) {
        PfTool.pf().putString(PfName.ME, JsonTool.toJson(me));
    }

}
