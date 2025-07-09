package com.codingtu.cooltu.processor.dealer.builder.base;

import com.codingtu.cooltu.lib4j.data.data.JavaInfo;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tool.TagTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.container.BuilderMap;
import com.codingtu.cooltu.processor.log.Logs;

import java.io.File;

public class BaseBuilder {

    public JavaInfo javaInfo;
    protected BaseEs<String> lineEs;

    public BaseBuilder(JavaInfo javaInfo) {
        this.javaInfo = javaInfo;
        BuilderMap.put(builderType(), this);
    }

    protected BuilderType builderType() {
        return BuilderType.DEFAULT;
    }

    protected boolean isGetLines() {
        return true;
    }

    protected boolean isBuild() {
        return true;
    }

    protected boolean isForce() {
        return true;
    }

    public void create() {
        if (javaInfo == null) {
            return;
        }
        Logs.i(javaInfo.path);
        File file = new File(javaInfo.path);
        if (isGetLines() && (isForce() || !file.exists())) {
            getLines();
            beforeBuild();
            if (isBuild() && !lineEs.isNull()) {
                FileWriter.to(file).cover().write(lineEs);
            }
        }
    }


    protected void getLines() {
        lineEs = Es.es();
        dealLines();
    }

    protected void dealLines() {
        addLine("package [pkg];", javaInfo.pkg);
    }

    protected void beforeBuild() {
    }

    protected void addLine(String line, Object... objs) {
        lineEs.add(TagTool.dealLine(line, objs));
    }
}
