package com.yc.www.jfinal.service.entity;

import com.yc.www.jfinal.service.common.Entity;

import java.util.Date;

/**
 * Created by Nick on 2017/3/5.
 */
public class User extends Entity<User> {

    public static final User dao = new User().dao();

    private long userId;
    private String username;
    private String password;
    private String salt;

    private Date createDate;
    private Date updateDate;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
