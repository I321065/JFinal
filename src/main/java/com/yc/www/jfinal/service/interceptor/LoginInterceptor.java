package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Date;
import java.util.Enumeration;


/**
 * Created by Nick on 2017/3/11.
 */
public class LoginInterceptor implements Interceptor {
    Logger log = LogManager.getLogger(LoginInterceptor.class);
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpSession session = controller.getSession(false);
        if(session == null) {
            log.info("The session is null. will redirect login");
            controller.render("login.html");
        }else {
            String nickName = (String)session.getAttribute("nickName");
            if(!StringUtils.isBlank(nickName)) {
                log.info("通过session登录");
                invocation.invoke();
            }
        }
    }
}
