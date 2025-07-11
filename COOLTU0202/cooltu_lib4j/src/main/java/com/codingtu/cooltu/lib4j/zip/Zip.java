package com.codingtu.cooltu.lib4j.zip;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.exception.ZipException;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip implements OnDestroy {

    private File src;
    private File desc;
    private long totalLen;
    private long zipedLen;
    private Integer cacheSize;
    private FilePass pass;
    private PathDeal zipedPathDeal;
    private OnCallBack.P1<Progress> onProgress;
    private OnCallBack.P1<Throwable> onError;
    private OnCallBack.P1<File> onFinish;
    private OnCallBack.P0 onStart;
    private long lastTime;
    private String rootDir;

    private Zip(File src) {
        this.src = src;
    }


    @Override
    public void onDestroy() {
        pass = null;
        zipedPathDeal = null;
        onProgress = null;
        onError = null;
        onFinish = null;
        onStart = null;
    }

    public static Zip src(File src) {
        return new Zip(src);
    }

    public static Zip src(String srcPath) {
        return src(new File(srcPath));
    }

    public Zip desc(File desc) {
        this.desc = desc;
        return this;
    }

    public Zip desc(String descPath) {
        return desc(new File(descPath));
    }

    public Zip progress(OnCallBack.P1<Progress> onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Zip pass(FilePass pass) {
        this.pass = pass;
        return this;
    }

    public Zip error(OnCallBack.P1<Throwable> onError) {
        this.onError = onError;
        return this;
    }

    public Zip finish(OnCallBack.P1<File> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Zip zipedPathDeal(PathDeal zipedPathDeal) {
        this.zipedPathDeal = zipedPathDeal;
        return this;
    }

    public Zip cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }


    public Zip start(OnCallBack.P0 onStart) {
        this.onStart = onStart;
        return this;
    }

    public void zip() {
        if (!src.exists()) {
            onError(new RuntimeException("没有找到需要压缩打包的文件"));
            return;
        }

        if (desc == null) {
            desc = new File(src.getAbsolutePath() + FileType.d_ZIP);
        }

        if (onProgress != null) {
            onProgress.onCallBack(new Progress(totalLen, 0));
        }

        if (cacheSize == null) {
            cacheSize = 1024 * 512;
        }

        if (onStart != null) {
            onStart.onCallBack();
        }

        String zipPath = desc.getAbsolutePath();
        totalLen = FileTool.obtainTotalLength(src, pass);
        lastTime = System.currentTimeMillis();

        File parentFile = desc.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        zip(src, zipPath);
        onProgress(totalLen);
        onFinish(desc);
    }

    private void zip(File needPressFile, String zipFilePath) {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFilePath));
            rootDir = StringTool.cutSuffix(desc.getName(), FileType.d_ZIP);
            recursivePressFile(out, needPressFile, "");
        } catch (Exception e) {
            onError.onCallBack(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    LibLogs.e(e);
                }
            }
        }
    }

    private void recursivePressFile(ZipOutputStream out, File needPressFile, String parentDirName) throws ZipException {
        if (needPressFile.isDirectory()) {
            if (pass != null && pass.isPass(needPressFile)) {
                return;
            }
            File[] files = needPressFile.listFiles();
            for (File file : files) {
                String newParentDirName = StringTool.isBlank(parentDirName) ? rootDir : (parentDirName + FileTool.SEPARATOR + needPressFile.getName());
                recursivePressFile(out, file, newParentDirName);
            }
        } else {
            if (pass != null && pass.isPass(needPressFile)) {
                return;
            }
            pressFile(out, needPressFile, parentDirName);

        }

    }

    private void pressFile(ZipOutputStream out, File file, String parentDirName) throws ZipException {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            String path = parentDirName + FileTool.SEPARATOR + file.getName();
            if (zipedPathDeal != null) {
                path = zipedPathDeal.deal(path);
            }
            out.putNextEntry(new ZipEntry(path));

            byte[] buff = new byte[cacheSize];
            int len = 0;

            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
                zipedLen += len;

                long nowTime = System.currentTimeMillis();
                if (nowTime - lastTime > 100) {
                    if (zipedLen < totalLen) {
                        onProgress(zipedLen);
                    }
                    lastTime = nowTime;
                }

            }
        } catch (Exception e) {
            throw new ZipException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    LibLogs.e(e);
                }
            }
            input = null;
        }

    }

    private void onProgress(long currentLen) {
        if (this.onProgress != null) {
            this.onProgress.onCallBack(new Progress(totalLen, currentLen));
        }
    }

    private void onFinish(File file) {
        if (this.onFinish != null) {
            this.onFinish.onCallBack(file);
        }
        onDestroy();
    }

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onCallBack(throwable);
        }
        onDestroy();
    }

}
