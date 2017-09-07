package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.user.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{
    private UserService userService = new UserService();

    @ActionKey("/login")
    public void login() {
        String userName = getPara(Constants.USER_NAME);
        String pwd = getPara(Constants.PASS_WORD);

        User user = userService.getUserByName(userName, pwd);
        if(user == null) {
            renderJson("you have not register");
        }

        HttpSession session = getSession(true);
        session.setAttribute("user", user);



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
