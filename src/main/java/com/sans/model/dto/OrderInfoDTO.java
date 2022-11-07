package com.sans.model.dto;

import com.sans.model.entity.Goods;
import com.sans.model.entity.Orderinfo;
import lombok.Data;

import java.util.List;

/**
 * @author Sans
 */
@Data
public class OrderInfoDTO {

    private int id;
    private String remark;
    private int status;
    private String createTime;
    private double price;
    private String userName;
    private String clientName;
    private String clientAddress;
    private String clientPhone1;
    private String clientPhone2;

    private List<Orderinfo> orderInfoList;
    private List<Goods> goodsList;
}
