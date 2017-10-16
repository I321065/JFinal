package com.yc.www.jfinal.controller.request.object;

/**
 * Created by superuser on 10/16/17.
 */
public class LoginRequestObject {

    public String publicKey;

    public String userName;

    public String password;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
