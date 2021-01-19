package com.li.encryptdecrypt.digest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;

public class DigestBase64Demo {

    public static void main(String[] args) throws Exception {
        // 明文
        String text = "李丽璇";
        // 采用的算法
        String algorithm = "MD5";
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] bytes = digest.digest(text.getBytes());
        // 使用 Base64 进行转码
        String encode = Base64.encode(bytes);
        System.out.println(encode);
    }
}
