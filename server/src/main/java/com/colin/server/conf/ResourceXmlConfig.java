package com.colin.server.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 用作引入自定义spring上下文
 * 方便引入配置文件，三方插件等信息
 * @author zhaolz
 */
@Configuration
@ImportResource(locations = {"classpath:/spring/*.xml"})
public class ResourceXmlConfig {
}
