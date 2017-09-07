package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.EncryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Nick on 2017/4/18.
 */

public class RegisterController extends Controller {
    Logger logger = LogManager.getLogger(RegisterController.class);

    UserService service = new UserService();

    @ActionKey("/register")
    public void register() {
        String userName = getAttr("userName");
        String pwd = EncryptUtil.sha1((String)getAttr("pwd"));

        User user = new User(userName, pwd);
        user = service.saveUser(user);
        renderJson(user);
    }









}
