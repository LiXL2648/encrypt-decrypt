package com.li.encryptdecrypt.digest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;

public class DigestDemo {

    public static void main(String[] args) throws Exception {
        // 明文
        String text = "李丽璇";
        // 采用的算法，常见的有 MD5、SHA-1、SHA-256、SHA-512
        String algorithm = "MD5";
        System.out.println(getDigest(text, algorithm));

        System.out.println(getDigest(text, "SHA-1"));
        System.out.println(getDigest(text, "SHA-256"));
        System.out.println(getDigest(text, "SHA-512"));
    }

    /**
     * 获取数字摘要
     * @param text 明文
     * @param algorithm 算法
     * @return
     * @throws Exception
     */
    private static String getDigest(String text, String algorithm) throws Exception {
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] bytes = digest.digest(text.getBytes());
        return toHex(bytes);
    }

    /**
     * 将字节数组转成16进制
     * @param bytes 数字摘要后得到的字节数组
     * @return
     */
    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte & 0xff);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 将字节数组进行Base64编码
     * @param bytes 数字摘要后得到的字节数组
     * @return
     */
    private static String toBase64(byte[] bytes) {
        return Base64.encode(bytes);
    }
}
