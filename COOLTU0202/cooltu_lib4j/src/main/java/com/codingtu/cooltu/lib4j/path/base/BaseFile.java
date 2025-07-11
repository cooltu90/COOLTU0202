package com.codingtu.cooltu.lib4j.path.base;

import com.codingtu.cooltu.lib4j.data.data.LibDataObj;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.File;

public class BaseFile extends LibDataObj {

    private String ROOT;
    private File ROOT_FILE;
    private String FILE_NAME;
    private String FILE_TYPE;

    public BaseFile(String root) {
        this.ROOT = root;
    }

    public BaseFile(File rootFile) {
        this.ROOT = rootFile.getAbsolutePath();
        this.ROOT_FILE = rootFile;
    }

    public String root() {
        return this.ROOT;
    }

    public File rootFile() {
        if (ROOT_FILE == null) {
            ROOT_FILE = new File(ROOT);
        }
        return ROOT_FILE;
    }

    public String fileName() {
        if (StringTool.isBlank(FILE_NAME)) {
            FILE_NAME = rootFile().getName();
        }
        return FILE_NAME;
    }

    public String fileType() {
        if (StringTool.isBlank(FILE_TYPE)) {
            FILE_TYPE = FileTool.type(rootFile());
        }
        return FILE_TYPE;
    }

    public String child(String name) {
        return this.ROOT + FileTool.addPrexSeparator(name);
    }

}
