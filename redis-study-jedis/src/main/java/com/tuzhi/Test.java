package com.tuzhi;

import redis.clients.jedis.Jedis;

/**
 * @program: redius-study-jedis
 * @description:
 * @author: 兔子
 * @create: 2022-01-16 19:14
 **/

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
    }
}
