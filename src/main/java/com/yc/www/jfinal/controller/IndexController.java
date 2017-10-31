package com.yc.www.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Nick on 2017/3/6.
 */
public class IndexController extends Controller {
    Logger logger = LogManager.getLogger(IndexController.class);
    private User user;

    @ActionKey("/index")
    public void index() {
        renderJson("hello word");
    }


}
