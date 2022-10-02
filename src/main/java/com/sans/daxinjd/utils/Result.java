package com.sans.daxinjd.utils;

import java.util.HashMap;
import java.util.Map;

// 统一返回结果
public class Result {


    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    // 成功
    public static Result ok() {
        return new Result()
                .setSuccess(true)
                .setCode(ResultCode.SUCCESS)
                .setMessage("ok");
    }

    // 失败
    public static Result error() {
        return new Result()
                .setSuccess(false)
                .setCode(ResultCode.ERROR)
                .setMessage("error");
    }

    public Boolean getSuccess() {
        return success;
    }

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Result setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public Result putData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
