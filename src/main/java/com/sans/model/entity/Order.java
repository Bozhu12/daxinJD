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
 * @TableName order
 */
@TableName(value ="`order`")
@Data
public class Order implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户id
     */
    @TableField(value = "order_client_id")
    private Long orderClientId;

    /**
     * 用户id
     */
    @TableField(value = "order_user_id")
    private Long orderUserId;

    /**
     * 实际支付
     */
    @TableField(value = "order_fact_price")
    private Double orderFactPrice;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     *  创建时间
     */
    @TableField(value = "order_create_time")
    private Date orderCreateTime;

    /**
     *  预期支付
     */
    @TableField(value = "order_expect_price")
    private Double orderExpectPrice;

    /**
     *  订单备注
     */
    @TableField(value = "order_remark")
    private String orderRemark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}