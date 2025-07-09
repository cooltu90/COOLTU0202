package com.codingtu.cooltu.lib4j.convert;

import java.io.File;

public class ToPath {

    /**************************************************
     * 包名转路径，以\开始
     **************************************************/
    public static String pkgToPath(String pkg) {
        if (pkg == null)
            return null;
        int length = pkg.length();
        if (length <= 0)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (int i = 0; i < length; i++) {
            char c = pkg.charAt(i);
            if (c == '.') {
                sb.append(File.separator);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
