package com.sans.daxinjd.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "order_client_id")
    private Long orderClientId;

    /**
     * 
     */
    @TableField(value = "order_user_id")
    private Long orderUserId;

    /**
     * 
     */
    @TableField(value = "order_fact_price")
    private Double orderFactPrice;

    /**
     * 
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     * 
     */
    @TableField(value = "order_create_time")
    private Date orderCreateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}