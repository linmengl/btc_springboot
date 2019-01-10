package com.blockchain.util;


import com.blockchain.service.util.impl.UtilServiceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static final char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String MD5(String passwordInPlainText) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(passwordInPlainText.getBytes("utf-8"));
            byte[] md = mdTemp.digest();
            char[] str = new char[md.length << 1];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String MD5forRequest(String content) {
        MessageDigest md5;
        try {
            md5 = MessageDigest
                    .getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            return null;
        }
        md5.update(content.getBytes());
        byte[] domain = md5.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        // converting domain to String
        for (int i = 0; i < domain.length; ++i) {
            if (Integer.toHexString(0xFF & domain[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & domain[i]));
            } else
                md5StrBuff.append(Integer.toHexString(0xFF & domain[i]));
        }
        return md5StrBuff.toString();
    }

    public static String generateRandomSalt () {
        return ApplicationContextHolder.getBean(UtilServiceImpl.class).generateKey(9999, 24);
    }



}
