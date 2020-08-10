package com.colin.server.response;

import java.io.Serializable;

/**
 * @author zhaolz
 */
public class RecordUpdateResponse implements Serializable {
    private static final long serialVersionUID = -3771131005328761938L;

    /**
     * 影响行数
     */
    private Integer raws;

    public Integer getRaws() {
        return raws;
    }

    public void setRaws(Integer raws) {
        this.raws = raws;
    }
}
