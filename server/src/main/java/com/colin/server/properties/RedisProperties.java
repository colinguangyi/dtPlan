package com.colin.server.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhaolz
 */
@Component
public class RedisProperties {
    @Value("${redis.host}")
    public String host;

    @Value("${redis.port}")
    public Integer port;

    @Value("${redis.password}")
    public String password;

    @Value("${redis.pool.max-active}")
    public Integer maxActive;

    @Value("${redis.pool.max-wait}")
    public Long maxWait;

    @Value("${redis.pool.max-idle}")
    public Integer maxIdle;

    @Value("${redis.pool.min-idle}")
    public Integer minIdle;

    @Value("${redis.timeout}")
    public Integer timeout;

    @Value("${redis.testOnBorrow}")
    public Boolean testOnBorrow;
}
