package com.hornen.common;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hornen on 15/9/29.
 */
public class Common {
    /**
     * string hash
     * @param src source string need to be hash
     * @return string or null
     */
    public static String md5(String src) {

        if(null == src || src.length() == 0) {
            return null;
        }

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(src.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    /**
     * decode string to base64 byte
     * @param src source string
     * @return bytes array
     */
    public static byte[] base64Decode(String src) {
        return Base64.decode(src, Base64.DEFAULT);
    }

    /**
     * encode byte to base64 string
     * @param src source bytes
     * @return string
     */
    public static String base64Encode(byte[] src) {
        return Base64.encodeToString(src, Base64.DEFAULT);
    }

    /**
     * check mobile format
     * @param mobile mobile phone
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * check email format
     * @param email email
     * @return boolean
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern
                .compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int currentUTCSeconds() {
        return (int)(System.currentTimeMillis() / 1000);
    }
}
