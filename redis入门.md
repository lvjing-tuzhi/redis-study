# redis入门

## 1、key相关命令

1. 设置key和valuse：set (key) (values)
2. 得到key对应的值：get (key)
3. 查询所有的key：keys *
4. 清空数据库：flushdb
5. 判断key有没有存在：exists (key)
6. 依次key和对应的值：move (key) (dbName)
7. 设置key过期时间：expire (key) (time)
8. 查询key还有多久过期：ttl (key)
9. 追加值：append (key) (values)
10. 值自增1：incr (key)
11. 值自减1：decr (key)
12. 增加具体值：incrby (key) number
13. 减少具体值：decrby(key) number
14. 设置键值的同时设置过期时间：setex （key） （time） （value）
15. 如果键不存在则设置键值：setnx （key） （value）

## 2、五大数据类型

### 1、String（字符串）

1. 追加值：append (key) (values)
2. 值自增1：incr (key)
3. 值自减1：decr (key)
4. 增加具体值：incrby (key) （number）
5. 减少具体值：decrby(key) （number）
6. 截取子串并且得到（包头包尾）：getrange （key） （startIndex） （endIndex）
7. 替换字符串：setrange （key） （startIndex） （values）
8. 设置键值的同时设置过期时间：setex （key） （time） （value）
9. 如果键不存在则设置键值（分布式锁常用）：setnx （key） （value）
10. 批量创建键值：mset （key1） （value1） （key2） （value2）
11. 如果键都不存在则批量创建键值：msetnx （key1） （key2） 
12. 获得值的同时设置值：getset (key) (value)

### 2、List（列表）

1. 将一个值插入到列表的头部中：lpush （key） （value）
2.  将一个值插入到列表的尾部中：rpush （key） （value）
3. 取多个从头部取，栈（包头包尾）：L/range （key）（startIndex） （endIndex）
4. 移出值并且输出该值：L/Rpop （key）
5. 按下角标获取值：Lindex （key） （index）； 
6. 移除具体的指定的值：Lrem （key） （count） （value）
7. 截取改变当前值（包头包尾）：Ltrim （key）（startIndex） （endIndex）
8. 移除最后一个元素并且插入到其他列表中（剪贴）：rpopLpush （source） （destination)
9. 按下角标修改值，如果不存在则会报错：lset （key） （index） （value）
10. 在具体的值前面或者后面插入值：linsert （key） after/before （表中具体的值） （value）

### 3、Set（值不会重复）

1. 添加值：sadd （key） （value）
2. 查看所有的值：smembers （key）
3. 判断某个值在set中有没有存在：sismembers （key） （value）
4. 查询列表中有几个元素：scart （key）
5. 移除列表中具体的某一个值：srem （key） （value）
6. 随机取元素：srandmember （key） [count]
7. 移除集合中的指定 key 的一个或多个随机元素，移除后会返回移除的元素：spop (key) [count]
8. 将列表某个元素移动到另外一个列表中：smove (key) (其他列表) (value)
9. 返回两个列表的差集：sdiff (key1) (key2)
10. 返回两个列表的交集：sinter (key1) (key2)
11. 返回两个列表的并集：sunion (key1) (key2)

### 3、hash(map)

1. 存值：hset (key) (键) (value)
2. 取值：hget (key) (键)
3. 同时添加多个值：hmset (key) (键) [(value) (key) (键) (value) ...] 
4. 同时获取多个值：hmget (key) (键) [(key) (键)...]
5. 获取一个hash里面的所有键值：hgetall (key)
6. 根据键删除：hdel (key) (键)
7. 获取hash的长度：hlen (key)
8. 根据键判断hash中某一个元素存不存在：hexists (key) (键)
9. hash获取所有的的键：hkeys (key)
10. hash获取所有的值：hvals (key)
11. hash的具体键的值加法：hincrby (key) (键) (number)
12. 如果不存在则设置键值：hsetnx (key) (键) (number)

### Zset(有序集合)

1. Zset添加一个值：zadd (key) (sortNumber) (value)
2. Zset添加多个值：zadd (key) (sortNumber) (value) [(sortNumber) (value)...]
3. 查询多个值,升序(包头包尾)：zrange (key) (startIndex) (endIndex)
4. 查询多个值,降序(包头包尾)：zrerange (key) (startIndex) (endIndex)
5. 返回有序集合中指定分数区间的成员列表：ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
6. 按照具体值移除元素：zrem (key) (value)
7. 获取Zset的个数：zcard (key)
8. 计算Zset中符合区间的元素个数：zcount (key) (sortNumber) (sortNumber)

## 3、三种特殊数据类型

### 1、geospatial(地理位置)

1. 添加地理位置：geoadd (key) (纬度) (经度) (cityName)

2. 根据key和cityName获得经纬度：geopos (key) (cityName)

   > 单位(m(米),km(千米),mi(英里),ft(英尺))

3. 返回两个给定位置之间的位置：geodist (key) (cityName) (cityName) (单位)

4. 以给定的经纬度为中心，从集合中找出某一半径内的元素：georedius (key) （纬度) (经度) (半径) (单位) 

5. 找出位于指定范围内的元素，中心点时由给定的位置元素决定,中心点是由给定的位置元素决定的，而不是像georadius那样，使用输入经度和纬度来决定中心点：georadiusbymember (key) (cityName) (半径距离) (单位)

6. 查找所有的城市：zrange (key) (startIndex) (endIndex)

4. 移除某个城市: zrem (key) (cityName)

### 2、Hyperloglog(基数统计)

1. 添加：pfadd (key) (value1) (value2) ...
2. 计算个数：pfcount (key)
3. 合并两个key(并集)：pfmerge (newKey) (key1) (key2)

### 3、Bitmaps(位存储)

> 00010111 使用二进制为来记录状态，统计用户信息，活跃，不活跃，登录，没登录，阿姨开关门，打开

1. 添加值：setbit (key) (键) (0/1)
2. 根据键获取值：getbit (key) (键)
3. 统计为1的个数：bit count (key) [start end]

## 4、事务

> 1.redius单条命令保证原子性，但是redius的事物是不保证原子性。
>
> 2.redis事务本质：一组命令的集合，一个事务中的所有命令都会被序列化，在事务执行过程中，会按照顺序执行。
>
> 3.一次性、顺序性、排他性地执行一些队列的任务。

1. 使用顺序

   * 开启事务(multi)

   * 命令入队( set ... get .....)

   * 执行事务(exec)

   * ~~~bash
     127.0.0.1:6379> multi
     OK
     127.0.0.1:6379> set k1 one
     QUEUED
     127.0.0.1:6379> set k1 two
     QUEUED
     127.0.0.1:6379> set k2 three
     QUEUED
     127.0.0.1:6379> get k1
     QUEUED
     127.0.0.1:6379> exec
     1) OK
     2) OK
     3) OK
     4) "two"
     127.0.0.1:6379>
     ~~~

   * 放弃事务,取消事务：discard

2. 异常：

   * 编译型异常：代码有问题，命令有错误，编译通过不了，事务中所有命令都不会被执行。
   * 运行时异常：如果事务队列中存在语法性逻辑上的错误，那么执行命令的时候，其他命令是可以正常执行的，错误命令抛出异常。

## 5、乐观锁

1. 加锁：watch (key)

2. 解锁：unwatch

3. 使用

   ~~~bash
   127.0.0.1:6379> watch money out
   OK
   127.0.0.1:6379> multi
   OK
   127.0.0.1:6379> decr money
   QUEUED
   127.0.0.1:6379> incr out
   QUEUED
   127.0.0.1:6379> exec
   1) (integer) 99
   2) (integer) 1
   127.0.0.1:6379> unwatch
   OK
   127.0.0.1:6379> watch money out
   OK
   127.0.0.1:6379> multi
   OK
   127.0.0.1:6379>
   ~~~

## 6、Jedis使用

### 1、导入maven依赖

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2、使用

```java
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
```

## 7、Spring Boot整合redis

### 1、导入maven依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 2、编写application.properties配置

```properties
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

### 3、编写自定义配置类

> config>RedisConfig.java

```java
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //Json配置序列化
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(om);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();


        return template;
    }
}
```

### 4、编写工具类

> util>RedisUtil

```java
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // =============================common============================
    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }


    // ============================String=============================

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    // ================================Map=================================

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

}
```



### 5、使用

```java
@Autowired
private RedisTemplate redisTemplate;

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
    value.set("name","吕竟");
    System.out.println(value.get("name"));
}
```

## 8、Redis持久化

### 1、RDB触发机制(Redis默认是使用RDB)

> Redis会单独创建（fork）一个子进程来进行持久化，会先将数据写入到一个临时文件中，待数据持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。整个过程，主线程是不进行任何IO操作的。这就确保了极高的性能。如果需要进行大规模数据都恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。RDB的缺点是最后一次持久化可能会丢失。

1. 配置文件中save的规则满足的情况下，会自动触发rdb规则。
2. 执行flushall命令，也会触发rdb规则。
3. 退出redis，也会产生rdb文件。

### 2、AOF（Append Only File）

> 默认关闭，要到配置文件中开启。appendonly yes
>
> 以日志的形式来记录每个写操作，将Redis执行过的所有指令记录下来（读操作不记录），只许追加文件但不可以改写文件，Redis启动的时候会读取该文件重新构建数据，Redis重启的时候就根据日志文件袋内容将写指令从前到后执行一次以完成数据都恢复工作。

1. 优点：
   * 每一次修改都同步，文件袋完整会更加好。
   * 每秒同步一次，可能会丢失一秒的数据。
   * 从不同步，效率最高！
2. 缺点：
   * aof文件会远大于rdb文件。
   * aof运行效率比rdb慢。

### 3、同时开启两种持久化方式

1. 在这种情况下，当redis重启时会优先载入AOF文件来恢复原始的数据，因为在通常情况下AOF文件保存的数据集要比rdb文件保存的数据集要完整。
2. rdb的数据不实时，同时使用两者时服务器重启时也只会找aof文件，

## 9、发布订阅

1. 订阅：subscribe (channel)
2. 发布消息：publish (channel) (message)

## 10、主从复制

> 读写分离，主机处理写请求，从机处理读请求，一般为一主二从

### 1、使用主从复制的原因

1. 从结构上，单个Redis服务器发送单点故障，并且一台服务器需要处理所有的请求负载，压力较大。
2. 从容量上，单个Redis服务器内存容量有限，就算一台Redis服务器内存容量为256G，也不能将所有的内存用作Redis存储内存，一般来说，单台Redis最大使用内存不应该超过20G。

### 2、主从复制的作用

1. 数据冗余：主从复制实现了数据的热备份，是持久化之外的一种数据冗余方式。
2. 故障恢复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复；实际上是一种服务的冗余。
3. 负载均衡：在主从复制的基础上，配合读写分离，可以由主节点提供写服务，由从节点提供读服务（即写Redis数据时应用连接主节点。读Redis数据时应用连接从节点），分担服务器负载；尤其是在写少读多的场景下，通过多个从节点分担读负载，可以大大提高Redis服务器的并发量。
4. 高可用（集群）：除了上述作用以外，主从复制还是哨兵和集群能够实施的基础，因此说主从复制时Redis高可用的基础。

### 3、复制的原理

1. Slave启动成功连接到master后会发送一个sync同步命令，Master接到命令，启动后台的存盘进程，同时收集所有接收到的用户修改数据集命令，在后台进程执行完毕之后，master将传送整个文件到slave，并完成一次完全同步。
2. 全量复制：Slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
3. 增量复制：Master继续将新的所有收集到的修改命令一次传给slave，完成同步。
4. 只要时重新连接master，一次完全同步（全量复制）将被自动执行！我们的数据一定可以从从机中看到。

### 4、配置使用

> 默认每一台都是主机。
>
> 查看当前状态命令：info replication

#### 1、命令行方式(暂时的，关闭就会失效)

1. 主机变为从机命令：slaveof (ip) (port)

2. 从机变为主机：slaveof no one

#### 2、配置文件redis.conf(永久的)

1. 配置里面的slaveof <masterip> <masterport>

## 11、哨兵模式(Sentinel)

> 当主机宕机了，自动设置一个从机为主机,哨兵是一个独立的进程，作为进程，它会独立运行。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行多个Redis实例。

![img](D:\项目\redis\redis入门.assets\webp.webp)



 ### 1、作用

1. 通过发送命令，让Redis服务器返回监控其运行状态，包括主服务器和从服务器。

2. 当哨兵监测到master宕机，会自动将slave切换成master，然后通过发布订阅模式通知其他的从服务器，修改配置文件，让它们切换主机。

3. 还可以设置多个哨兵进行互相监控，形成多哨兵模式。

   ![img](D:\项目\redis\redis入门.assets\webp-16424757285972.webp)

### 2、原理

1. 假设主服务器宕机，哨兵1先监测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象成为主观下线。当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间会进行一次投票1，投票的结果由一个哨兵发起，进行failover[故障转移]操作。切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为客观下线。
2. 当哨兵已经切换了一个主机的时候，而之前宕机的主机又恢复正常了，则这个宕机主机会成为从机。

### 3、使用

#### 1、配置

1. sentinel.conf

   sentinel monitor (哨兵名字) (ip) (port) (1:1为开启投票切换主机模式)

#### 2、启动

开启redis-sentinel

#### 1、完整的哨兵模式配置文件

~~~bash
# Example sentinel.conf
 
# 哨兵sentinel实例运行的端口 默认26379
port 26379
 
# 哨兵sentinel的工作目录
dir /tmp
 
# 哨兵sentinel监控的redis主节点的 ip port 
# master-name  可以自己命名的主节点名字 只能由字母A-z、数字0-9 、这三个字符".-_"组成。
# quorum 当这些quorum个数sentinel哨兵认为master主节点失联 那么这时 客观上认为主节点失联了
# sentinel monitor <master-name> <ip> <redis-port> <quorum>
sentinel monitor mymaster 127.0.0.1 6379 1
 
# 当在Redis实例中开启了requirepass foobared 授权密码 这样所有连接Redis实例的客户端都要提供密码
# 设置哨兵sentinel 连接主从的密码 注意必须为主从设置一样的验证密码
# sentinel auth-pass <master-name> <password>
sentinel auth-pass mymaster MySUPER--secret-0123passw0rd
 
 
# 指定多少毫秒之后 主节点没有应答哨兵sentinel 此时 哨兵主观上认为主节点下线 默认30秒
# sentinel down-after-milliseconds <master-name> <milliseconds>
sentinel down-after-milliseconds mymaster 30000
 
# 这个配置项指定了在发生failover主备切换时最多可以有多少个slave同时对新的master进行 同步，
这个数字越小，完成failover所需的时间就越长，
但是如果这个数字越大，就意味着越 多的slave因为replication而不可用。
可以通过将这个值设为 1 来保证每次只有一个slave 处于不能处理命令请求的状态。
# sentinel parallel-syncs <master-name> <numslaves>
sentinel parallel-syncs mymaster 1
 
 
 
# 故障转移的超时时间 failover-timeout 可以用在以下这些方面： 
#1. 同一个sentinel对同一个master两次failover之间的间隔时间。
#2. 当一个slave从一个错误的master那里同步数据开始计算时间。直到slave被纠正为向正确的master那里同步数据时。
#3.当想要取消一个正在进行的failover所需要的时间。  
#4.当进行failover时，配置所有slaves指向新的master所需的最大时间。不过，即使过了这个超时，slaves依然会被正确配置为指向master，但是就不按parallel-syncs所配置的规则来了
# 默认三分钟
# sentinel failover-timeout <master-name> <milliseconds>
sentinel failover-timeout mymaster 180000
 
# SCRIPTS EXECUTION
 
#配置当某一事件发生时所需要执行的脚本，可以通过脚本来通知管理员，例如当系统运行不正常时发邮件通知相关人员。
#对于脚本的运行结果有以下规则：
#若脚本执行后返回1，那么该脚本稍后将会被再次执行，重复次数目前默认为10
#若脚本执行后返回2，或者比2更高的一个返回值，脚本将不会重复执行。
#如果脚本在执行过程中由于收到系统中断信号被终止了，则同返回值为1时的行为相同。
#一个脚本的最大执行时间为60s，如果超过这个时间，脚本将会被一个SIGKILL信号终止，之后重新执行。
 
#通知型脚本:当sentinel有任何警告级别的事件发生时（比如说redis实例的主观失效和客观失效等等），将会去调用这个脚本，
#这时这个脚本应该通过邮件，SMS等方式去通知系统管理员关于系统不正常运行的信息。调用该脚本时，将传给脚本两个参数，
#一个是事件的类型，
#一个是事件的描述。
#如果sentinel.conf配置文件中配置了这个脚本路径，那么必须保证这个脚本存在于这个路径，并且是可执行的，否则sentinel无法正常启动成功。
#通知脚本
# sentinel notification-script <master-name> <script-path>
  sentinel notification-script mymaster /var/redis/notify.sh
 
# 客户端重新配置主节点参数脚本
# 当一个master由于failover而发生改变时，这个脚本将会被调用，通知相关的客户端关于master地址已经发生改变的信息。
# 以下参数将会在调用脚本时传给脚本:
# <master-name> <role> <state> <from-ip> <from-port> <to-ip> <to-port>
# 目前<state>总是“failover”,
# <role>是“leader”或者“observer”中的一个。 
# 参数 from-ip, from-port, to-ip, to-port是用来和旧的master和新的master(即旧的slave)通信的
# 这个脚本应该是通用的，能被多次调用，不是针对性的。
# sentinel client-reconfig-script <master-name> <script-path>
sentinel client-reconfig-script mymaster /var/redis/reconfig.sh


~~~

## 12、Redis缓存穿透和雪崩

### 1、缓存穿透

1. 在默认情况下，用户请求数据时，会先在缓存(Redis)中查找，若没找到即缓存未命中，再在数据库中进行查找，数量少可能问题不大，可是一旦大量的请求数据（例如秒杀场景）缓存都没有命中的话，就会全部转移到数据库上，造成数据库极大的压力，就有可能导致数据库崩溃。网络安全中也有人恶意使用这种手段进行攻击被称为洪水攻击。

2. 解决方案1：布隆过滤器，对所有可能查询的参数以Hash的形式存储，以便快速确定是否存在这个值，在控制层先进行拦截校验，校验不通过直接打回，减轻了存储系统的压力。

   ![在这里插入图片描述](D:\项目\redis\redis入门.assets\watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80Mzg3MzIyNw==,size_16,color_FFFFFF,t_70.jpeg)

3. 解决方案2：缓存空对象，一次请求若在缓存和数据库中都没找到，就在缓存中方一个空对象用于处理后续这个请求。

   ![img](D:\项目\redis\redis入门.assets\watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80Mzg3MzIyNw==,size_16,color_FFFFFF,t_70-16424803769725.jpeg)

 这样做有一个缺陷：存储空对象也需要空间，大量的空对象会耗费一定的空间，存储效率并不高。解决这个缺陷的方式就是设置较短过期时间

即使对空值设置了过期时间，还是会存在缓存层和存储层的数据会有一段时间窗口的不一致，这对于需要保持一致性的业务会有影响。

### 2、缓存击穿(量太大，缓存过期)

1. 相较于缓存穿透，缓存击穿的目的性更强，一个存在的key，在缓存过期的一刻，同时有大量的请求，这些请求都会击穿到DB，造成瞬时DB请求量大、压力骤增。这就是缓存被击穿，只是针对其中某个key的缓存不可用而导致击穿，但是其他的key依然可以使用缓存响应。

    比如热搜排行上，一个热点新闻被同时大量访问就可能导致缓存击穿。

2. 解决方案1：设置热点数据永不过期，这样就不会出现热点数据过期的情况，但是当Redis内存空间满的时候也会清理部分数据，而且此种方案会占用空间，一旦热点数据多了起来，就会占用部分空间。
3. 解决方案2：加互斥锁(分布式锁)，在访问key之前，采用SETNX（set if not exists）来设置另一个短期key来锁住当前key的访问，访问结束再删除该短期key。保证同时刻只有一个线程访问。这样对锁的要求就十分高。

### 3、缓存雪崩

1. 大量的key设置了相同的过期时间，导致在缓存在同一时刻全部失效，造成瞬时DB请求量大、压力骤增，引起雪崩。

2. redis高可用

   这个思想的含义是，既然redis有可能挂掉，那我多增设几台redis，这样一台挂掉之后其他的还可以继续工作，其实就是搭建的集群

3. 限流降级

   这个解决方案的思想是，在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许一个线程查询数据和写缓存，其他线程等待。

4. 数据预热

   数据加热的含义就是在正式部署之前，我先把可能的数据先预先访问一遍，这样部分可能大量访问的数据就会加载到缓存中。在即将发生大并发访问前手动触发加载缓存不同的key，设置不同的过期时间，让缓存失效的时间点尽量均匀。















