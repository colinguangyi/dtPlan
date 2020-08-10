package com.colin.server.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhaolz
 */
@Component
public class TransProperties {
    @Value("${trans.perWaitTime}")
    public Long perWaitTime;

    @Value("${trans.waitTimes}")
    public Long waitTimes;
}
