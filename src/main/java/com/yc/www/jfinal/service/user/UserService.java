package com.yc.www.jfinal.service.user;

import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.user.bean.UserColumn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Created by Nick on 2017/3/5.
 */
public class UserService {
    Logger log  = LogManager.getLogger(UserService.class);


    public User getUserByName(String userName, String pwd) {
        String sqlString = "select * from user where USER_NAME='" + userName + "' AND USER_PASS_WORD='" + pwd + "'";
        User user = User.dao.findFirst(sqlString);
        return user;
    }

    public User saveUser(User user) {
       return User.dao._setAttrs(user);
    }

}
