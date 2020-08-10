package com.colin.server.util;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhaolz
 */
public class ApplicationContextUtils {
    private static ApplicationContext applicationContext = null;

    /**
     * 保证spring初始化中能够调用此方法，将ApplicationContext进行复制
     * @param applicationContext spring上下文
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        if(ApplicationContextUtils.applicationContext == null){
            ApplicationContextUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextUtils.applicationContext;
    }

    public static DefaultListableBeanFactory getDefaultListableBeanFactory() {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }

    public static boolean containsBean(String className) {
        return applicationContext.containsBean(className);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T)applicationContext.getBean(beanName);
    }
    
    public static <T> T getBean(Class<T> requiredType) {
    	return applicationContext.getBean(requiredType);
    }

}
