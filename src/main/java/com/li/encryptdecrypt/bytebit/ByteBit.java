package com.li.encryptdecrypt.bytebit;

import org.junit.Test;

public class ByteBit {

    public static void main(String[] args) {
        String a = "a";
        for (byte aByte : a.getBytes()) {
            System.out.println((int) aByte);
            // byte字节对应的bit
            String binaryString = Integer.toBinaryString(aByte);
            System.out.println(binaryString);
        }
    }


    /**
     *
     */
    @Test
    public void testByteBitChinese() {

        String text = "璇";
        for (byte aByte : text.getBytes()) {
            System.out.println((int) aByte);
            System.out.println(Integer.toBinaryString(aByte));
        }
    }
}

