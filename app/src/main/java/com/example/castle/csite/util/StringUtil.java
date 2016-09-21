package com.example.castle.csite.util;

import android.text.TextUtils;

import java.security.MessageDigest;

/**
 * Created by castle on 16-8-30.
 * 字符串工具类
 */
public class StringUtil {


    /**
     * 转换指定字符串为md5格式
     * @param string 要加密的字符串
     * @return md5字符串
     */
    public static String toMD5(String string) {
        try {
            byte[] strBytes = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(strBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        }
        catch (java.security.NoSuchAlgorithmException e) {
            return string.replace("/", ".");
        }
    }

    /**
     * 比较两个字符串是否相同，如果其中有个为空或者不相等则返回false
     * @param text1 字符串一
     * @param text2 字符串二
     * @return 比较两个字符串是否相同
     */
    public static boolean isSame(String text1, String text2) {
        return nonNull(text1, text2) && text1.equals(text2);
    }

    /**
     * 一次传入多个字符串，判断是否没有空字符串或者null
     *
     * @param strings 要判断的字符串，一个或多个
     * @return 是否没有空字符串或者null
     */
    public static boolean nonNull(String... strings) {
        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
    }


}
