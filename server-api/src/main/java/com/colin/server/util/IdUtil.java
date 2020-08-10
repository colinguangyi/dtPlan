package com.colin.server.util;

import java.util.UUID;

/**
 * @author zhaolz
 */
public class IdUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
