package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.controller.object.RequestObject;
import com.yc.www.jfinal.service.UserService;
import com.yc.www.jfinal.service.annotaion.PermissionCheck;
import com.yc.www.jfinal.service.common.Role;
import com.yc.www.jfinal.service.entity.User;
import com.yc.www.jfinal.service.result.ResponseResult;
import com.yc.www.jfinal.service.utils.ParseRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static com.yc.www.jfinal.service.common.Constants.USER_ID;

/**
 * Created by superuser on 9/7/17.
 * get the user from token and
 */
public class UserTokenInterceptor implements Interceptor {

    private static Logger log = LogManager.getLogger(UserTokenInterceptor.class);
    UserService userService = new UserService();

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        Method mehod = invocation.getMethod();
        try {
            RequestObject userRequestObject = ParseRequest.getObjectFromRequest(RequestObject.class, controller);
            String userToken = userRequestObject.getToken();
            log.info("user token is" + userToken);
            User user = userService.parseUserToken(userToken);
            PermissionCheck permissionCheck = mehod.getAnnotation(PermissionCheck.class);
            boolean hasPermission = checkURLPermission(user, permissionCheck);
            if(!hasPermission) {
                log.error("the user did not have the permission to access this link, userId=" + user == null ? -1 : user.getUserId() + ", url=" + controller.getRequest().getRequestURI());
                controller.renderJson(new ResponseResult(new Error("the user did not have the permission to access this link")));
                return;
            }

            if(!StringUtils.isBlank(userToken) && user == null) {
                log.error("can not parse user from token, token=" + userToken);
                controller.renderJson(new ResponseResult(new Error("can not parse user from token")));
                return;
            }else if(user != null) {
                controller.setAttr(USER_ID, user.getUserId());
            }
            invocation.invoke();
        } catch (IOException e) {
            log.error("");
        }
    }

    private boolean checkURLPermission(User user, PermissionCheck permission) {
        if(permission == null) {
            return true;
        }else if(user == null) {
            return false;
        }else {
            //check the permission
            //user and permission are both not null
            long userId = user.getUserId();
            Role permissionNeed = permission.value();
            if(permissionNeed == Role.Admin) {
                //check admin permission
                return true;
            }else if(permissionNeed == Role.User) {
                //check user permission
                return true;
            }else {
                return true;
            }
        }

    }
}
