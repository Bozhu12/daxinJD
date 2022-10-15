package com.sans.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Sans
 * @TableName orderinfo
 */
@TableName(value ="orderinfo")
@Data
public class Orderinfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "orderinfo_order_id")
    private Long orderinfoOrderId;

    /**
     * 
     */
    @TableField(value = "orderinfo_goods_id")
    private Long orderinfoGoodsId;

    /**
     * 
     */
    @TableField(value = "orderinfo_goods_SKU")
    private String orderinfoGoodsSku;

    /**
     * 
     */
    @TableField(value = "orderinfo_count")
    private Integer orderinfoCount;

    /**
     * 
     */
    @TableField(value = "orderinfo_create_time")
    private Date orderinfoCreateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}