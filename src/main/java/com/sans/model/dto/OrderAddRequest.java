package com.sans.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Sans
 */
@Data
public class OrderAddRequest {

    /**
     * 订单实付
     */
    private double orderPrice;
    /**
     * 客户id
     */
    private long clientId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 订单备注
     */
    private String orderRemark;
    /**
     * 商品列表
     */
    private List<GoodsUnit> goodsList;

    /**
     * 单个商品
     */
    @Data
    public static class GoodsUnit{
        /**
         * 商品id
         */
        private long goodsId;
        /**
         * 商品个数
         */
        private int goodsCount;
        /**
         * 商品单价
         */
        private double goodsPrice;
    }

}
