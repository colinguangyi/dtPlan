package com.colin.server.request;

/**
 * @author zhaolz
 */
public class RecordSaveRequest extends BaseRequest{
    private static final long serialVersionUID = 3372904617367896485L;

    /**
     * 广告ID
     */
    private String addId;
    /**
     * 平台ID
     */
    private Long platformId;
    /**
     * 店铺ID
     */
    private Long slwId;
    /**
     * 用户ID
     */
    private Long numberId;

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getSlwId() {
        return slwId;
    }

    public void setSlwId(Long slwId) {
        this.slwId = slwId;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }
}
