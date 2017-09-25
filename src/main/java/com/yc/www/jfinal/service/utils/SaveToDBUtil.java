package com.yc.www.jfinal.service.utils;

import com.yc.www.jfinal.service.common.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by I321065 on 9/25/2017.
 */
public class SaveToDBUtil {

    private static final Logger log = LogManager.getLogger(SaveToDBUtil.class);

    public static <T extends Entity<T>> T saveModel(T t) {
        String[] fields = t.getFieldNames();
        Class<?> clazz = t.getClass();
        T saveBean = null;
        try {
            saveBean = (T) clazz.newInstance();
            for(String field : fields) {
                if(clazz.getDeclaredField(field).getModifiers() == 25) {
                    continue;
                }
                Object value = t.getFieldValue(field);
                if(value == null) {
                    continue;
                }
                if(value instanceof String) {
                    saveBean.set(field,(String)value);
                }else if(value instanceof Date) {
                    saveBean.set(field, (Date)value);
                }else if(value instanceof Integer) {
                    saveBean.set(field,(Integer)value);
                }
            }
            saveBean.save();
            return saveBean;
        } catch (InstantiationException e) {
            log.error("catch exception...", e);
        } catch (IllegalAccessException e) {
            log.error("catch exception...", e);
        } catch (NoSuchFieldException e) {
            log.error("catch exception...", e);
        }
        return null;
    }

}
