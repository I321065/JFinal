package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.common.Constants;

import javax.servlet.http.HttpSession;

/**
 * Created by superuser on 9/7/17.
 */
public class SessionInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpSession session = controller.getSession();
        User user = null;
        if(session == null) {
            controller.renderJson("please login");
        }else {
            user = (User) session.getAttribute("user");
        }
    }
}
