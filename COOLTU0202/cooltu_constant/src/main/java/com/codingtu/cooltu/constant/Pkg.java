package com.codingtu.cooltu.constant;

public class Pkg {

    public static String R;

    //cooltu_processor
    public static final String PROCESSOR = "com.codingtu.cooltu.processor";
    public static final String PROCESSOR_DEALER = PROCESSOR + ".dealer";
    public static final String PROCESSOR_DEAL = PROCESSOR_DEALER + ".deal";

    public static String core() {
        return "core." + Module.CURRENT;
    }

    public static String actBase() {
        return core() + ".actbase";
    }
}
