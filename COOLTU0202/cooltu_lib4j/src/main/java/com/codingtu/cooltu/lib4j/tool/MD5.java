package com.codingtu.cooltu.lib4j.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5 {

    private static final char hexDigits[] = "0123456789ABCDEF".toCharArray();

    public static String md5(String content) {
        return md5(content, 1);
    }

    public static String md5(String content, int times) {
        return toHexString(md5Bytes(content, times));
    }

    public static byte[] md5Bytes(String content, int times) {
        try {
            MessageDigest md5 = getMd5();
            byte[] data = content.getBytes("utf-8");
            for (int i = 0; i < times; i++) {
                data = md5.digest(data);
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = getMd5();
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return toHexString(MD5.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String toHexString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexDigits[(b >> 4) & 0xF]);
            r.append(hexDigits[(b & 0xF)]);
        }
        return r.toString();
    }


    private static MessageDigest getMd5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return null;
        }
    }

}
