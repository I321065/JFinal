package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * Created by superuser on 9/7/17.
 */
public class SessionInterceptor implements Interceptor {

    private static Logger logger = LogManager.getLogger(SessionInterceptor.class);

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpSession session = controller.getSession();
        if(session == null) {
            controller.renderJson("please login, redirect login page");
            return;
        }else {
            User user = (User) session.getAttribute("user");
            if(user != null) {
                logger.info("get the user from session, userName=" + user.getUserName());
                invocation.invoke();
                return;
            }else {
                controller.renderJson("can not find the user in session, sessionId=" + session.getId());
            }

        }

    }
}
