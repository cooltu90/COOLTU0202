package com.codingtu.cooltu.cryption.tools;

import java.io.File;

public class CryptionTools {

    /**************************************
     *
     * 文件加密格式
     * [p90n9encryption][type][lastModify][filenameLen][filename][file]
     *
     **************************************/

    public static final String SIGN = "[p90n9encryption]";
    public static final int MAX_READ_LEN = 1024 * 512;
    public static final String CHARSET = "utf-8";

    public static int getInfosLen() {
        //signLen typeLen lastModifyLen filenameLen
        return signLen() + typeLen() + lastModifyLen() + 1;
    }

    /**************************************
     *
     * sign
     *
     **************************************/
    public static int signLen() {
        return SIGN.length();
    }

    public static byte[] signBytes() {
        return SIGN.getBytes();
    }

    /**************************************
     *
     * type
     *
     **************************************/
    public static int typeLen() {
        return 1;
    }

    /**************************************
     *
     * lastModify
     *
     **************************************/
    public static int lastModifyLen() {
        return 13;
    }


    public static byte[] lastModifyBytes(File file) {
        return (file.lastModified() + "").getBytes();
    }

    /**************************************
     *
     * method
     *
     **************************************/
    public static boolean isEncode(byte[] signBytes, byte[] bytes) {
        for (int i = 0; i < signBytes.length; i++) {
            if (bytes[i] != signBytes[i]) {
                return false;
            }
        }
        return true;
    }

}
