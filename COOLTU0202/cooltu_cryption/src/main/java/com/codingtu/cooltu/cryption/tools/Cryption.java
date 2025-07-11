package com.codingtu.cooltu.cryption.tools;

import com.codingtu.cooltu.cryption.decode.DecodeFile;
import com.codingtu.cooltu.cryption.encode.EncodeFile;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;

import java.io.File;

public class Cryption {

    private boolean isEncode;
    private File file;
    private byte[] pswBytes;
    private CryptionListener listener;
    private boolean isRename;


    public static Cryption encode() {
        Cryption cryption = new Cryption();
        cryption.isEncode = true;
        return cryption;
    }

    public static Cryption decode() {
        return new Cryption();
    }

    public Cryption file(File file) {
        this.file = file;
        return this;
    }

    public Cryption file(String path) {
        this.file = new File(path);
        return this;
    }

    public Cryption password(String password) {
        this.pswBytes = password.getBytes();
        return this;
    }

    public Cryption listener(CryptionListener listener) {
        this.listener = listener;
        return this;
    }

    public Cryption rename() {
        this.isRename = true;
        return this;
    }

    public void start() {
        if (file.isDirectory()) {
            FileLister.dir(file)
                    .list(new ListFile() {
                        @Override
                        public void list(File file) {
                            start(file);
                        }
                    });
        } else {
            start(file);
        }
    }

    private void start(File file) {
        (isEncode ? new EncodeFile(isRename, file, pswBytes, listener) : new DecodeFile(file, pswBytes, listener)).start();
    }

}
