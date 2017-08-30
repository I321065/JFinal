package com.yc.www.jfinal.service.common;

import com.yc.www.jfinal.model.entity.User;
import com.yc.www.jfinal.service.utils.SendMessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by Nick on 2017/4/18.
 */
public class RegisterService {
    Logger log  = LogManager.getLogger(RegisterService.class);

    //
    public void sendIdentifyCode(String phoneNumber) {
        //create a random code
        String identifyCode = SendMessageUtil.createIdentifiedCode();
        User registerUser = new User(phoneNumber, identifyCode, phoneNumber);
        registerUser.setCreateDate(new Date());
        registerUser.setEffectiveDate(new Date(new Date().getTime() + 5*60*1000));//effective time 5 minutes
        User.dao.put(registerUser);
    }

    public void addUser(User user) {
        String phoneNumber = user.getPhoneNumber();

    }

}
