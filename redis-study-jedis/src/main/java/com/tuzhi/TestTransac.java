package com.tuzhi;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @program: redis-study-jedis
 * @description:
 * @author: 兔子
 * @create: 2022-01-16 19:27
 **/

public class TestTransac {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("money", "100");
        jedis.set("out","0");
        //开启乐观锁
        jedis.watch("money");
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.decrBy("money",10);
            multi.incrBy("out",10);
            //执行事务
            multi.exec();
        }catch (Exception e) {
            //如果报错则放弃事务，结束事务
            multi.discard();
            e.printStackTrace();
        }finally {
            System.out.println("money: "+jedis.get("money"));
            System.out.println("out: " + jedis.get("out"));
            jedis.close();
        }
    }
}
