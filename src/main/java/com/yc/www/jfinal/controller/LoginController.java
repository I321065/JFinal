package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import com.yc.www.jfinal.controller.object.*;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.result.ResponseError;
import com.yc.www.jfinal.service.result.ResponseResult;
import com.yc.www.jfinal.service.UserService;
import com.yc.www.jfinal.service.entity.User;
import com.yc.www.jfinal.service.vo.UserVO;
import com.yc.www.jfinal.service.utils.Base64Util;
import com.yc.www.jfinal.service.utils.JedisUtil;
import com.yc.www.jfinal.service.utils.ParseRequest;
import com.yc.www.jfinal.service.utils.RSAUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nick on 2017/3/5.
 */

public class LoginController extends Controller{

    private static final Logger log = LogManager.getLogger("LoginController");

    private UserService userService = new UserService();

    private static Map<String, String> keyPairs = new ConcurrentHashMap<String, String>();

    @ActionKey("/publicKey")
    public void publicKey() {
        try {
            List<String> loginKeyPair = RSAUtil.createLoginKeyPair();
            if(loginKeyPair == null || loginKeyPair.size() != 2) {
                renderJson(new ResponseResult(new ResponseError(Constants.CONTACT_ADMINISTRATOR)));
                return;
            }
            String publicKey = loginKeyPair.get(0);
            String privateKey = loginKeyPair.get(1);
            JedisUtil.set(publicKey, privateKey);
            ResponseResult result = new ResponseResult(publicKey);
            renderJson(result);
            return;
        } catch (Exception e) {
            log.error("catch exception", e);
        }
        renderJson(new ResponseResult(new ResponseError(Constants.CONTACT_ADMINISTRATOR)));
    }


    @ActionKey("/register")
    public void register() {
        try {
            RequestObject object = ParseRequest.getObjectFromRequest(RequestObject.class, this);
            String username = object.getUsername();
            String password = object.getPassword();
            String publicKey = object.getPublicKey();
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                renderJson(new ResponseResult(new ResponseError("the user Name or password can be null")));
                return;
            }
            String privateKey = JedisUtil.getStringValue(publicKey);
            String pwd = new String(RSAUtil.decryptByPrivateKey(Base64Util.decodeString(password), privateKey));
            User user = userService.getUserByName(username);
            if(user != null) {
                log.info("The user name has been registered, please change it, username=" + username);
                renderJson(new ResponseResult(new ResponseError("The user name has been registered, please change it")));
                return;
            }
            user = new User(username, pwd);
            userService.saveUser(user);
            User loginUser = userService.getLoginUser(username, pwd);
            if(loginUser == null) {
                renderJson(new ResponseResult(new ResponseError("the password is not right")));
                return;
            }
            UserVO uVO = new UserVO();
            uVO.setUsername(user.getUsername());
            uVO.setToken(userService.generateUserToken(user));
            renderJson(new ResponseResult(uVO));
            return;
        } catch (IOException e) {
            log.error("failed to get request object", e);
        } catch (Exception e) {
            log.error("catch exception", e);
        }
        renderJson(new ResponseResult(new ResponseError(Constants.CONTACT_ADMINISTRATOR)));
    }


    @ActionKey("/login")
    public void login() {
        try {
            //parse json request
            RequestObject loginRequestObject = ParseRequest.getObjectFromRequest(RequestObject.class, this);
            String publicKey = loginRequestObject.getPublicKey();
            String uName = loginRequestObject.getUsername();
            String password = loginRequestObject.getPassword();
            /*String publicKey = getPara("publicKey");
            String uName = getPara("username");
            String password = getPara("password");*/

            if(StringUtils.isBlank(publicKey) || StringUtils.isBlank(uName) || StringUtils.isBlank(password)) {
                log.error("failed to load login user, publicKey=" + publicKey + ", uName=" + uName + ", password=" + password);
                renderJson(new ResponseResult(new ResponseError("the login user message upload failed")));
                return;
            }

            String privateKey = JedisUtil.getStringValue(publicKey);
            String pwd = new String(RSAUtil.decryptByPrivateKey(Base64Util.decodeString(password), privateKey));
            User user = userService.getLoginUser(uName, pwd);
            if(user == null) {
                renderJson(new ResponseResult(new ResponseError("the password is not right")));
                return;
            }
            UserVO uVO = new UserVO();
            uVO.setUsername(user.getUsername());
            uVO.setToken(userService.generateUserToken(user));
            renderJson(new ResponseResult(uVO));
            return;
        } catch (Exception e) {
            log.error("catch exception", e);
        }

        renderJson(new ResponseResult(new ResponseError(Constants.CONTACT_ADMINISTRATOR)));
    }

}
