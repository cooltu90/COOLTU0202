package com.codingtu.cooltu.processor.config;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.ClassTool;
import com.codingtu.cooltu.processor.SupportTypes;
import com.codingtu.cooltu.processor.container.BuilderMap;
import com.codingtu.cooltu.processor.dealer.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.log.Logs;
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
import javax.lang.model.element.Element;
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
        Configs.init(processingEnv);

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
        try {
            supportTypeEs.ls(new Es.EachEs<Class>() {
                @Override
                public boolean each(int position, Class annoClass) {
                    Set<Element> es = roundEnvironment.getElementsAnnotatedWith(annoClass);
                    String dealSampleName = annoClass.getSimpleName() + Suffix.PROCESS_DEAL;
                    String dealClassFullName = ClassTool.getClassFullName(Pkg.PROCESSOR_DEAL, dealSampleName);
                    Class dealClass = ClassTool.getClass(dealClassFullName);
                    Es.es(es).ls(new Es.EachEs<Element>() {
                        @Override
                        public boolean each(int position, Element element) {
                            try {
                                BaseDeal deal = (BaseDeal) dealClass.getConstructor().newInstance();
                                deal.dealElement(element);
                            } catch (Exception e) {
                                Logs.i(e);
                            }
                            return false;
                        }
                    });
                    return false;
                }
            });

            BuilderMap.create();
        } catch (Exception e) {
            Logs.i(e);
            throw new RuntimeException("报错了");
        }
        return true;
    }
}
