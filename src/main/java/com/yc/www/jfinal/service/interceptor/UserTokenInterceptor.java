package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.user.bean.User;
import com.yc.www.jfinal.service.utils.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * Created by superuser on 9/7/17.
 */
public class UserTokenInterceptor implements Interceptor {

    private static Logger logger = LogManager.getLogger(UserTokenInterceptor.class);
    UserService userService = new UserService();

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        String userToken = controller.getPara("token");
        logger.info("token is" + userToken);
        User user = userService.parseUserToken(userToken);
        if(user == null) {
            controller.renderJson("redirect login");
        }else {
            controller.setAttr("user", user);
            invocation.invoke();
        }
    }
}
