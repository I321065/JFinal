package com.yc.www.jfinal.service.user;

import com.yc.www.jfinal.service.table.Columns;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.EncryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;


/**
 * Created by Nick on 2017/3/5.
 */
public class UserService {
    Logger log  = LogManager.getLogger(UserService.class);

    public User getLoginUser(String userName, String pwd) {
        String passWord = EncryptUtil.sha1(pwd);
        String sqlString = "select * from user where USER_NAME='" + userName + "' AND USER_PASS_WORD='" + passWord + "'";
        User user = User.dao.findFirst(sqlString);
        return user;
    }

    public User getUserByName(String name) {
        String sql = "select USER_NAME from user where USER_NAME='" + name + ".";
        User user = User.dao.findFirst(sql);
        return user;
    }

    public void saveUser(User user) {

        User userDB = new User();
        userDB.set(Columns.User.USER_NAME, user.getUserName());
        userDB.set(Columns.User.USER_PASS_WORD, user.getPassWord());
        userDB.set(Columns.User.CREATE_DATE, new Date());
        userDB.set(Columns.User.UPDATE_DATE, new Date());
        userDB.save();
    }

}
