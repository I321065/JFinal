package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.interceptor.LoginInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Nick on 2017/3/11.
 */
public class ProductionController extends Controller {
    Logger log = LogManager.getLogger(ProductionController.class);
    @ActionKey("/getGoods")
    @Before(LoginInterceptor.class)
    public void get() {
        render("goods.html");
    }


}
