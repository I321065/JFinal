package com.yc.www.jfinal.service.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static com.yc.www.jfinal.service.factory.JedisFactory.RedisConfig.*;

/**
 * Created by superuser on 9/16/17.
 */
public class JedisFactory {
    private static final Logger log = LogManager.getLogger("JedisUtil");

    public static class RedisConfig {
        //Redis服务器IP
        static final String ADDR = "127.0.0.1";
        //Redis的端口号
        static final int PORT = 6379;
        //可用连接实例的最大数目，默认值为8；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        static final int MAX_ACTIVE = 1024;
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        static final int MAX_IDLE = 200;
        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        static final int MAX_WAIT = 10000;

        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        static final boolean TEST_ON_BORROW = true;

    }

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */

    static {
        init();
    }

    public static void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public static synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                log.error("No exception but no jedis pool returned");
                return null;
            }
        } catch (Exception e) {
            log.error("catch exception when get jedis object", e);
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void closeReidsPool(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
            jedisPool.close();
        }
    }
}
