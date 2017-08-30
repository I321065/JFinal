package com.yc.www.jfinal.service.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nick on 2017/3/11.
 */
public class ServletUtil {

    public void addCookie(String userName, String pwd, HttpServletResponse response) {
        Cookie cookie = new Cookie(EncryptUtil.shaEncode("cookieName"), EncryptUtil.shaEncode(userName + "-" + pwd));
        cookie.setDomain(".yc.com");
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
               return cookie;
            }
        }
        return null;
    }

}
