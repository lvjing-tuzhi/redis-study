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

## 6、Jedis

