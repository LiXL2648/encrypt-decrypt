package com.li.encryptdecrypt.ascii;

public class AsciiDemo {

    public static void main(String[] args) {
        // 在 ASCII 码中 A 对应的十进制数字
        System.out.println((int) 'A');

        String hello = "hello";
        for (char c : hello.toCharArray()) {
            System.out.println((int) c);
        }
    }
}
