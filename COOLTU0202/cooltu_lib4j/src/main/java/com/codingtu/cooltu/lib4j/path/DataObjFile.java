package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.io.File;

public class DataObjFile<T> extends TextFile {

    private final Class<T> dataObjClass;

    public DataObjFile(Class<T> dataObjClass, String root) {
        super(root);
        this.dataObjClass = dataObjClass;
    }

    public DataObjFile(Class<T> dataObjClass, File rootFile) {
        super(rootFile);
        this.dataObjClass = dataObjClass;
    }

    public T dataObj() {
        return JsonTool.toDataObj(dataObjClass, getText());
    }

    public void dataObj(T dataObj) {
        setText(JsonTool.toJson(dataObj));
    }
}
