package com.codingtu.cooltu.lib4j.data.data;

public class JavaInfo extends LibDataObj {
    public String path;
    public String name;
    public String pkg;
    public String fullName;
    public String genericities;

    public JavaInfo() {
    }

    public JavaInfo(String pkg, String name) {
        this.pkg = pkg;
        this.name = name;
        this.fullName = pkg + "." + name;
    }

    public JavaInfo(String fullName) {
        int index = fullName.indexOf("<");
        if (index >= 0) {
            genericities = fullName.substring(index + 1, fullName.length() - 1);
            fullName = fullName.substring(0, index);
        }

        this.fullName = fullName;
        int lastIndexOf = fullName.lastIndexOf(".");
        this.pkg = fullName.substring(0, lastIndexOf);
        this.name = fullName.substring(lastIndexOf + 1);
    }

}
