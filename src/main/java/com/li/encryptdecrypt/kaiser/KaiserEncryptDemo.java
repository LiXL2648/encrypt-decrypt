package com.li.encryptdecrypt.kaiser;

public class KaiserEncryptDemo {

    public static void main(String[] args) {

        // 定义原文
        String str = "Hello World";
        // 把原文右移3位
        int key = 3;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append((char) (c + 3));
        }
        System.out.println(sb.toString());
    }
}
