package com.sans.daxinjd.exception;

import com.sans.daxinjd.model.enums.StateCode;

/**
 * 自定义 业务异常
 * @author Sans
 */
public class BusinessException extends RuntimeException{

    private final int code;

    public BusinessException(StateCode stateCode) {
        super(stateCode.getMessage());
        this.code = stateCode.getCode();
    }

    public BusinessException(StateCode stateCod , String msg) {
        super(msg);
        this.code = stateCod.getCode();
    }

    public int getCode() {
        return code;
    }
}
