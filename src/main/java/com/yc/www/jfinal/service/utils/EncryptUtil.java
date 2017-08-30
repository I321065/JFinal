package com.yc.www.jfinal.service.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nick on 2017/3/11.
 */
public class EncryptUtil {
    private static final String MD_5 = "MD5";
    private static final String SHA = "SHA";

    public static String md5Encode(String info) {
        MessageDigest md5 = null;
        StringBuffer sb = null;
        try {
            md5 = MessageDigest.getInstance(MD_5);
            byte[] bytes = info.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(bytes);
            sb = new StringBuffer();
            for(int i=0; i<md5Bytes.length; i++) {
                int val = ((int)md5Bytes[i]) & 0xff;
                if(val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static String shaEncode(String info) {
        MessageDigest sha = null;
        StringBuffer sb = null;
        try {
            sha = MessageDigest.getInstance(SHA);
            byte[] bytes = info.getBytes("UTF-8");
            byte[] shaBytes = sha.digest(bytes);
            sb = new StringBuffer();
            for(int i=0; i<shaBytes.length; i++) {
                int val = ((int)shaBytes[i]) & 0xff;
                if(val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
