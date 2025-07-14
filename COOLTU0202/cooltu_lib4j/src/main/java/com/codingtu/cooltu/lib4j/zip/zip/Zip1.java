package com.codingtu.cooltu.lib4j.zip.zip;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.exception.ZipException;
import com.codingtu.cooltu.lib4j.exception.ZipFailException;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.path.ZipFile;
import com.codingtu.cooltu.lib4j.tool.CountTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.File;
import java.util.HashMap;
import java.util.zip.ZipOutputStream;

import sun.jvm.hotspot.oops.FieldType;

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
        xxx(srcFile, zipName, "");

        Es.maps(pathMap).ls(new Es.MapEach<String, String>() {
            @Override
            public boolean each(String s, String s2) {
                LibLogs.i("src:" + s + " zip:" + s2);
                return false;
            }
        });

        super.zip();
    }

//    10:05:59.247  I  rootDir:3
//            10:05:59.247  I  path:3/output_003.mp4
//10:06:05.519  I  path:3/output_005.mp4
//10:06:11.121  I  path:3/output_007.mp4
//10:06:15.447  I  path:3/output_004.mp4
//10:06:20.494  I  path:3/output_000.mp4
//10:06:26.297  I  path:2/output_006.mp4
//10:06:32.004  I  path:2/output_002.mp4
//10:06:37.609  I  path:2/output_001.mp4
//10:06:43.045  I  path:2/z_video/record02.mp4
//10:06:43.453  I  path:2/z_video/record03.mp4
//10:06:43.825  I  path:2/z_video/record01.mp4
//10:06:44.237  I  path:2/z_video/record00.mp4


//    10:09:07.433  I  rootDir:2
//            10:09:07.433  I  path:/2.mp4

    private void xxx(File file, String zipName, String parentName) {
        if (filePass.isPass(file)) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    File file1 = files[i];
                    xxx(file1, zipName, StringTool.isBlank(parentName) ? zipName : (parentName + FileTool.addPrexSeparator(file.getName())));
                }
            }
        } else {
            //加入map
            pathMap.put(file.getAbsolutePath(), parentName + FileTool.addPrexSeparator(file.getName()));
        }
    }
}
