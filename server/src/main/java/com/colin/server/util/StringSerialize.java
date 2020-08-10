package com.colin.server.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhaolz
 */
public class StringSerialize {
    /**
     * 将null的字符串都改为“”
     */
    public static String nullRemove(String s){
        return s==null?"":s;
    }

    /**
     * 将null的对象都改为""。否则转为json串
     */
    public static String nullRemove(Object obj){
        return obj==null?"": JSONObject.toJSONString(obj);
    }
}
