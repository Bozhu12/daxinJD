package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private String userName;
    private String userPhone;
    private String userPassword;
    private String userEmail;
}
