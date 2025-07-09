package com.codingtu.cooltu.lib4j.callback;

import com.codingtu.cooltu.lib4j.data.progress.Progress;

public interface OnProgress {
    
    default void onProgress(long totalLen, long currentLen) {
        onProgress(new Progress(totalLen, currentLen));
    }

    void onProgress(Progress progress);
}