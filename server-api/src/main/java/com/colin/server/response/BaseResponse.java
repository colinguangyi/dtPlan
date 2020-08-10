package com.colin.server.response;

import com.colin.server.exception.ServerAException;
import com.colin.server.request.BaseRequest;
import com.colin.server.enums.StatusEnum;
import java.io.Serializable;

/**
 * @author zhaolz
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -2519786469457952416L;

    private static final String SUCCESS_DESC = "success";
    private static final String SYS_ERROR_DESC = "systemError";
    private static final String TIME_OUT_DESC = "服务调用超时";

    /**
     * 业务ID，每次请求唯一
     */
    private String transId;
    /**
     * 返回状态码
     */
    private String status;
    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public BaseResponse(){
    }

    public BaseResponse(String transId){
        this.transId = transId;
    }

    public void success(Object obj){
        this.status = StatusEnum.ST_200.getCode();
        this.message = SUCCESS_DESC;
        this.data = obj;
    }

    public void sysError(){
        this.status = StatusEnum.ST_500.getCode();
        this.message = SYS_ERROR_DESC;
    }

    public void busiError(ServerAException se){
        this.status = se.getCode();
        this.message = se.getMessage();
        this.data = se.getData();
    }

    public static BaseResponse fallback(BaseRequest request){
        BaseResponse response = new BaseResponse();
        response.setTransId(request.getTransId());
        response.setStatus(StatusEnum.ST_205.getCode());
        response.setMessage(TIME_OUT_DESC);
        return response;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
