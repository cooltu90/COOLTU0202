package com.codingtu.cooltu.lib4a.cryption;

import com.codingtu.cooltu.lib4a.log.Logs;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class RsaTool {

    /**************************************************
     * 获取原始的KeyPair
     **************************************************/
    public static KeyPair createKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RsaStatic.ALGORITHM_RSA);
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (Exception e) {

        }
        return null;
    }

    /**************************************************
     * 通过byte数组获取私钥
     **************************************************/
    public static PrivateKey privateKey(byte[] privateKeyBytes) {
        try {
            return KeyFactory.getInstance(RsaStatic.ALGORITHM_RSA)
                    .generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        } catch (Exception e) {

        }
        return null;
    }

    /**************************************************
     *
     **************************************************/
    public static byte[] sign(String signType, PrivateKey privateKey, byte[] datas) {
        try {
            Signature signature = Signature.getInstance(signType);
            signature.initSign(privateKey);
            signature.update(datas);
            return signature.sign();
        } catch (Exception e) {
            Logs.e(e);
        }
        return null;
    }

}
