package com.yc.www.jfinal.service.utils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by superuser on 10/16/17.
 */
public class ParseRequest {

    public static <T> T getObjectFromRequest(Class<T> t, Controller controller) throws IOException {
        StringBuilder json = new StringBuilder();
        BufferedReader reader = controller.getRequest().getReader();
        String line = null;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        return JSONObject.parseObject(json.toString(), t);
    }
}
