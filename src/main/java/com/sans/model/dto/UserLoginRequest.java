package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求
 * @author Sans
 */
@Data
public class UserLoginRequest implements Serializable {

    private String userAccount;

    private String userPassword;

}
