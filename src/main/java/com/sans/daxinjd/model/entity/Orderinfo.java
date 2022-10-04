package com.sans.daxinjd.model.entity;

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
public class Orderinfo implements Serializable {


    private Long id;

    @TableField("orderinfo_order_id")
    private Long orderinfoOrderId;

    @TableField("orderinfo_goods_id")
    private Long orderinfoGoodsId;

    @TableField("orderinfo_goods_SKU")
    private String orderinfoGoodsSku;

    @TableField("orderinfo_count")
    private Integer orderinfoCount;

    @TableField("orderinfo_create_time")
    private LocalDateTime orderinfoCreateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderinfoOrderId() {
        return orderinfoOrderId;
    }

    public void setOrderinfoOrderId(Long orderinfoOrderId) {
        this.orderinfoOrderId = orderinfoOrderId;
    }

    public Long getOrderinfoGoodsId() {
        return orderinfoGoodsId;
    }

    public void setOrderinfoGoodsId(Long orderinfoGoodsId) {
        this.orderinfoGoodsId = orderinfoGoodsId;
    }

    public String getOrderinfoGoodsSku() {
        return orderinfoGoodsSku;
    }

    public void setOrderinfoGoodsSku(String orderinfoGoodsSku) {
        this.orderinfoGoodsSku = orderinfoGoodsSku;
    }

    public Integer getOrderinfoCount() {
        return orderinfoCount;
    }

    public void setOrderinfoCount(Integer orderinfoCount) {
        this.orderinfoCount = orderinfoCount;
    }

    public LocalDateTime getOrderinfoCreateTime() {
        return orderinfoCreateTime;
    }

    public void setOrderinfoCreateTime(LocalDateTime orderinfoCreateTime) {
        this.orderinfoCreateTime = orderinfoCreateTime;
    }

    @Override
    public String toString() {
        return "Orderinfo{" +
        "id=" + id +
        ", orderinfoOrderId=" + orderinfoOrderId +
        ", orderinfoGoodsId=" + orderinfoGoodsId +
        ", orderinfoGoodsSku=" + orderinfoGoodsSku +
        ", orderinfoCount=" + orderinfoCount +
        ", orderinfoCreateTime=" + orderinfoCreateTime +
        "}";
    }
}
