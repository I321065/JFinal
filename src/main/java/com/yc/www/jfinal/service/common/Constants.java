package com.yc.www.jfinal.service.common;

/**
 * Created by Nick on 2017/4/18.
 */
public class Constants {
    public static final String CHARS_TEMPLATE = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String USER_NAME = "userName";
    public static final String PASS_WORD = "pwd";

    //JWT
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond

}
