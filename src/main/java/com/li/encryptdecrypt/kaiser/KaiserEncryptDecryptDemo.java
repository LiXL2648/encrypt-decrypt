package com.li.encryptdecrypt.kaiser;

public class KaiserEncryptDecryptDemo {

    public static void main(String[] args) {

        // 定义原文
        String str = "Hello World";
        // 把原文右移3位
        int key = 3;
        String encrypt = kaiserEncrypt(str, key);
        System.out.println(encrypt);
        String decrypt = kaiserEncrypt(encrypt, -key);
        System.out.println(decrypt);

    }

    private static String kaiserEncrypt(String str, int key) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append((char) (c + key));
        }
        return sb.toString();
    }
}
