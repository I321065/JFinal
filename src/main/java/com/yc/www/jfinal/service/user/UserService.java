package com.yc.www.jfinal.service.user;

import com.alibaba.fastjson.JSONObject;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.*;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;


/**
 * Created by Nick on 2017/3/5.
 */
public class UserService {
    private static final Logger log  = LogManager.getLogger(UserService.class);

    public User getLoginUser(String userName, String password, String privateKey) {
        try {
            String sqlString = "select userId, password, salt from user where userName = '" + userName + "'";
            log.info("get the user String=" + sqlString);
            User user = User.dao.findFirst(sqlString);
            log.info("user is null by DB query=" + user);
            if(user == null) {
                log.info("user is null by DB query");
                return null;
            }
            String salt = user.getStr("salt");
            String pwdDB = user.getStr("password");
            String passWord = MD5Util.generate(password, salt);
            log.info("get the user password" + passWord);
            if(passWord.equals(pwdDB)) {
                user.setUserId(user.getLong("userId"));
                user.setUserName(userName);
                return user;
            }else {
                log.info("password is wrong");
            }
        } catch (Exception e) {
            log.error("catch exception", e);
        }
        return null;
    }

    public User getUserByName(String name) {
        String sql = "select userName from user where userName='" + name + "';";
        User user = User.dao.findFirst(sql);
        return user;
    }

    public String getUserNameById(long userId) {
        if(userId < 0) {
            log.error("userId is not right, userId=" + userId);
            return null;
        }
        String sql = "select userName from user where userId=" + userId + ";";
        User user = User.dao.findFirst(sql);
        if(user != null) {
            return user.getStr("userName");
        }
        return null;
    }

    public User getUserByUserIdAndUserName(long userId, String userName) {
        String sql = "select userId, userName from user where userId = " + userId + " AND userName='" + userName + "';";
        log.info("get user by name and userId sql= " + sql);
        User user = User.dao.findFirst(sql);
        if(user != null) {
            user.setUserId(user.getLong("userId"));
            user.setUserName(user.getStr("userName"));
        }
        log.info("get user by name and userId " + user);
        return user;
    }

    public void saveUser(User user) {
        User userDB = new User();
        userDB.setUserName(user.getUserName());
        String salt = MD5Util.generateSalt();
        userDB.setSalt(salt);
        userDB.setPassword(MD5Util.generate(user.getPassword(), salt));
        userDB.setCreateDate(new Date());
        userDB.setUpdateDate(new Date());
        SaveToDBUtil.saveModel(user);
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
    private static String generateUserSubject(User user){
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getUserId());
        jo.put("userName", user.getUserName());
        return jo.toJSONString();
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
    public String generateUserToken(User user) {
        String userObject = generateUserSubject(user);
        String jwt = null;
        try {
            jwt = JWTUtil.createJWT(Constants.JWT_ID, userObject, Constants.JWT_TTL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwt;
    }

    public User parseUserToken(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        User user = null;
        try {
            Claims claims = JWTUtil.parseJWT(token);
            String userJson = claims.getSubject();
            JSONObject obj = (JSONObject) JSONObject.parse(userJson);
            int userId = obj.getInteger("userId");
            String userName = obj.getString("userName");
            log.info("parse User Token userId=" + userId + " userName=" + userName);
            user = getUserByUserIdAndUserName(userId, userName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse User token failed", e);
        }
        return user;
    }
}
