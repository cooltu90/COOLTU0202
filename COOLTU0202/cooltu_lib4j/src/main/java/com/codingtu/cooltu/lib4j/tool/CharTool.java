package com.codingtu.cooltu.lib4j.tool;

public class CharTool {

    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isLowerLine(char c) {
        return c == '_';
    }

    public static boolean isMiddleLine(char c) {
        return c == '-';
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**************************************************
     *
     * 字母大小写的操作
     *
     **************************************************/
    public static char toUpper(char c) {
        return (char) (c - 32);
    }

    public static char toLower(char c) {
        return (char) (c + 32);
    }

}
