package com.yc.www.jfinal.service.user;

import com.yc.www.jfinal.service.table.Columns;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.Base64Util;
import com.yc.www.jfinal.service.utils.EncryptUtil;
import com.yc.www.jfinal.service.utils.MD5Util;
import com.yc.www.jfinal.service.utils.RSAUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;


/**
 * Created by Nick on 2017/3/5.
 */
public class UserService {
    Logger log  = LogManager.getLogger(UserService.class);

    public User getLoginUser(String userName, String pwd, String privateKey) {
        try {
            //decrypt userName
            String pwdTemp = new String(RSAUtil.decryptByPrivateKey(Base64Util.decodeString(pwd), privateKey));
            String sqlString = "select USER_PASS_WORD, USER_SALT from user where USER_NAME='" + userName + "'";
            User user = User.dao.findFirst(sqlString);
            String salt = user.getSalt();
            String pwdDB = user.getPassWord();
            String passWord = MD5Util.generate(pwdTemp, salt);
            if(passWord.equals(pwdDB)) {
                user.setUserName(userName);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

    public User getUserByName(String name) {
        String sql = "select USER_NAME from user where USER_NAME='" + name + "';";
        User user = User.dao.findFirst(sql);
        return user;
    }

    public void saveUser(User user) {
        User userDB = new User();
        userDB.set(Columns.User.NAME, user.getUserName());
        String salt = MD5Util.generateSalt();
        userDB.set(Columns.User.SALT, salt);
        userDB.set(Columns.User.PASS_WORD, MD5Util.generate(user.getPassWord(), salt));
        userDB.set(Columns.User.CREATE_DATE, new Date());
        userDB.set(Columns.User.UPDATE_DATE, new Date());
        userDB.save();
    }

}
