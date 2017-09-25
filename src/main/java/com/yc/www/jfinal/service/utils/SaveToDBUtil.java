package com.yc.www.jfinal.service.utils;

import com.yc.www.jfinal.service.common.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by I321065 on 9/25/2017.
 */
public class SaveToDBUtil {

    private static final Logger log = LogManager.getLogger(SaveToDBUtil.class);

    public static <T extends Entity<T>> T saveModel(T t) {
        String[] fields = t.getFieldNames();
        T saveBean = null;
        for(String field : fields) {
            try {
                saveBean = (T) t.getClass().newInstance();
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
                t.save();
                return saveBean;
            } catch (InstantiationException e) {
                log.error("catch exception...", e);
            } catch (IllegalAccessException e) {
                log.error("catch exception...", e);
            }
        }
        return null;
    }

}
