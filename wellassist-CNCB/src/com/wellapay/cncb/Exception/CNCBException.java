package com.wellapay.cncb.Exception;

/**
 * Created by Administrator on 2017/9/4.
 */
public class CNCBException extends RuntimeException{

    private String code;
    private String message;

    public CNCBException(String message){
        super(message);
        this.message=message;
    }

    public CNCBException(String code,String message){
        super();
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
