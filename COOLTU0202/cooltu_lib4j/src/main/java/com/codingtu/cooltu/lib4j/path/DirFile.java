package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.path.base.BaseFile;

import java.io.File;

public class DirFile extends BaseFile {
    public DirFile(String root) {
        super(root);
    }

    public DirFile(File rootFile) {
        super(rootFile);
    }

    public BaseEs<File> getFileTs() {
        return Es.es(rootFile().listFiles());
    }
}
