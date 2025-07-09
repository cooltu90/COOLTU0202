package com.codingtu.cooltu.lib4j.data.progress;

import com.codingtu.cooltu.lib4j.data.data.LibDataObj;

public class Progress extends LibDataObj {
    public long totalLen;
    public long currentLen;

    public Progress() {
    }

    public Progress(long totalLen, long currentLen) {
        this.totalLen = totalLen;
        this.currentLen = currentLen;
    }
}
