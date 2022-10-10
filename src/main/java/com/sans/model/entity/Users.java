package com.sans.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
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
    @TableField(value = "delete")
    @TableLogic
    private Integer delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}