package com.codingtu.cooltu.lib4j.zip.zip;

import java.io.File;
import java.util.Map;

public class Zip {

    public static Zip0 map(Map<String, String> pathMap) {
        return new Zip0(pathMap);
    }

    public static Zip1 src(File srcFile) {
        return new Zip1(srcFile);
    }

    public static Zip1 src(String srcPath) {
        return src(new File(srcPath));
    }

}
