package com.colin.client.conf;

import com.colin.client.properties.RedisProperties;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 * @author zhaolz
 */
@Configuration
public class RedisConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public JedisPool redisPoolFactory() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(redisProperties.minIdle);
        jedisPoolConfig.setMaxIdle(redisProperties.maxIdle);
        jedisPoolConfig.setMaxTotal(redisProperties.maxActive);
        jedisPoolConfig.setMaxWaitMillis(redisProperties.maxWait);
        jedisPoolConfig.setTestOnBorrow(redisProperties.testOnBorrow);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.host, redisProperties.port, redisProperties.timeout, redisProperties.password);

        logger.info("JedisPool注入成功！！host:" + redisProperties.host);

        return jedisPool;
    }

}
