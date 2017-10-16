package com.yc.www.jfinal.service.annotaion;

import java.lang.annotation.*;

/**
 * Created by I321065 on 10/16/2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestField {
    String value() default "";
}
