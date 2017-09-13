package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.interceptor.SessionInterceptor;

/**
 * Created by superuser on 9/8/17.
 */
public class TestSessionController extends Controller {

    @ActionKey("/test")
    @Before(SessionInterceptor.class)
    public void test() {
        renderJson("have login  via session");
    }
}
