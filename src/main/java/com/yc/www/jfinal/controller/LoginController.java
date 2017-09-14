package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.utils.RSAUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{
    Logger logger = LogManager.getLogger(LoginController.class);
    private UserService userService = new UserService();

    private static Map<String, String> keyPairs = Collections.synchronizedMap(new HashMap<String, String>());

    @ActionKey("getPublicKey")
    public void getPublickey() {
        try {
            Map<String, String> loginKeyPair = RSAUtil.createLoginKeyPair();
            keyPairs.putAll(loginKeyPair);
            String publicKey = loginKeyPair.keySet().iterator().next();
            renderJson(publicKey, keyPairs);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderJson("failed to create public key");
    }


    @ActionKey("/login")
    public void login() {
        String publicKey = getPara("publicKey");
        String privateKey = keyPairs.get(getPara("publicKey"));
        logger.info(privateKey);
        renderJson(privateKey);
        return;
        /*keyPairs.remove(publicKey);
        String userName = getPara(Constants.USER_NAME);
        String pwd = getPara(Constants.PASS_WORD);
        User user = userService.getLoginUser(userName, pwd, privateKey);
        if(user != null) {
            renderJson("login success");
        }else {
            renderJson("you have not register");
        }*/
    }


    private HttpSession createUserSession(HttpSession session, User user) {
        if(session.isNew()) {
            session.setMaxInactiveInterval(30*60);//30 minutes
            session.setAttribute("user", user);
        }
        return session;
    }
}
