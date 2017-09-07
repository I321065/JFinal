package com.yc.www.jfinal.service.user.bean;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Created by Nick on 2017/3/5.
 */
public class User extends Model<User> {

    public static final User dao = new User().dao();

    private int userId;
    private String userName;
    private String passWord;

    private Date createDate;
    private Date updateDate;

    public User() {}

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setNickName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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
