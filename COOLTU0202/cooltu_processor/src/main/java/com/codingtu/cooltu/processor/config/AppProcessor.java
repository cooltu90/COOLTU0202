package com.codingtu.cooltu.processor.config;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.SupportTypes;
import com.codingtu.cooltu.processor.tool.IdTool;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    private Set<String> supportTypes = new HashSet<>();
    private BaseEs<Class> supportTypeEs;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTool.trees = Trees.instance(processingEnv);
        IdTool.rScanner = new IdTool.RScanner();
        Configs.init(processingEnv.getMessager());

        supportTypeEs = SupportTypes.types();
        supportTypeEs.ls(new Es.EachEs<Class>() {
            @Override
            public boolean each(int position, Class annoClass) {
                supportTypes.add(annoClass.getCanonicalName());
                return false;
            }
        });

    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportTypes;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
