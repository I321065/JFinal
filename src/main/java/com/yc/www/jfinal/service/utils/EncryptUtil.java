package com.yc.www.jfinal.service.utils;

import com.jfinal.log.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.util.EmptyStackException;

/**
 * Created by superuser on 9/7/17.
 */
public class EncryptUtil {
    private static Logger logger = LogManager.getLogger(EncryptUtil.class);

    public static String sha1(String str) {
        if(str == null || str.length() == 0) {
            return null;
        }

        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            logger.error("catch some exception when using SHA1 to encrypt");
            return null;
        }
    }
}
