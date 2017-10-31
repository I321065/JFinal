package com.yc.www.jfinal.service.utils;

import com.yc.www.jfinal.service.factory.JedisFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * Created by superuser on 9/15/17.
 */
public class JedisUtil {

    private static Logger logger = LogManager.getLogger("JedisUtil");

    public static String set(String key, String value) {
        Jedis jedis = JedisFactory.getJedis();
        jedis.set(key, value);
        logger.info("set redis key-value, key=" + key + ", value=" + value);
        logger.info("=========================" + value);
        //jedis.expire(key,10);//expire in 10s
        return key;
    }

    public static String getStringValue(String key) {
        Jedis jedis = JedisFactory.getJedis();
        String value = jedis.get(key);
        logger.info("Get redis key-value, key=" + key + ", value=" + value);
        return value;
    }

}
