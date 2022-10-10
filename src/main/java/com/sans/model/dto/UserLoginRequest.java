package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求
 * @author Sans
 */
@Data
public class UserLoginRequest implements Serializable {

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 用户密文
     */
    private String code;

}
