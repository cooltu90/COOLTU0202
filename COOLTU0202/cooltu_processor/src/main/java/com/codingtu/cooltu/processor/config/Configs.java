package com.codingtu.cooltu.processor.config;

import com.codingtu.cooltu.lib4j.config.LibConfigs;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

public class Configs extends LibConfigs {

    private final Messager messager;
    private final ProcessingEnvironment processingEnv;

    public Configs(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
    }

    public static void init(ProcessingEnvironment processingEnv) {
        configs(new Configs(processingEnv));
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
