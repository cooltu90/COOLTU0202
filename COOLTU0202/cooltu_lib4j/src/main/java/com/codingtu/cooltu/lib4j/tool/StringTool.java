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

    /**************************************************
     *
     * object转换成string
     *
     **************************************************/
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return String.valueOf(obj);
        }
    }


    /**************************************************
     *
     * 重复拼接字符串
     *
     * @param times 重复几次
     * @param str 重复的字符串
     * @return 拼接的字符串
     *
     **************************************************/
    public static String repeatString(int times, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * trim
     *
     **************************************************/
    public static String trim(String str, char x) {
        if (str == null) {
            return null;
        }

        int left = getLeftIndex(str, x);
        int right = getRightIndex(str, x);

        if (left >= right) {
            return null;
        } else {
            return str.substring(left, right);
        }
    }

    public static String trimLeft(String str, char x) {
        if (str == null) {
            return null;
        }
        int index = getLeftIndex(str, x);

        if (index == str.length()) {
            return null;
        } else {
            return str.substring(index);
        }
    }

    public static String trimRight(String str, char x) {
        if (str == null) {
            return null;
        }

        int index = getRightIndex(str, x);
        if (index == 0) {
            return null;
        } else {
            return str.substring(0, index);
        }
    }

    private static int getLeftIndex(String str, char x) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != x) {
                break;
            }
            index++;
        }
        return index;
    }

    private static int getRightIndex(String str, char x) {
        int index = str.length();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c != x) {
                break;
            }
            index--;
        }
        return index;
    }
}
