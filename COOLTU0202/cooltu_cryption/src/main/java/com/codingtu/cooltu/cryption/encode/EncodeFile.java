package com.codingtu.cooltu.cryption.encode;

import com.codingtu.cooltu.cryption.tools.CryptionFile;
import com.codingtu.cooltu.cryption.tools.CryptionListener;
import com.codingtu.cooltu.cryption.tools.CryptionTools;
import com.codingtu.cooltu.cryption.tools.CryptionTypes;
import com.codingtu.cooltu.lib4j.tool.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeFile extends CryptionFile {

    private final boolean isRename;

    public EncodeFile(boolean isRename, File file, byte[] pswBytes, CryptionListener listener) {
        super(file, pswBytes, listener);
        this.isRename = isRename;
    }

    @Override
    protected void startWithException() throws IOException {

        ipt = new FileInputStream(file);
        byte[] bytes = new byte[CryptionTools.MAX_READ_LEN];
        //第一次读取
        int len = ipt.read(bytes);

        byte[] signBytes = encode(CryptionTools.signBytes());

        if (CryptionTools.isEncode(signBytes, bytes)) {
            throw new RuntimeException("此文件是已加密文件");
        }

        if (listener != null) {
            listener.start(file);
        }

        //未加密
        File tempFile = new File(this.file.getAbsolutePath() + ".tmp");
        opt = new FileOutputStream(tempFile);
        //写入标记
        opt.write(signBytes);
        //写入类型
        opt.write(CryptionTypes.lastType());

        type = CryptionTypes.getLastType();

        //写入最后更新时间
        opt.write(encode(CryptionTools.lastModifyBytes(this.file)));

        byte[] nameBytes = encode(file.getName().getBytes(CryptionTools.CHARSET));

        //写入名字长度
        opt.write(nameBytes.length);
        //写入名字
        opt.write(nameBytes);

        long readLen = CryptionTools.getInfosLen() + nameBytes.length;

        long totalLen = this.file.length() + readLen;

        //写入文件实体
        do {
            if (len > 0) {
                opt.write(encode(bytes, len), 0, len);
                readLen += len;
            }
            progress(totalLen, readLen);
        }
        while ((len = ipt.read(bytes)) > 0);
    }

    @Override
    protected void dealFile() {
        File tempFile = new File(this.file.getAbsolutePath() + ".tmp");

        String newPath = file.getAbsolutePath();
        if (isRename) {
            newPath = getFile(this.file).getAbsolutePath();
        }
        this.file.delete();
        File newFile = new File(newPath);
        tempFile.renameTo(newFile);
        this.file = newFile;
    }

    private static File getFile(File file) {
        String name = file.getName();
        int times = 2;
        while (true) {
            if (times > 10) {
                return file;
            }
            File newFile = new File(file.getParentFile().getAbsolutePath(), MD5.md5(name, times++));
            if (!newFile.exists()) {
                return newFile;
            }
        }
    }

}
