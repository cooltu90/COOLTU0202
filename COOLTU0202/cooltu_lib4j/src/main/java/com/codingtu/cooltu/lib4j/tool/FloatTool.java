package com.codingtu.cooltu.lib4j.tool;

public class FloatTool {

    public static String toString(float num, int bit, boolean trim) {
        return toString(Float.valueOf(num), bit, trim);
    }

    public static String toString(Float num, int bit, boolean trim) {
        return DoubleTool.toString(Double.valueOf(num), bit, trim);
    }

}
