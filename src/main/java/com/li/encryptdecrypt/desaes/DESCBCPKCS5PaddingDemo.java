package com.li.encryptdecrypt.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESCBCPKCS5PaddingDemo {

    public static void main(String[] args) throws Exception {

        // 原文
        String text = "李晓亮";
        // 密钥，密钥必须为8字节
        String secret = "12345678";
        // 采用的算法
        // String transformation = "DES";
        // 采用的算法/加密模式/填充模式：DES：加密算法；ECB：加密模式；PKCS5Padding：填充模式。
        // String transformation = "DES/ECB/PKCS5Padding";
        String transformation = "DES/CBC/PKCS5Padding";
        // 加密类型
        String algorithm = "DES";

        // 使用 DES 进行加密
        String encryptText = encryptDes(text, secret, transformation, algorithm);
        System.out.println(encryptText); // 7lFMXVfmDbQ2BxG+Uonupg== / 7lFMXVfmDbQ2BxG+Uonupg==

        // 使用 DES 进行解密
        String decryptText = decryptDes(encryptText, secret, transformation, algorithm);
        System.out.println(decryptText);
    }

    /**
     * 使用 DES 进行加密
     * @param text 原文
     * @param secret 密钥（DES，密钥的长度必须是8个字节）
     * @param transformation 获取Cipher对象采用的算法/加密模式/填充模式
     * @param algorithm 获取密钥的算法
     * @return 加密后的密文
     * @throws Exception
     */
    private static String encryptDes(String text, String secret, String transformation, String algorithm) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则，第一个参数：表示 secret 的字节，第二个参数：表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), algorithm);
        // 创建 iv 向量，当加密模式为 CBC 时，需要使用该向量
        IvParameterSpec iv = new IvParameterSpec(secret.getBytes());
        // 进行加密初始化，第一个参数：加密模式，第二个参数：加密规则，第三个参数：iv向量，长度必须为 8bite
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        // 调用加密方法，参数：原文字节数组
        byte[] bytes = cipher.doFinal(text.getBytes());
        // 创建 base64，对得到的字节数组进行Base64编码
        String encode = Base64.encode(bytes);
        // 打印密文，如果直接打印密文会因为在编码表上找不到对应字符，因此出现乱码
        // System.out.println(new String(encryotText));
        return encode;
    }

    /**
     * 使用 DES 进行解密
     * @param text 密文
     * @param secret 密钥（DES，密钥的长度必须是8个字节）
     * @param transformation 获取Cipher对象采用的算法/加密模式/填充模式
     * @param algorithm 获取密钥的算法
     * @return 解密后的明文
     * @throws Exception
     */
    public static String decryptDes(String text, String secret, String transformation, String algorithm) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则，第一个参数：表示 secret 的字节，第二个参数：表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), algorithm);
        // 创建 iv 向量，当加密模式为 CBC 时，需要使用该向量
        IvParameterSpec iv = new IvParameterSpec(secret.getBytes());
        // 进行加密初始化，第一个参数：加密模式，第二个参数：加密规则，第三个参数：iv向量，长度必须为 8bite
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        // 创建base64，对密文进行Base64解码，得到解密后的字节数组
        byte[] decode = Base64.decode(text);
        // 调用解密方法，参数：密文的字节数组
        byte[] bytes = cipher.doFinal(decode);

        return new String(bytes);
    }
}
