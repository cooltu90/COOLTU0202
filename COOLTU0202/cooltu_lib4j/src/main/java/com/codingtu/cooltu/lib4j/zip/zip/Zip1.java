package com.codingtu.cooltu.lib4j.zip.zip;

import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.path.ZipFile;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.io.File;
import java.util.HashMap;

public class Zip1 extends ZipBase<Zip1> {

    private File srcFile;
    private FilePass filePass;
    private PathDeal pathDeal;

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.filePass = null;
        this.pathDeal = null;
    }

    public Zip1(String srcPath) {
        this(new File(srcPath));
    }

    public Zip1(File srcFile) {
        this.srcFile = srcFile;
    }

    public Zip1 zipFile(ZipFile zipFile) {
        this.zipFile = zipFile;
        return this;
    }

    public Zip1 zipFile(String zipFilePath) {
        this.zipFile = new ZipFile(zipFilePath);
        return this;
    }

    public Zip1 zipFile(File zipFile) {
        this.zipFile = new ZipFile(zipFile);
        return this;
    }

    public Zip1 filePass(FilePass filePass) {
        this.filePass = filePass;
        return this;
    }

    public Zip1 pathDeal(PathDeal pathDeal) {
        this.pathDeal = pathDeal;
        return this;
    }

    @Override
    public void zip() {

        pathMap = new HashMap<>();

        xxx(srcFile);


        super.zip();
    }

    private void xxx(File file) {
        if (filePass.isPass(file)) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    File file1 = files[i];
                    xxx(file);
                }
            }
        } else {
            //加入map
            //pathMap.put(file.getAbsolutePath(),)
        }
    }
}
