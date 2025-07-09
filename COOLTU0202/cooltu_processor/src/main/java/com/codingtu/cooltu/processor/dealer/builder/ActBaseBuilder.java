package com.codingtu.cooltu.processor.dealer.builder;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.value.TValue;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.lib4j.tool.ClassTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.base.BaseClass;
import com.codingtu.cooltu.processor.annotation.base.Genericity;
import com.codingtu.cooltu.processor.container.BuilderMap;
import com.codingtu.cooltu.processor.dealer.builder.base.BaseBuilder;
import com.codingtu.cooltu.processor.log.Logs;
import com.codingtu.cooltu.processor.tool.BuilderTool;

public class ActBaseBuilder extends BaseBuilder {

    public BaseClass baseClass;

    public ActBaseBuilder(String activityName) {
        super(BuilderTool.actBaseJavaInfo(activityName));
        BuilderMap.putActBaseBuilder(this);
    }

    @Override
    protected BuilderType builderType() {
        return BuilderType.ACTBASE;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild() {
        super.beforeBuild();
        lineEs.log();
    }

    @Override
    protected void dealLines() {
        super.dealLines();
        StringEs actBaseGenericityStrEs = Es.strs();
        StringEs baseGenericityStrEs = Es.strs();
        TValue<String> baseClassFullName = TValue.obtain();
        getGenericities(baseClassFullName, actBaseGenericityStrEs, baseGenericityStrEs);

        addLine("");
        addLine("public abstract class [ActivityBase][Genericities]", javaInfo.name, actBaseGenericityStrEs.toGenericities());
        addLine("        extends [baseClass][Genericities] {", baseClassFullName.value, baseGenericityStrEs.toGenericities());
        addLine("");
        addLine("}");
    }

    private void getGenericities(TValue<String> baseClassFullName, StringEs actBaseGenericityStrEs, StringEs baseGenericityStrEs) {

        if (baseClass == null) {
            baseClassFullName.value = FullName.BASE_ACTIVITY;
            return;
        }
        Genericity[] genericities = baseClass.genericities();
        BaseEs<Genericity> es = Es.es(genericities);

        baseClassFullName.value = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return baseClass.value();
            }
        });

        es.ls(new Es.EachEs<Genericity>() {
            @Override
            public boolean each(int position, Genericity genericity) {

                String genericityClassFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return genericity.value();
                    }
                });
                if (ClassTool.isNotVoid(genericityClassFullName)) {
                    baseGenericityStrEs.add(genericityClassFullName);
                } else {
                    String name = genericity.name();
                    String extendsClassFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                        @Override
                        public Object get() {
                            return genericity.extendsClass();
                        }
                    });
                    if (ClassTool.isNotVoid(extendsClassFullName)) {
                        actBaseGenericityStrEs.add(name + " extends " + extendsClassFullName);
                    } else {
                        actBaseGenericityStrEs.add(name);
                    }
                    baseGenericityStrEs.add(name);
                }
                return false;
            }
        });
    }
}
