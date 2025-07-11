package com.codingtu.cooltu.cryption.tools;

import java.io.File;

public interface CryptionListener {

    void start(File file);

    void finish(File file);

    void progress(File file, long totalLen, long currentLen);

    void error(File file, Throwable throwable);
}
