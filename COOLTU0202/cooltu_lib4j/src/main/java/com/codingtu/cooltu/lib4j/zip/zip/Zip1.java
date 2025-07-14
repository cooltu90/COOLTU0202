package com.codingtu.cooltu.lib4j.zip.zip;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.exception.ZipFailException;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.path.ZipFile;
import com.codingtu.cooltu.lib4j.tool.CountTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

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
        if (zipFile == null) {
            File file = new File(srcFile.getParentFile(), srcFile.getName() + FileType.d_ZIP);
            zipFile = new ZipFile(file);
        }
        if (!zipFile.fileName().endsWith(FileType.d_ZIP)) {
            throw new ZipFailException("压缩文件后缀名不是zip");
        }
        String zipName = StringTool.cutSuffix(zipFile.fileName(), FileType.d_ZIP);
        dealPath(srcFile, zipName, "");

        Es.maps(pathMap).ls(new Es.MapEach<String, String>() {
            @Override
            public boolean each(String s, String s2) {
                LibLogs.i("src:" + s + " zip:" + s2);
                return false;
            }
        });

        super.zip();
    }

    private void dealPath(File file, String zipName, String parentName) {
        if (filePass != null && filePass.isPass(file)) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    File file1 = files[i];
                    dealPath(file1, zipName, StringTool.isBlank(parentName) ? zipName : (parentName + FileTool.addPrexSeparator(file.getName())));
                }
            }
        } else {
            //加入map
            String zipPath = parentName + FileTool.addPrexSeparator(file.getName());
            if (pathDeal != null) {
                zipPath = pathDeal.deal(zipPath);
            }
            pathMap.put(file.getAbsolutePath(), zipPath);
        }
    }
}
