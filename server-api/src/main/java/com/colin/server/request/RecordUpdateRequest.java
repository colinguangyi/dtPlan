package com.colin.server.request;

/**
 * @author zhaolz
 */
public class RecordUpdateRequest extends BaseRequest{
    private static final long serialVersionUID = 5888812378288020149L;

    /**
     * 记录ID
     */
    private Long recordId;
    /**
     * 更新状态
     */
    private Integer state;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
