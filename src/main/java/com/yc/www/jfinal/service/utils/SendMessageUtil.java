package com.yc.www.jfinal.service.utils;

import com.yc.www.jfinal.service.common.CommonConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by Nick on 2017/4/18.
 */
public class SendMessageUtil {
    Logger log  = LogManager.getLogger(SendMessageUtil.class);

    public static String sendIdentifiedCodeToPhone(String phoneNumber) {


        return null;
    }

    public static String createIdentifiedCode() {
        return createCombiningChars(6);
    }

    private static String createCombiningChars(int length) {
        char [] charArr = new char[length];
        Random random = new Random();
        int tempLength = CommonConstants.CHARS_TEMPLATE.length();
        for(int i = 0; i < length; i++) {
            //create random number
            int index = random.nextInt(tempLength);
            charArr[i] = CommonConstants.CHARS_TEMPLATE.charAt(index);
        }
        return String.valueOf(charArr);
    }

}
