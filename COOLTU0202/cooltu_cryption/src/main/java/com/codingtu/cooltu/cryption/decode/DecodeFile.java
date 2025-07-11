package com.codingtu.cooltu.cryption.decode;


import com.codingtu.cooltu.cryption.tools.CryptionFile;
import com.codingtu.cooltu.cryption.tools.CryptionListener;
import com.codingtu.cooltu.cryption.tools.CryptionTools;
import com.codingtu.cooltu.cryption.tools.CryptionTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DecodeFile extends CryptionFile {

    private String newName;

    public DecodeFile(File file, byte[] pswBytes, CryptionListener listener) {
        super(file, pswBytes, listener);
    }

    @Override
    protected void startWithException() throws IOException {
        ipt = new FileInputStream(file);
        byte[] bytes = new byte[CryptionTools.MAX_READ_LEN];
        //获取标志
        ipt.read(bytes, 0, CryptionTools.signLen());
        byte[] signBytes = encode(CryptionTools.signBytes());
        if (!CryptionTools.isEncode(signBytes, bytes)) {
            throw new RuntimeException("此文件为未加密文件");
        }

        if (listener != null) {
            listener.start(file);
        }

        //获取类型
        type = CryptionTypes.getType(ipt.read());
        //获取最后修改时间
        long lastModify = Long.parseLong(read(bytes, CryptionTools.lastModifyLen()));
        //获取名字长度
        int nameLen = ipt.read();
        //获取名字
        newName = read(bytes, nameLen);

        //获取文件实体
        File tempFile = new File(this.file.getAbsolutePath() + ".tmp");

        opt = new FileOutputStream(tempFile);

        long readLen = CryptionTools.getInfosLen() + nameLen;
        long totalLen = this.file.length();

        int len = 0;
        while ((len = ipt.read(bytes)) > 0) {
            opt.write(encode(bytes, len), 0, len);
            readLen += len;
            progress(totalLen, readLen);
        }
        tempFile.setLastModified(lastModify);
    }

    @Override
    protected void dealFile() {
        File tempFile = new File(this.file.getAbsolutePath() + ".tmp");

        File newFile = new File(this.file.getParentFile(), newName);

        this.file.delete();

        tempFile.renameTo(newFile);

    }

}
