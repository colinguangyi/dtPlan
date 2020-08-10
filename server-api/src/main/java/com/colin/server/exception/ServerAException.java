package com.colin.server.exception;

import com.colin.server.enums.StatusEnum;

/**
 * @author zhaolz
 */
public class ServerAException extends Exception {
    private static final long serialVersionUID = 3054436338892867464L;

    private String code;
    private String message;
    private Object data;

    public ServerAException(){
        super();
    }

    public ServerAException(String message) {
        super(message);
        this.code = StatusEnum.ST_400.name();
        this.message = message;
    }

    public ServerAException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServerAException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
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
