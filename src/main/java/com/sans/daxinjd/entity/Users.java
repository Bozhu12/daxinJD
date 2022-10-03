package com.sans.daxinjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sans
 * 
 */
public class Users implements Serializable {


    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("user_true_name")
    private String userTrueName;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_root")
    private Integer userRoot;

    @TableField("user_create_time")
    private LocalDateTime userCreateTime;

    @TableField("user_update_time")
    private LocalDateTime userUpdateTime;

    private Integer delete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserRoot() {
        return userRoot;
    }

    public void setUserRoot(Integer userRoot) {
        this.userRoot = userRoot;
    }

    public LocalDateTime getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(LocalDateTime userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public LocalDateTime getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(LocalDateTime userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Users{" +
        "id=" + id +
        ", userName=" + userName +
        ", userTrueName=" + userTrueName +
        ", userPhone=" + userPhone +
        ", userRoot=" + userRoot +
        ", userCreateTime=" + userCreateTime +
        ", userUpdateTime=" + userUpdateTime +
        ", delete=" + delete +
        "}";
    }
}
