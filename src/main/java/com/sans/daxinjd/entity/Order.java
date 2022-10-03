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
public class Order implements Serializable {


    private Long id;

    @TableField("order_client_id")
    private Long orderClientId;

    @TableField("order_user_id")
    private Long orderUserId;

    @TableField("order_fact_price")
    private Float orderFactPrice;

    @TableField("order_status")
    private Integer orderStatus;

    @TableField("order_create_time")
    private LocalDateTime orderCreateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderClientId() {
        return orderClientId;
    }

    public void setOrderClientId(Long orderClientId) {
        this.orderClientId = orderClientId;
    }

    public Long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Float getOrderFactPrice() {
        return orderFactPrice;
    }

    public void setOrderFactPrice(Float orderFactPrice) {
        this.orderFactPrice = orderFactPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(LocalDateTime orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", orderClientId=" + orderClientId +
        ", orderUserId=" + orderUserId +
        ", orderFactPrice=" + orderFactPrice +
        ", orderStatus=" + orderStatus +
        ", orderCreateTime=" + orderCreateTime +
        "}";
    }
}
