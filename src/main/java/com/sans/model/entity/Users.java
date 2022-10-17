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
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "user_true_name")
    private String userTrueName;

    /**
     * 
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 
     */
    @TableField(value = "user_root")
    private Integer userRoot;

    /**
     * 
     */
    @TableField(value = "user_create_time")
    private Date userCreateTime;

    /**
     * 
     */
    @TableField(value = "user_update_time")
    private Date userUpdateTime;

    /**
     * 
     */
    @TableField(value = "`delete`")
    @TableLogic
    private Integer delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}