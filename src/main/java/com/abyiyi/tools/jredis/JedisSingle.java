package com.abyiyi.tools.jredis;

import redis.clients.jedis.Jedis;

/**
 * Created by dongqingsong on 2018/3/27.
 */
public class JedisSingle {
    private static Jedis jedis;

    private JedisSingle(){

    }
    public static synchronized Jedis getJedisInstance(){
        if(jedis == null){
            jedis = new Jedis("localhost");
        }

        return jedis;
    }
}
