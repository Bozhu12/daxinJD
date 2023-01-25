package com.sans.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Sans
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 真实姓名
     */
    @TableField(value = "user_true_name")
    private String userTrueName;

    /**
     *  手机号
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     *  是否为管理员
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     *  创建时间
     */
    @TableField(value = "user_create_time")
    private Date userCreateTime;

    /**
     *  头像
     */
    @TableField(value = "user_avatar")
    private Date userAvatar;

    /**
     *  修改时间
     */
    @TableField(value = "user_update_time")
    private Date userUpdateTime;

    /**
     * 删除?
     */
    @TableField(value = "`delete`")
    @TableLogic
    private Integer delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}