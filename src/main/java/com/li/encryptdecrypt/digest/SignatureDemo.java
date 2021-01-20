package com.li.encryptdecrypt.digest;

import com.li.encryptdecrypt.rsa.RsaDemo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.*;

public class SignatureDemo {

    public static void main(String[] args) throws Exception {
        // 获取私钥
        PrivateKey priKey = RsaDemo.getPriKey("pri.key", "RSA");
        // 获取公钥
        PublicKey pubKey = RsaDemo.getPubKey("pub.key", "RSA");

        String text = "柯亚丹";
        //  算法
        String algorithm = "sha256withrsa";
        String signatureData = getSignature(text, algorithm, priKey);
        System.out.println(signatureData);
        boolean b = verifySignature(text, signatureData, algorithm, pubKey);
        System.out.println(b);
    }

    /**
     * 校验数字签名
     * @param text 明文
     * @param signatureData 数字签名密文
     * @param algorithm 算法
     * @param pubKey 公钥
     * @return
     */
    public static boolean verifySignature(String text, String signatureData, String algorithm, PublicKey pubKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名对象
        signature.initVerify(pubKey);
        // 传入原文
        signature.update(text.getBytes());
        // 使用Base64对签名密文进行解码
        byte[] bytes = Base64.decode(signatureData);
        // 对数字签名进行校验
        return signature.verify(bytes);
    }

    /**
     * 获取数字签名
     * @param text 原文
     * @param algorithm 算法
     * @param priKey 私钥
     * @return
     */
    public static String getSignature(String text, String algorithm, PrivateKey priKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名对象
        signature.initSign(priKey);
        // 传入原文
        signature.update(text.getBytes());
        // 开始签名
        byte[] bytes = signature.sign();
        // 使用Base64进行编码
        return Base64.encode(bytes);
    }
}
