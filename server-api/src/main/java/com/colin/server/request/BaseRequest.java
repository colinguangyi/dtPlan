package com.colin.server.request;

import java.io.Serializable;

/**
 * @author zhaolz
 */
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 4920050438183453558L;

    /**
     * 当前操作业务ID，每次请求唯一
     */
    private String transId;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 关联事务ID，异步事务处理时使用
     */
    private String relateTransIds;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRelateTransIds() {
        return relateTransIds;
    }

    public void setRelateTransIds(String relateTransIds) {
        this.relateTransIds = relateTransIds;
    }
}
