package com.codingtu.cooltu.processor.config;

import com.codingtu.cooltu.lib4j.config.LibConfigs;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Configs extends LibConfigs {

    private final Messager messager;

    public Configs(Messager messager) {
        this.messager = messager;
    }

    public static void init(Messager messager) {
        configs(new Configs(messager));
    }

    public static Configs configs() {
        return (Configs) LibConfigs.configs();
    }

    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public void baseLog(int level, String tag, String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
