package com.colin.server.util;

/**
 * @author zhaolz
 */
public class AsyncTransConstants {
    /**
     * 事务标识时效（秒）
     */
    public static final Integer EXPIRE_TIME = 10;
    /**
     * 异步事务key保存在redis中的值
     * ready:处理进行中
     */
    public static final String TRANS_READY = "ready";
    /**
     * 异步事务key保存在redis中的值
     * done:处理完成
     */
    public static final String TRANS_DONE = "done";
    /**
     * 异步事务key保存在redis中的值
     * fail:处理失败
     */
    public static final String TRANS_FAIL = "fail";
}
