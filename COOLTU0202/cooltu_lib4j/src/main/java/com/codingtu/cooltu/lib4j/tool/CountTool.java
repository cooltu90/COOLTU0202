package com.codingtu.cooltu.lib4j.tool;

import com.codingtu.cooltu.lib4j.es.CoreEs;

import java.util.Collection;
import java.util.Map;

public class CountTool {
    /**************************************************
     *
     * count 获取数组，集合，map的元素个数
     *
     **************************************************/

    public static int count(CoreEs vs) {
        return vs == null ? 0 : vs.count();
    }

    public static int count(Collection cs) {
        return cs == null ? 0 : cs.size();
    }

    public static int count(Map map) {
        return map == null ? 0 : map.size();
    }

    public static int count(Object[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(String[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(int[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(byte[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(long[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(char[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(float[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(double[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(boolean[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(short[] objs) {
        return objs == null ? 0 : objs.length;
    }

    public static int count(String str) {
        return str == null ? 0 : str.length();
    }

    /**************************************************
     *
     * isNull 判断是否为空
     *
     **************************************************/
    public static boolean isNull(CoreEs ts) {
        return count(ts) <= 0;
    }

    public static boolean isNull(Collection cs) {
        return count(cs) <= 0;
    }

    public static boolean isNull(Map map) {
        return count(map) <= 0;
    }

    public static boolean isNull(Object[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(String[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(int[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(byte[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(long[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(char[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(float[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(double[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(boolean[] objs) {
        return count(objs) <= 0;
    }

    public static boolean isNull(short[] objs) {
        return count(objs) <= 0;
    }

}
