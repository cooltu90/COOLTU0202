package com.codingtu.cooltu.lib4j.file.copy;

import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.exception.FileCopyException;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
    private File srcFile;
    private File targetFile;


    private boolean force;
    private String targetPath;
    private String srcPath;
    private OnCallBack.P1<Progress> onProgress;
    private OnCallBack.P0 onFinish;
    private OnCallBack.P0 onStart;
    private long totalLen;
    private long currentLen;
    private long lastTime;

    public static FileCopy src(String srcPath) {
        return src(new File(srcPath));
    }

    public static FileCopy src(File srcFile) {
        FileCopy fileCopy = new FileCopy();
        fileCopy.srcFile = srcFile;
        return fileCopy;
    }

    public FileCopy force() {
        this.force = true;
        return this;
    }

    public FileCopy progress(OnCallBack.P1<Progress> onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public FileCopy finish(OnCallBack.P0 onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public FileCopy start(OnCallBack.P0 onStart) {
        this.onStart = onStart;
        return this;
    }

    public void to(String targetPath) throws FileCopyException {
        to(new File(targetPath));
    }

    public void to(File targetFile) throws FileCopyException {
        this.targetFile = targetFile;
        toTarget();
    }

    public void toDir(String dirPath) throws FileCopyException {
        toDir(new File(dirPath));
    }

    public void toDir(File dirFile) throws FileCopyException {
        this.targetFile = new File(dirFile, srcFile.getName());
        toTarget();
    }

    private void toTarget() throws FileCopyException {
        if (!force && targetFile.exists()) {
            return;
        }
        targetPath = targetFile.getAbsolutePath();
        srcPath = srcFile.getAbsolutePath();

        if (onStart != null) {
            onStart.onCallBack();
        }

        lastTime = System.currentTimeMillis();
        totalLen = FileTool.obtainTotalLength(srcFile, null);
        toTarget(srcFile);
        onProgress(totalLen);
        if (onFinish != null) {
            onFinish.onCallBack();
        }
    }

    private void toTarget(File file) throws FileCopyException {
        String rename = FileTool.getRename(file, srcPath, targetPath);
        File newFile = new File(rename);
        if (file.isDirectory()) {
            newFile.mkdirs();
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    File file1 = files[i];
                    toTarget(file1);

                }
            }
        } else {
            copy(file, newFile);
        }
    }

    private void copy(File oldFile, File newFile) throws FileCopyException {
        InputStream input = null;
        OutputStream output = null;
        try {
            FileTool.createParentDir(newFile);
            input = new FileInputStream(oldFile);
            output = new FileOutputStream(newFile);
            copy(input, output);
        } catch (Exception e) {
            throw new FileCopyException("文件拷贝错误 旧文件：" + oldFile.getAbsolutePath() + " 新文件：" + newFile.getAbsolutePath());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void copy(InputStream input, OutputStream output) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = input.read(bytes)) > 0) {
            output.write(bytes, 0, len);
            currentLen += len;

            long nowTime = System.currentTimeMillis();
            if (nowTime - lastTime > 100) {
                if (currentLen < totalLen) {
                    onProgress(currentLen);
                }
                lastTime = nowTime;
            }

        }
    }

    private void onProgress(long currentLen) {
        if (this.onProgress != null) {
            this.onProgress.onCallBack(new Progress(totalLen, currentLen));
        }
    }

}
