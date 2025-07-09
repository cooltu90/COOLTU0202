package com.codingtu.cooltu.processor.tool;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.convert.ToPath;
import com.codingtu.cooltu.lib4j.data.data.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;

public class BuilderTool {

    public static JavaInfo actBaseJavaInfo(String actSimpleName) {
        return javaInfo(Module.CURRENT,
                new JavaInfo(Pkg.actBase(), actSimpleName + Suffix.ACT_BASE));
    }

    public static String layoutPath(String layoutName) {
        return layoutDir(Module.CURRENT) + FileTool.addPrexSeparator(layoutName + FileType.d_XML);
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 模块下的 javaInfo 设置path
     **************************************************/
    private static JavaInfo javaInfo(String module, JavaInfo info) {
        info.path = javaPath(module, info.pkg, info.name);
        return info;
    }

    /**************************************************
     * 模块下的 java 目录
     **************************************************/
    protected static String javaDir(String module) {
        return FileTool.getProjectDir()
                + FileTool.SEPARATOR
                + module
                + Path.SRC_MAIN_JAVA;
    }

    /**************************************************
     * 模块下的 layout 目录
     **************************************************/
    protected static String layoutDir(String module) {
        return FileTool.getProjectDir()
                + FileTool.SEPARATOR
                + module
                + Path.SRC_MAIN_RES_LAYOUT;
    }

    /**************************************************
     * 模块下的 pkg 目录
     **************************************************/
    protected static String pkgDir(String module, String pkg) {
        return javaDir(module) + ToPath.pkgToPath(pkg);
    }

    /**************************************************
     *  模块下的 .java 路径
     **************************************************/
    protected static String javaPath(String module, String pkg, String name) {
        return pkgDir(module, pkg)
                + FileTool.SEPARATOR
                + name
                + FileType.d_JAVA;
    }
}
