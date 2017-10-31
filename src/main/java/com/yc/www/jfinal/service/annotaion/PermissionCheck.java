package com.yc.www.jfinal.service.annotaion;

import com.yc.www.jfinal.service.common.Role;

import java.lang.annotation.*;

/**
 * Created by superuser on 10/31/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PermissionCheck {
    Role value();
}
