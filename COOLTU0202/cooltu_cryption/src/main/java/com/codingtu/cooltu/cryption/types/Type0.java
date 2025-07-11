package com.codingtu.cooltu.cryption.types;

public interface Type0 {

    default byte[] encode(byte[] bytes, byte[] pswBytes) {
        return encode(bytes, pswBytes, bytes.length);
    }

    byte[] encode(byte[] bytes, byte[] pswBytes, int len);

    default byte[] defaultEncode(byte[] bytes, byte[] pswBytes, int len) {
        for (int i = 0; i < len; i++) {
            bytes[i] ^= pswBytes[i % pswBytes.length];
        }
        return bytes;
    }
}
