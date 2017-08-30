package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.model.entity.User;
import com.yc.www.jfinal.service.common.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{
    private UserService userService = new UserService();

    @ActionKey("/login/standard")
    public void login() {
        String nickName = getPara("nickName");
        String pwd = getPara("pwd");

        User user = userService.loginUser(nickName, pwd);
        if(user != null) {
            Map<String, Object> mess = new HashMap<String, Object>();
            mess.put("nickName", nickName);
            createSession(mess);
        }else {
            renderJson("此用户并未注册，请注册");
        }
        render("success.html");
    }

    @ActionKey("/login/others")
    public void loginByOtherAccount() {

    }

    private HttpSession createSession(Map<String, Object> messages) {
        HttpSession session = getSession(true);
        if(session.isNew()) {
            session.setMaxInactiveInterval(30*60);//30 minutes
            for(Map.Entry<String, Object> entry : messages.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
        }
        return session;
    }

}
