package com.yc.www.jfinal.service.user.bean.vo;

/**
 * Created by superuser on 9/16/17.
 * pack the user data for token
 */
public class UserVO {

    private String userName;

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
