package com.codingtu.cooltu.lib4j.tool;

public class StringTool {
    /**************************************************
     *
     * 判断字符串是否为空。包括null和"","   "等
     *
     **************************************************/
    public static boolean isBlank(String text) {
        return text == null || text.trim().length() <= 0;
    }

    /**************************************************
     *
     * 判断字符串是否有值，不包括"","  "等
     *
     **************************************************/
    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }
}
