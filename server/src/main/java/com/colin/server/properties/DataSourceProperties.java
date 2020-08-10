package com.colin.server.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhaolz
 */
@Component
public class DataSourceProperties {
    @Value("${datasource.url}")
    public String dbUrl;
    @Value("${datasource.username}")
    public String username;
    @Value("${datasource.password}")
    public String password;
    @Value("${datasource.driverClassName}")
    public String driverClassName;
    @Value("${datasource.initialSize}")
    public int initialSize;
    @Value("${datasource.minIdle}")
    public int minIdle;
    @Value("${datasource.maxActive}")
    public int maxActive;
    @Value("${datasource.maxWait}")
    public int maxWait;
    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    public int timeBetweenEvictionRunsMillis;
    @Value("${datasource.minEvictableIdleTimeMillis}")
    public int minEvictableIdleTimeMillis;
    @Value("${datasource.validationQuery}")
    public String validationQuery;
    @Value("${datasource.testWhileIdle}")
    public boolean testWhileIdle;
    @Value("${datasource.testOnBorrow}")
    public boolean testOnBorrow;
    @Value("${datasource.testOnReturn}")
    public boolean testOnReturn;
    @Value("${datasource.poolPreparedStatements}")
    public boolean poolPreparedStatements;
    @Value("${datasource.maxPoolPreparedStatementPerConnectionSize}")
    public int maxPoolPreparedStatementPerConnectionSize;
    @Value("${datasource.filters}")
    public String filters;
    @Value("${datasource.connectionProperties}")
    public String connectionProperties;
    @Value("${datasource.useGlobalDataSourceStat}")
    public boolean useGlobalDataSourceStat;
}
