package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.io.File;
import java.util.List;

public class DataObjsFile<T> extends TextFile {

    private final Class<T> dataObjClass;

    public DataObjsFile(Class<T> dataObjClass, String root) {
        super(root);
        this.dataObjClass = dataObjClass;
    }

    public DataObjsFile(Class<T> dataObjClass, File rootFile) {
        super(rootFile);
        this.dataObjClass = dataObjClass;
    }

    public BaseEs<T> list() {
        return JsonTool.toDataObjs(dataObjClass, getText());
    }

    public void list(List<T> list) {
        setText(JsonTool.toJson(list));
    }

    public void list(BaseEs<T> es) {
        setText(JsonTool.toJson(es.toList()));
    }

}
