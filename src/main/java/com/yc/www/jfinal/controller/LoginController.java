package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.result.json.Result;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.user.bean.vo.UserVO;
import com.yc.www.jfinal.service.utils.Base64Util;
import com.yc.www.jfinal.service.utils.JedisUtil;
import com.yc.www.jfinal.service.utils.RSAUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{
    Logger logger = LogManager.getLogger("LoginController");
    private UserService userService = new UserService();

    private static Map<String, String> keyPairs = Collections.synchronizedMap(new HashMap<String, String>());

    @ActionKey("/getPublicKey")
    public void getPublickey() {
        try {
            List<String> loginKeyPair = RSAUtil.createLoginKeyPair();
            if(loginKeyPair == null || loginKeyPair.size() != 2) {
                renderJson("failed to create public key");
                return;
            }
            String publicKey = loginKeyPair.get(0);
            String privateKey = loginKeyPair.get(1);
            JedisUtil.set(publicKey, privateKey);
            Result result = new Result(publicKey, 0, null);
            renderJson(result);
            return;
        } catch (Exception e) {
            logger.error("catch exception", e);
            renderJson("failed to create public key");
        }
    }


    @ActionKey("/login")
    public void login() throws Exception {
        String publicKey = getPara("publicKey");
        String uName = getPara("userName");
        String password = getPara("password");
        String privateKey = JedisUtil.getStringValue(publicKey);
        String pwd = new String(RSAUtil.decryptByPrivateKey(Base64Util.decodeString(password), privateKey));
        User user = userService.getLoginUser(uName, pwd, privateKey);
        if(user == null) {
            renderJson(new Result("the password is not right"));
        }
        UserVO uVO = new UserVO();
        uVO.setUserName(user.getUserName());
        uVO.setToken(userService.generateUserToken(user));
        renderJson(new Result(uVO));
    }

}
