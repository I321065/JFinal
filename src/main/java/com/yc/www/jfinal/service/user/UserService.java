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

    public User getLoginUser(String username, String password) {
        try {
            String sqlString = "select userId, password, salt from user where username = '" + username + "'";
            log.info("get the user String=" + sqlString);
            User user = User.dao.findFirst(sqlString);

            if(user == null) {
                log.info("user is null by DB query");
                return null;
            }
            String salt = user.getStr("salt");
            String pwdDB = user.getStr("password");
            String passWord = MD5Util.generate(password, salt);
            log.info("get the user password=" + passWord);
            if(passWord.equals(pwdDB)) {
                user.setUserId(user.getLong("userId"));
                user.setUsername(username);
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
        String sql = "select username from user where username='" + name + "';";
        User user = User.dao.findFirst(sql);
        return user;
    }

    public String getUsernameById(long userId) {
        if(userId < 0) {
            log.error("userId is not right, userId=" + userId);
            return null;
        }
        String sql = "select username from user where userId=" + userId + ";";
        User user = User.dao.findFirst(sql);
        if(user != null) {
            return user.getStr("username");
        }
        return null;
    }

    public User getUserByUserIdAndUsername(long userId, String username) {
        if(userId <= 0) {
            log.error("the userId is not right, userId=" + userId);
            return null;
        }
        String sql = "select userId, username from user where userId = " + userId + " AND username='" + username + "';";
        log.info("get user by name and userId sql= " + sql);
        User user = User.dao.findFirst(sql);
        if(user != null) {
            user.setUserId(user.getLong("userId"));
            user.setUsername(user.getStr("username"));
        }
        log.info("get user by name and userId " + user);
        return user;
    }

    public void saveUser(User user) {
        User userDB = new User();
        userDB.setUsername(user.getUsername());
        String salt = MD5Util.generateSalt();
        userDB.setSalt(salt);
        userDB.setPassword(MD5Util.generate(user.getPassword(), salt));
        userDB.setCreateDate(new Date());
        userDB.setUpdateDate(new Date());
        SaveToDBUtil.saveModel(userDB);
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
    private static String generateUserSubject(User user){
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getUserId());
        jo.put("username", user.getUsername());
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
            long userId = obj.getLongValue("userId");
            String username = obj.getString("username");
            log.info("parse User Token userId=" + userId + " username=" + username);
            user = getUserByUserIdAndUsername(userId, username);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse User token failed", e);
        }
        return user;
    }
}
