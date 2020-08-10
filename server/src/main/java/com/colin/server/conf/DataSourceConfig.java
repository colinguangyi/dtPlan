package com.colin.server.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.colin.server.properties.DataSourceProperties;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhaolz
 */
@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Resource
    private DataSourceProperties dataSourceProperties;

    /**
     * ·@Primary·表示在同样的DataSource中，首先使用被标注的DataSource
     */
    @Bean
    @Primary
    public DruidDataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.dbUrl);
        datasource.setUsername(dataSourceProperties.username);
        datasource.setPassword(dataSourceProperties.password);
        datasource.setDriverClassName(dataSourceProperties.driverClassName);

        //configuration
        datasource.setInitialSize(dataSourceProperties.initialSize);
        datasource.setMinIdle(dataSourceProperties.minIdle);
        datasource.setMaxActive(dataSourceProperties.maxActive);
        datasource.setMaxWait(dataSourceProperties.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(dataSourceProperties.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(dataSourceProperties.validationQuery);
        datasource.setTestWhileIdle(dataSourceProperties.testWhileIdle);
        datasource.setTestOnBorrow(dataSourceProperties.testOnBorrow);
        datasource.setTestOnReturn(dataSourceProperties.testOnReturn);
        datasource.setPoolPreparedStatements(dataSourceProperties.poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.maxPoolPreparedStatementPerConnectionSize);
        datasource.setUseGlobalDataSourceStat(dataSourceProperties.useGlobalDataSourceStat);
        try {
            datasource.setFilters(dataSourceProperties.filters);
        } catch (Exception e) {
            logger.error("druid configuration initialization filter: "+ e.getMessage(), e);
        }
        datasource.setConnectionProperties(dataSourceProperties.connectionProperties);
        return datasource;
    }
}
