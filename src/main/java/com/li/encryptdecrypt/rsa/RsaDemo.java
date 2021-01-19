package com.li.encryptdecrypt.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import java.security.*;

public class RsaDemo {

    public static void main(String[] args) throws Exception {
        // 加密明文
        String text = "余彩欣";

        //  算法
        String algorithm = "RSA";
        // 返回一个 KeyPairGenerator 对象，该对象为指定的算法生成公钥/私钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 使用Base64对公钥/私钥的Base64进行编码
        System.out.println(Base64.encode(publicKeyEncoded));
        System.out.println(Base64.encode(privateKeyEncoded));

        // 创建Cipher对象进行加密，使用 RSA 算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对Cipher对象进行初始化，第一个参数：加密模式，第二个参数：使用公钥或者私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 使用私钥进行加密
        byte[] bytes = cipher.doFinal(text.getBytes());
        // 使用 Base64 进行编码
        System.out.println(Base64.encode(bytes));
    }
}
