package com.codingtu.cooltu.lib4a.cryption;

import android.util.Base64;

public class Base64Tool {

    public static byte[] encodeToBase64(byte[] bytes) {
        return Base64.encode(bytes, Base64.DEFAULT);
    }

    public static String encodeToBase64Str(byte[] bytes) {
        return new String(encodeToBase64(bytes));
    }

    public static byte[] decodeToBase64(byte[] bytes) {
        return Base64.decode(bytes, Base64.DEFAULT);
    }

    public static String decodeToBase64Str(byte[] bytes) {
        return new String(decodeToBase64(bytes));
    }
}
