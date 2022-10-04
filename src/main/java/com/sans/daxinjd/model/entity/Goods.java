package com.sans.daxinjd.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Sans
 */
public class Goods implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("goods_title")
    private String goodsTitle;

    /**
     * 名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 型号
     */
    @TableField("goods_model")
    private String goodsModel;

    /**
     * 商品SKU
     */
    @TableField("goods_sku")
    private String goodsSku;

    /**
     * 分类id
     */
    @TableField("goods_class_id")
    private Long goodsClassId;

    /**
     * 分类名
     */
    @TableField("goods_class_name")
    private String goodsClassName;

    /**
     * 品牌
     */
    @TableField("goods_brand")
    private String goodsBrand;

    /**
     * 单价
     */
    @TableField("goods_price")
    private Float goodsPrice;

    /**
     * 最低价
     */
    @TableField("goods_small_price")
    private Float goodsSmallPrice;

    /**
     * 状态
     */
    @TableField("goods_status")
    private Integer goodsStatus;

    /**
     * 备注
     */
    @TableField("goods_remarks")
    private String goodsRemarks;

    /**
     * 大图url
     */
    @TableField("goods_big_logo")
    private String goodsBigLogo;

    /**
     * 小图url
     */
    @TableField("goods_small_logo")
    private String goodsSmallLogo;

    /**
     * 创建时间
     */
    @TableField("goods_create_time")
    private LocalDateTime goodsCreateTime;

    /**
     * 修改时间
     */
    @TableField("goods_update_time")
    private LocalDateTime goodsUpdateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }

    public Long getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(Long goodsClassId) {
        this.goodsClassId = goodsClassId;
    }

    public String getGoodsClassName() {
        return goodsClassName;
    }

    public void setGoodsClassName(String goodsClassName) {
        this.goodsClassName = goodsClassName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Float getGoodsSmallPrice() {
        return goodsSmallPrice;
    }

    public void setGoodsSmallPrice(Float goodsSmallPrice) {
        this.goodsSmallPrice = goodsSmallPrice;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsRemarks() {
        return goodsRemarks;
    }

    public void setGoodsRemarks(String goodsRemarks) {
        this.goodsRemarks = goodsRemarks;
    }

    public String getGoodsBigLogo() {
        return goodsBigLogo;
    }

    public void setGoodsBigLogo(String goodsBigLogo) {
        this.goodsBigLogo = goodsBigLogo;
    }

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
    }

    public LocalDateTime getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(LocalDateTime goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public LocalDateTime getGoodsUpdateTime() {
        return goodsUpdateTime;
    }

    public void setGoodsUpdateTime(LocalDateTime goodsUpdateTime) {
        this.goodsUpdateTime = goodsUpdateTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "id=" + id +
        ", goodsTitle=" + goodsTitle +
        ", goodsName=" + goodsName +
        ", goodsModel=" + goodsModel +
        ", goodsSku=" + goodsSku +
        ", goodsClassId=" + goodsClassId +
        ", goodsClassName=" + goodsClassName +
        ", goodsBrand=" + goodsBrand +
        ", goodsPrice=" + goodsPrice +
        ", goodsSmallPrice=" + goodsSmallPrice +
        ", goodsStatus=" + goodsStatus +
        ", goodsRemarks=" + goodsRemarks +
        ", goodsBigLogo=" + goodsBigLogo +
        ", goodsSmallLogo=" + goodsSmallLogo +
        ", goodsCreateTime=" + goodsCreateTime +
        ", goodsUpdateTime=" + goodsUpdateTime +
        "}";
    }
}
