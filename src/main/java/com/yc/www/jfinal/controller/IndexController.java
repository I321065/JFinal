package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.interceptor.SessionInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * Created by Nick on 2017/3/6.
 */
public class IndexController extends Controller {
    Logger logger = LogManager.getLogger(IndexController.class);

    @ActionKey("/index")
    @RequiresRoles()
    @Before(SessionInterceptor.class)
    public void index() {
        renderJson("hello word");
    }


}
