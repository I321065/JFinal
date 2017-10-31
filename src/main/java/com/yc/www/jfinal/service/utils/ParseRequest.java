package com.yc.www.jfinal.service.utils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by superuser on 10/16/17.
 */
public class ParseRequest {

    private static final Logger log = LogManager.getLogger(ParseRequest.class);

    public static <T> T getObjectFromRequest(Class<T> t, Controller controller) {
        StringBuilder json = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = controller.getRequest().getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
            return JSONObject.parseObject(json.toString(), t);
        } catch (IOException e) {
            log.error("failed to parse object from request", e);
        }
        return null;
    }
}
