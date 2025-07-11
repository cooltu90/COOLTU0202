package com.codingtu.cooltu.lib4j.file.delete;

import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.file.FileTool;

import java.io.File;

public class FileDeleter {

    private File deleteFile;
    private OnCallBack.P1<Progress> onProgress;
    private OnCallBack.P0 onFinish;
    private OnCallBack.P0 onStart;
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

    public FileDeleter progress(OnCallBack.P1<Progress> onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public FileDeleter finish(OnCallBack.P0 onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public FileDeleter start(OnCallBack.P0 onStart) {
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
            this.onProgress.onCallBack(new Progress(totalLen, currentLen));
        }
    }

}
