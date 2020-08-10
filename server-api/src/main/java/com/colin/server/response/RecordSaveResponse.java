package com.colin.server.response;

import java.io.Serializable;

/**
 * @author zhaolz
 */
public class RecordSaveResponse implements Serializable {
    private static final long serialVersionUID = -8350103347822631417L;

    /**
     * 记录ID
     */
    private Long recordId;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
}
