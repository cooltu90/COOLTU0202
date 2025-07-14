package com.codingtu.cooltu.lib4a.cryption;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAKey;

import javax.crypto.Cipher;

public abstract class BaseRSA<THIS extends BaseRSA> {

    private KeyPair keyPair;
    private String signType = RsaStatic.SIGN_TYPE_SHA256_WITH_RSA;

    public THIS signType(String signType) {
        this.signType = signType;
        return (THIS) this;
    }

    /**************************************************
     * 获取原始的KeyPair
     **************************************************/
    public THIS createKeyPair() {
        keyPair = RsaTool.createKeyPair();
        return (THIS) this;
    }

    public KeyPair keyPair() {
        return keyPair;
    }


    /**************************************************
     * 储存私钥
     **************************************************/

    public THIS cachePrivateKey() {
        if (keyPair != null) {
            return cachePrivateKey(
                    Base64Tool.encodeToBase64Str(keyPair.getPrivate().getEncoded())
            );
        }
        throw new RuntimeException("还未创建公钥和私钥");
    }

    public THIS cachePrivateKey(String privateKey) {
        cachePrivateKeyStr(privateKey);
        return (THIS) this;
    }

    protected abstract void cachePrivateKeyStr(String privateKey);

    /**************************************************
     * 储存公钥
     **************************************************/
    public THIS cachePublicKey() {
        if (keyPair != null) {
            return cachePublicKey(
                    Base64Tool.encodeToBase64Str(keyPair.getPublic().getEncoded())
            );
        }
        throw new RuntimeException("还未创建公钥和私钥");
    }

    public THIS cachePublicKey(String publicKeyStr) {
        cachePublicKeyReal(publicKeyStr);
        return (THIS) this;
    }

    protected void cachePublicKeyReal(String publicKeyStr) {
        throw new RuntimeException("还未实现储存公钥的方法");
    }

    /**************************************************
     * 获取公钥
     **************************************************/
    public String publicKeyStr() {
        if (keyPair != null) {
            return Base64Tool.encodeToBase64Str(keyPair.getPublic().getEncoded());
        }
        return publicKeyStrFormLocal();
    }

    protected String publicKeyStrFormLocal() {
        throw new RuntimeException("还未实现从本地获取公钥的方法");
    }

    /**************************************************
     *
     **************************************************/
    public PrivateKey privateKey() {
        try {
            if (keyPair == null) {
                String privateKeyStr = obtainPrivateKeyStrFormLocal();
                PrivateKey privateKey = RsaTool.privateKey(Base64Tool.decodeToBase64(privateKeyStr.getBytes()));
                keyPair = new KeyPair(null, privateKey);
            }
            return keyPair.getPrivate();
        } catch (Exception e) {
            Logs.e(e);
        }
        return null;
    }

    protected abstract String obtainPrivateKeyStrFormLocal();


    /**************************************************
     *
     **************************************************/
    public String sign(Object... objs) {
        try {
            if (objs.length == 0) {
                return null;
            }
            String data = null;
            if (objs.length == 1) {
                data = StringTool.toString(objs[0]);
            } else {
                StringBuilder params = new StringBuilder();
                Es.es(objs).ls(new Es.EachEs<Object>() {
                    @Override
                    public boolean each(int position, Object o) {
                        if (position != 0) {
                            params.append(",");
                        }
                        params.append(o);
                        return false;
                    }
                });
                data = params.toString();
            }
            return Base64Tool.encodeToBase64Str(RsaTool.sign(signType, privateKey(), data.getBytes()));
        } catch (Exception e) {
            Logs.e(e);
        }
        return null;

    }

    /**************************************************
     *
     **************************************************/
    public String decode(String content) {
        PrivateKey privateKey = privateKey();

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(RsaStatic.ALGORITHM_RSA_ECB_PKCS1);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (Exception e) {
            return null;
        }

        byte[] datas = Base64Tool.decodeToBase64(content.getBytes());

        int maxBlockSize = ((RSAKey) privateKey).getModulus().bitLength() / 8;
        if (maxBlockSize < 0) {
            // 在引入BC库情况下，自动获取块大小
            final int blockSize = cipher.getBlockSize();
            if (blockSize > 0) {
                maxBlockSize = blockSize;
            }
        }
        maxBlockSize = maxBlockSize < 0 ? datas.length : maxBlockSize;

        // 模长
        final int dataLength = datas.length;

        // 不足分段
        byte[] results = null;
        if (dataLength <= maxBlockSize) {
            try {
                results = cipher.doFinal(datas, 0, dataLength);
            } catch (Exception e) {

            }
        } else {
            try {
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                int offSet = 0;
                // 剩余长度
                int remainLength = dataLength;
                int blockSize;
                // 对数据分段处理
                while (remainLength > 0) {
                    blockSize = Math.min(remainLength, maxBlockSize);
                    out.write(cipher.doFinal(datas, offSet, blockSize));

                    offSet += blockSize;
                    remainLength = dataLength - offSet;
                }
                results = out.toByteArray();
                out.close();
            } catch (Exception e) {

            }
        }

        if (results == null)
            return null;
        return new String(results);

    }

}
