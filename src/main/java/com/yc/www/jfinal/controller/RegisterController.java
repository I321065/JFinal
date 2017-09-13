package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.EncryptUtil;
import org.apache.commons.lang.StringUtils;
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
        String userName = getPara("userName");
        User user = service.getUserByName(userName);
        if(user != null) {
            logger.info("The userName has been registered, please change it, userName=" + userName);
            return;
        }
        String pwd = EncryptUtil.sha1(getPara("pwd"));
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
            renderJson("the userName and password can be null");
            return;
        }
        user = new User(userName, pwd);
        service.saveUser(user);
        renderJson(user);
    }









}
