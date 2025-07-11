package com.codingtu.cooltu.cryption.types;

/**************************************
 *
 * 默认类型
 *
 **************************************/
public class Type1 implements Type0 {

    @Override
    public byte[] encode(byte[] bytes, byte[] pswBytes, int len) {
        return defaultEncode(bytes, pswBytes, len);
    }
}
