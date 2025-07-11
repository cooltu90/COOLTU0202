package com.codingtu.cooltu.cryption.tools;

import com.codingtu.cooltu.cryption.types.Type0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class CryptionFile {

    protected File file;
    protected byte[] pswBytes;

    protected Type0 type;

    protected FileInputStream ipt;
    protected FileOutputStream opt;

    protected CryptionListener listener;

    public CryptionFile(File file, byte[] pswBytes, CryptionListener listener) {
        this.file = file;
        this.pswBytes = pswBytes;
        type = CryptionTypes.getDefaultType();
        this.listener = listener;
    }

    public void start() {
        try {
            startWithException();
        } catch (IOException e) {
            error(e);
        } finally {
            if (ipt != null) {
                try {
                    ipt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ipt = null;
            }

            if (opt != null) {
                try {
                    opt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                opt = null;
            }
            dealFile();
            finish();
        }
    }

    protected void dealFile() {

    }

    protected void finish() {
        if (listener != null) {
            listener.finish(this.file);
        }
    }

    protected abstract void startWithException() throws IOException;


    protected void error(String msg) {
        if (listener != null) {
            listener.error(file, new RuntimeException(msg));
        }
    }

    protected void error(Throwable throwable) {
        if (listener != null) {
            listener.error(file, throwable);
        }
    }


    protected void progress(long totalLen,long currentLen) {
        if (listener != null) {
            listener.progress(file, totalLen, currentLen);
        }
    }

    protected byte[] encode(byte[] bytes) {
        return encode(bytes, bytes.length);
    }

    protected byte[] encode(byte[] bytes, int len) {
        return this.type.encode(bytes, pswBytes, len);
    }

    protected String read(byte[] bytes, int len) throws IOException {
        ipt.read(bytes, 0, len);
        encode(bytes, len);
        return new String(bytes, 0, len, CryptionTools.CHARSET);
    }

}
