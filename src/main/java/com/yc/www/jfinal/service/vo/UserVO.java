package com.yc.www.jfinal.service.vo;

/**
 * Created by superuser on 9/16/17.
 * pack the user data for token
 */
public class UserVO {

    private String username;

    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
