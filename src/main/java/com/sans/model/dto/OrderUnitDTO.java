package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderUnitDTO implements Serializable {

    private long id;
    private String clientName;
    private String userName;
    private double price;
    private String createTime;
    private String title;
    private int goodsCount;

}
