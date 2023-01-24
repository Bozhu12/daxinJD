package com.sans.utils;

import com.sans.model.enums.StateCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 * @author Sans
 */
public class BaseResult {


    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> response = new HashMap<>();

    private BaseResult() {}
    private BaseResult(StateCode stateCode) {
        this.code = stateCode.getCode();
        this.message = stateCode.getMessage();
    }

    public static BaseResult ok() {
        return new BaseResult(StateCode.SUCCESS)
                .setSuccess(true);
    }

    public static BaseResult error(StateCode stateCode) {
        return new BaseResult()
                .setSuccess(false)
                .setCode(stateCode.getCode())
                .setMessage(stateCode.getMessage());
    }

    public static BaseResult error(StateCode stateCode , String message) {
        return new BaseResult()
                .setSuccess(false)
                .setCode(stateCode.getCode())
                .setMessage(message);
    }

    public static BaseResult error(int code , String message) {
        return new BaseResult()
                .setSuccess(false)
                .setCode(code)
                .setMessage(message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public BaseResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public BaseResult setData(Map<String, Object> data) {
        this.response = data;
        return this;
    }

    public BaseResult putData(String key, Object value) {
        this.response.put(key, value);
        return this;
    }

}
