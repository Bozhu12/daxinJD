package com.sans.model.dto;


import java.io.Serializable;

/**
 * 商品编辑请求
 * @author Sans
 */
public class GoodsEditRequest implements Serializable {

    /**
     * 标题
     */
    private String goodsTitle;

    /**
     * 名称
     */
    private String goodsName;

    /**
     * 型号
     */
    private String goodsModel;

    /**
     * 商品SKU
     */
    private String goodsSku;

    /**
     * 分类id
     */
    private String goodsClassId;

    /**
     * 品牌
     */
    private String goodsBrand;

    /**
     * 单价
     */
    private String goodsPrice;

    /**
     * 最低单价
     */
    private String goodsSmallPrice;

    /**
     * 状态
     */
    private String goodsStatus;

    /**
     * 备注
     */
    private String goodsRemarks;

    /**
     * 大图
     */
    private String goodsBigLogo;

    /**
     * 小图
     */
    private String goodsSmallLogo;

}
