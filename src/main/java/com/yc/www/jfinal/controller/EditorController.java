package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.interceptor.UserTokenInterceptor;
import com.yc.www.jfinal.service.user.bean.User;

/**
 * Created by superuser on 9/18/17.
 */
public class EditorController extends Controller {

    @ActionKey("/edit")
    @Before(UserTokenInterceptor.class)
    public void edit() {
        renderJson("you can edit");
    }
}
