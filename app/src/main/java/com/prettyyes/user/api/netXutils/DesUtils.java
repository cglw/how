package com.prettyyes.user.api.netXutils;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/7/19
 * Description: 用于Des加密字符串
 */
public class DesUtils {
    public static final byte[] Key1 = "x7x6i8a9o9y2u6a8n6x7".getBytes();
    public static final byte[] Key2 = "d8x2i5a6o7w2u1d8".getBytes();


    /**
     * 对外暴露的加密，des加密后要进行Base64编码
     *
     * @param txt
     * @return
     * @throws Exception
     */
    public static String encrypt(String txt) throws Exception {

        byte[] result1 = encrypt(txt.getBytes(), Key1);
        String des1 = new String(Base64.encode(result1, Base64.DEFAULT), "UTF-8");
        byte[] result2 = encrypt(des1.getBytes(), Key2);
        String des2 = new String(Base64.encode(result2, Base64.DEFAULT), "UTF-8");

        return des2;
    }

    public static String encryptionByDes(String txt) {
        try {
            return java.net.URLEncoder.encode(encrypt(txt), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 对外暴露的解密，需要先对字符串进行Base64解码
     *
     * @param unkonw
     * @return
     */
    public static String decrypt(String unkonw) {
        String desdecode1 = DesUtils.decrypt(Base64.decode(unkonw, 0), DesUtils.Key2);
        String desdecode2 = DesUtils.decrypt(Base64.decode(desdecode1, 0), DesUtils.Key1);
        return desdecode2;
    }

    private static byte[] encrypt(byte[] data, byte[] key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            // using DES in ECB mode
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
            // 执行加密操作
            byte encryptedData[] = cipher.doFinal(data);
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密函数
     *
     * @param data 解密数据
     * @param key  密钥
     * @return 返回解密后的数据
     */
    private static String decrypt(byte[] data, byte[] key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // byte rawKeyData[] = /* 用某种方法获取原始密匙数据 */;
            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            // using DES in ECB mode
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
            // 正式执行解密操作
            byte decryptedData[] = cipher.doFinal(data);

            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
