package com.codingtu.cooltu.lib4j.file.delete;

import com.codingtu.cooltu.lib4j.callback.OnProgress;
import com.codingtu.cooltu.lib4j.callback.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.file.FileTool;

import java.io.File;

public class FileDeleter {

    private File deleteFile;
    private OnProgress onProgress;
    private OnCallBack onFinish;
    private OnCallBack onStart;
    private long totalLen;
    private long currentLen;

    public static FileDeleter file(String path) {
        return file(new File(path));
    }

    public static FileDeleter file(File file) {
        FileDeleter fileDeleter = new FileDeleter();
        fileDeleter.deleteFile = file;
        return fileDeleter;
    }

    public FileDeleter progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public FileDeleter finish(OnCallBack onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public FileDeleter start(OnCallBack onStart) {
        this.onStart = onStart;
        return this;
    }

    public void delete() {
        totalLen = FileTool.obtainTotalLength(deleteFile, null);

        if (onStart != null) {
            onStart.onCallBack();
        }
        deleteReal(deleteFile);

        if (onFinish != null) {
            onFinish.onCallBack();
        }
    }

    private void deleteReal(File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int size = files == null ? 0 : files.length;
            for (int i = 0; i < size; i++) {
                deleteReal(files[i]);
            }
        } else {
            currentLen += file.length();
            onProgress(currentLen);
        }
        file.delete();
    }

    private void onProgress(long currentLen) {
        if (this.onProgress != null) {
            this.onProgress.onProgress(totalLen, currentLen);
        }
    }

}
