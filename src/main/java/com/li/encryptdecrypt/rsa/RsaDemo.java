package com.li.encryptdecrypt.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaDemo {

    public static void main(String[] args) throws Exception {
        // 加密明文
        String text = "余彩欣";

        //  算法
        String algorithm = "RSA";


        // generateKeyToFile(algorithm, "pub.key", "pri.key");
        Key priKey = getPriKey("pri.key", algorithm);
        Key pubKey = getPubKey("pub.key", algorithm);





        // 私钥解密，报Decryption error
        // 对Cipher对象进行初始化，第一个参数：解密模式，第二个参数：使用私钥解密
        // cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 使用私钥进行解密
        // byte[] decryptByte = cipher.doFinal(encryptBytes);


        String encrypt = priKeyEncrypt(text, algorithm, priKey);
        System.out.println(encrypt);
        String decrypt = pubKeyDecrypt(encrypt, algorithm, pubKey);
        System.out.println(decrypt);
    }

    /**
     * 从公钥文件中获取公钥对象
     * @param path 公钥文件路径
     * @param algorithm RSA算法
     * @return
     * @throws Exception
     */
    public static PublicKey getPubKey (String path, String algorithm) throws Exception {
        // 从公钥文件中读取公钥字符串
        String publicKeyStr = FileUtils.readFileToString(new File(path), Charset.forName("UTF-8"));
        // 创建key功查
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 使用Base64对公钥字符串进行解码
        byte[] decode = Base64.decode(publicKeyStr);
        // 创建公钥key的规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
        // 返回公钥对象
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从私钥文件中获取私钥对象
     * @param path 私钥文件路径
     * @param algorithm RSA算法
     * @return
     * @throws Exception
     */
    public static PrivateKey getPriKey(String path, String algorithm) throws Exception {
        // 从私钥文件中读取私钥字符串
        String privateKeyStr = FileUtils.readFileToString(new File(path), Charset.forName("UTF-8"));
        // 创建key功查
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 使用Base64对私钥字符串进行解码
        byte[] decode = Base64.decode(privateKeyStr);
        // 创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
        // 返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用私钥进行加密
     * @param text 明文
     * @param algorithm RSA算法
     * @param key 私钥
     * @return
     * @throws Exception
     */
    public static String priKeyEncrypt(String text, String algorithm, Key key) throws Exception {
        // 私钥加密
        // 创建Cipher对象进行加密，使用 RSA 算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对Cipher对象进行初始化，第一个参数：加密模式，第二个参数：使用私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 使用私钥进行加密
        byte[] encrypt = cipher.doFinal(text.getBytes());
        // 使用 Base64 进行编码
        return Base64.encode(encrypt);
    }

    /**
     * 使用公钥进行解密
     * @param text 密文
     * @param algorithm RSA算法
     * @param key 公钥
     * @return
     * @throws Exception
     */
    public static String pubKeyDecrypt(String text, String algorithm, Key key) throws Exception {
        // 公钥解密
        // 创建Cipher对象进行解密密，使用 RSA 算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对Cipher对象进行初始化，第一个参数：解密模式，第二个参数：使用公钥解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 使用Base64进行解码
        byte[] decode = Base64.decode(text);
        // 使用公钥进行解密
        byte[] decryptByte = cipher.doFinal(decode);
        return new String(decryptByte);
    }

    /**
     * 把公钥和私钥保存到文件中
     * @param algorithm RSA算法
     * @param pubKeyPath 保存公钥的路径
     * @param priKeyPath 保存私钥的路径
     */
    public static void generateKeyToFile(String algorithm, String pubKeyPath, String priKeyPath) throws Exception {
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
        String pubkeyEncode = Base64.encode(publicKeyEncoded);
        String pribkeyEncode = Base64.encode(privateKeyEncoded);
        // 把公钥和私钥保存到文件中
        FileUtils.writeStringToFile(new File(pubKeyPath), pubkeyEncode, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priKeyPath), pribkeyEncode, Charset.forName("UTF-8"));
    }
}
