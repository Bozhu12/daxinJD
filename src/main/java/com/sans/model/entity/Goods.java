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
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "goods_title")
    private String goodsTitle;

    /**
     * 名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 型号
     */
    @TableField(value = "goods_model")
    private String goodsModel;

    /**
     * 商品SKU
     */
    @TableField(value = "goods_sku")
    private String goodsSku;

    /**
     * 分类id
     */
    @TableField(value = "goods_class_id")
    private Long goodsClassId;

    /**
     * 分类名
     */
    @TableField(value = "goods_class_name")
    private String goodsClassName;

    /**
     * 品牌
     */
    @TableField(value = "goods_brand")
    private String goodsBrand;

    /**
     * 单价
     */
    @TableField(value = "goods_price")
    private Double goodsPrice;

    /**
     * 最低价
     */
    @TableField(value = "goods_small_price")
    private Double goodsSmallPrice;

    /**
     * 状态
     */
    @TableField(value = "goods_status")
    private Integer goodsStatus;

    /**
     * 备注
     */
    @TableField(value = "goods_remarks")
    private String goodsRemarks;

    /**
     * 大图url
     */
    @TableField(value = "goods_big_logo")
    private String goodsBigLogo;

    /**
     * 小图url
     */
    @TableField(value = "goods_small_logo")
    private String goodsSmallLogo;

    /**
     * 创建时间
     */
    @TableField(value = "goods_create_time")
    private Date goodsCreateTime;

    /**
     * 修改时间
     */
    @TableField(value = "goods_update_time")
    private Date goodsUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}