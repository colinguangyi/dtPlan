package com.colin.client.util;

import com.colin.server.util.ApplicationContextUtils;
import java.util.Collections;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.SetParams;

/**
 * @author zhaolz
 */
public class JedisUtil {

    private static final JedisPool POOL = ApplicationContextUtils.getBean(JedisPool.class);

    /**
     * 字符串添加
     * @param key redis-key
     * @param value redis-value
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            jedis.set(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 字符串获取
     * @param key redis-key
     * @return 字符串
     */
    public static String get(String key)  {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.get(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 设定一个key的活动时间（s）
     * @param key key
     * @param time 活跃时间
     * @return 设置成功返回 1 ,
     * 当 key 不存在或者不能为 key 设置生存时间时返回 0 。
     */
    public static Long expire(String key, int time) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.expire(key, time);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 如果不存在名称为key的string，则向库中添加string，名称为key，值为value
     * @param key key
     * @param value value
     * @return 1成功，0失败
     */
    public static Long setnx(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.setnx(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖
     * @param key key
     * @param field field
     * @param value value
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     *         如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0
     */
    public static Long hset(String key, String field, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.hset(key, field, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * @param key key
     * @param field field
     * @param value value
     * @return 1成功，0失败
     */
    public static Long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.hsetnx(key, field, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 返回名称为key的hash中field对应的value
     * @param key key
     * @param field field
     * @return 字符串
     */
    public static String hget(String key, String field) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.hget(key, field);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 确认一个key是否存在
     * @param key key
     * @return boolean
     */
    public static Boolean exists(String key) {
        Jedis jedis = null;
        Boolean result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.exists(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 通过索引获取列表中的元素
     * @param key 列表key
     * @param index 索引值
     * @return 元素
     */
    public static String lindex(String key, Long index) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.lindex(key, index);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 将一个或多个值插入到列表头部。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误
     * @param key key
     * @param value 列表值
     * @return 列表长度
     */
    public static Long lpush(String key, String value){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.lpush(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0
     * 如果 key 不是列表类型，返回一个错误。
     * @param key key
     * @return 长度
     */
    public static Long llen(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = POOL.getResource();
            result = jedis.llen(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 发布消息到指定频道
     * @param channel 频道名称
     * @param message 消息
     * @return 接收到消息的订阅者数量
     */
    public static Long pub(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            return jedis.publish(channel, message);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 订阅-注意:多线程环境，考虑消息重复问题，尽量避免使用
     * @param sub 自定义订阅类，继承JedisPubSub来实现自己业务逻辑
     * @param channel 渠道,可以同时订阅多个渠道
     */
    public static void sub(JedisPubSub sub, String... channel) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            jedis.subscribe(sub, channel);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 释放锁，原子操作
     * @param lockKey 锁key
     * @param requestId 当前流水唯一ID
     */
    public static void releaseDistributedLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    /**
     * setnx,expire原子操作
     * 高于3.0版本使用params
     * 低版本使用
     * "OK".equals(jedis.set(key, value, "NX", "EX", seconds))
     */
    public static boolean setNxEx(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            SetParams params = new SetParams().nx().ex(seconds);
            return "OK".equals(jedis.set(key, value, params));
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * set,expire原子操作
     */
    public static String setEx(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            return jedis.setex(key.getBytes(), seconds, value.getBytes());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 原子性增加 key
     */
    public static Long incrBy(String key, long value) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            return jedis.incrBy(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 原子性增加 hash-key
     */
    public static Long hIncrBy(String key, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = POOL.getResource();
            return jedis.hincrBy(key, field, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

}
