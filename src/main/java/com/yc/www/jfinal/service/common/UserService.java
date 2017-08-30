package com.yc.www.jfinal.service.common;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.*;
import com.yc.www.jfinal.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Created by Nick on 2017/3/5.
 */
public class UserService {
    Logger log  = LogManager.getLogger(UserService.class);
    public User findUser(String name,String password) {
        User user = new User();
        user = null;

        String sql = "select * from user where name='" + name + "' and password='" + password + "';";

        user =User.dao.findFirst(sql);

        System.out.printf("sql_template:"+sql);

        return user;
    }

    public void findUserByName(String name) {

        JMap condition = JMap.create("name", name);
        SqlPara sp = Db.getSqlPara("user.getUserByName", condition);
        List<Record> userList =  Db.find(sp);
        Record re = userList.get(0);

        String name1 = re.getStr("name");
        String password = re.getStr("password");
        return;
    }

    public User loginUser(String nickName, String pwd) {
        String sql = User.dao.getSql("user.validateUserByNickNameAndPassWord");
        User user = User.dao.findFirst(sql, nickName, pwd);
        return user;
    }
}
