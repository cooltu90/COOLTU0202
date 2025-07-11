package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.path.base.BaseFile;

import java.io.File;

public class ZipFile extends BaseFile {

    public ZipFile(String root) {
        super(root);
    }

    public ZipFile(File rootFile) {
        super(rootFile);
    }
}
