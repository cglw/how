package com.hornen.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hornen on 15/11/11.
 */
public class MD5 {
    public MD5() {
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var5) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for(int i = 0; i < byteArray.length; ++i) {
            if(Integer.toHexString(255 & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
            }
        }

        return md5StrBuff.substring(8, 24).toString().toUpperCase();
    }

    public static String getMD5(String val) {
        if(val == null) {
            return "";
        } else {
            MessageDigest md5;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var3) {
                return "";
            }

            md5.update(val.getBytes());
            byte[] m = md5.digest();
            return getString(m);
        }
    }

    private static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(b[i])}));
        }

        return sb.toString();
    }
}
