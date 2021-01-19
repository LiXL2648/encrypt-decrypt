package com.li.encryptdecrypt.digest;

import java.security.MessageDigest;

public class Digest0xffDemo {

    public static void main(String[] args) throws Exception {
        // 明文
        String text = "李丽璇";
        // 采用的算法
        String algorithm = "MD5";
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] bytes = digest.digest(text.getBytes());
        // 将字节数组转成16进制
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte & 0xff);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
