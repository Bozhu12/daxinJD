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
 * @author Sans
 * @TableName client
 */
@TableName(value ="client")
@Data
public class Client implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "client_name")
    private String clientName;

    /**
     * 
     */
    @TableField(value = "client_phone1")
    private String clientPhone1;

    /**
     * 
     */
    @TableField(value = "client_phone2")
    private String clientPhone2;

    /**
     * 
     */
    @TableField(value = "client_address")
    private String clientAddress;

    /**
     * 
     */
    @TableField(value = "client_remarks")
    private String clientRemarks;

    /**
     * 
     */
    @TableField(value = "client_create_time")
    private Date clientCreateTime;

    /**
     * 
     */
    @TableField(value = "client_update_time")
    private Date clientUpdateTime;

    /**
     * 
     */
    @TableField(value = "delete")
    @TableLogic
    private Integer delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}