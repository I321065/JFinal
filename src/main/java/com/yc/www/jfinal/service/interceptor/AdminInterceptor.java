package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;


/**
 * Created by Nick on 2017/4/23.
 */
public class AdminInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpSession session = controller.getSession();
        String userId = (String)session.getAttribute("userId");

        //check db


    }
}
