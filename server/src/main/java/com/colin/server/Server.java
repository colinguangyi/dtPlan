package com.colin.server;

import com.colin.server.util.ApplicationContextUtils;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhaolz
 */
@SpringBootApplication
@EnableEurekaClient
@ImportResource(locations = {"classpath:/spring/*.xml"})
@MapperScan( basePackages = {"com.colin.server.mapper"})
public class Server {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Server.class, args);
        ApplicationContextUtils.setApplicationContext(context);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
