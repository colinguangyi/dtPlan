package com.colin.client;

import com.colin.server.util.ApplicationContextUtils;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhaolz
 */
@SpringBootApplication(scanBasePackages = {"com.colin"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.colin")
@ImportResource(locations = {"classpath:/spring/*.xml"})
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Client.class, args);
        ApplicationContextUtils.setApplicationContext(context);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
