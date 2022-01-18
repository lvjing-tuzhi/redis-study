package com.tuzhi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuzhi.pojo.User;
import com.tuzhi.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        /**
         * redisTemplate 操作不同的数据类型，api和redis指令一样
         * opsForValue 操作字符串，String
         * opsForList 操作list，list
         * opsForSet 操作set，set
         * opsForHash 操作hash，hash
         * opsForZset 操作Zset
         * opsForGeo 操作geospatial
         * opsForHyperLogLog
         * opsForValue().setBit() 操作Bitmaps
         */
        ValueOperations value = redisTemplate.opsForValue();
        value.set("name1","吕竟");
        System.out.println(value.get("name1"));
    }

    @Test
    void test1() throws JsonProcessingException {
        User user = new User("admin", "admin");
        String s = new ObjectMapper().writeValueAsString(user);
        redisUtil.set("user1",s);
        redisUtil.get("user1");
    }

}
