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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    @TableField(value = "orderinfo_order_id")
    private Long orderinfoOrderId;

    /**
     * 商品id
     */
    @TableField(value = "orderinfo_goods_id")
    private Long orderinfoGoodsId;

    /**
     * 商品sku
     */
    @TableField(value = "orderinfo_goods_SKU")
    private String orderinfoGoodsSku;

    /**
     * 商品数量
     */
    @TableField(value = "orderinfo_count")
    private Integer orderinfoCount;

    /**
     * 创建时间
     */
    @TableField(value = "orderinfo_create_time")
    private Date orderinfoCreateTime;

    /**
     * 商品单价
     */
    @TableField(value = "orderinfo_goods_price")
    private Double orderinfoGoodsPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}