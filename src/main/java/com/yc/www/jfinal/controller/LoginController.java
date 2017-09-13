package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{
    Logger logger = LogManager.getLogger(LoginController.class);
    private UserService userService = new UserService();

    @ActionKey("/login")
    public void login() {
        String userName = getPara(Constants.USER_NAME);
        String pwd = getPara(Constants.PASS_WORD);
        User user = userService.getLoginUser(userName, pwd);
        if(user != null) {
            HttpSession session = createSession(user);
            renderJson("login success, session=" + session.toString());
        }else {
            renderJson("you have not register");render();
        }
        createToken();
    }


    private HttpSession createSession(User user) {
        HttpSession session = getSession(true);
        if(session.isNew()) {
            session.setMaxInactiveInterval(30*60);//30 minutes
            session.setAttribute("user", user);
        }
        return session;
    }

}
