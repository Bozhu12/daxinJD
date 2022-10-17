package com.sans.model.dto;

import com.sans.model.entity.Order;
import com.sans.model.entity.Orderinfo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单所有详情信息
 * @author Sans
 */
public class OrderAllInfoDTO extends Order implements Serializable {

    private List<Orderinfo> orderinfoList;

    public List<Orderinfo> getOrderinfoList() {
        return orderinfoList;
    }

    public OrderAllInfoDTO setOrderinfoList(List<Orderinfo> orderinfoList) {
        this.orderinfoList = orderinfoList;
        return this;
    }
}
