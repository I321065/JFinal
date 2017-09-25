package com.yc.www.jfinal.service.common;

import com.jfinal.plugin.activerecord.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;


/**
 * Created by I321065 on 9/25/2017.
 */
public class Entity<T extends Model> extends Model<T> {
    Logger log = LogManager.getLogger(Entity.class);

    public String[] getFieldNames() {
        Field[] fields = this.getClass().getFields();
        String[] names = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    public Object getFieldValue(String name) {
        try {
            return this.getClass().getField(name).get(this);
        } catch (NoSuchFieldException nsfe) {
            log.error("Error occurred: ", nsfe);
        } catch (IllegalAccessException iae) {
            log.error("Error occurred: ", iae);
        }
        return null;
    }
}
