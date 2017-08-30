package com.yc.www.jfinal.model.entity;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Created by Nick on 2017/3/5.
 */
public class User extends Model<User> {
    public static final User dao = new User().dao();

    private int userId;
    private String nickName;
    private String passWord;
    private String phoneNumber;

    private Date createDate;
    private Date endDate;

    public User() {}

    public User(String nickName, String passWord) {
        this(nickName, passWord, null);
    }

    public User(String nickName, String passWord, String phoneNumber) {
        this.nickName = nickName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEffectiveDate() {
        return endDate;
    }

    public void setEffectiveDate(Date endDate) {
        this.endDate = endDate;
    }
}
