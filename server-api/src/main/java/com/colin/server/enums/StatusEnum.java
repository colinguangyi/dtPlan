package com.colin.server.enums;

/**
 * @author zhaolz
 */

public enum StatusEnum {
    /**成功*/
    ST_200("200"),
    /**请求处理中*/
    ST_201("201"),
    /**请求超时*/
    ST_205("205"),
    /**业务异常*/
    ST_400("400"),
    /**未登陆*/
    ST_401("401"),
    /**余额不足*/
    ST_402("402"),
    /**未实名*/
    ST_403("403"),
    /**系统异常*/
    ST_500("500");

    /**
     * 请求状态码
     */
    private String code;

    StatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
