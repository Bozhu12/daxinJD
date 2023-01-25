package com.sans.model.dto;

import lombok.Data;

/**
 * 订单撤回(重定向状态)
 * @author Sans
 */
@Data
public class OrderWithdrawalRequestDTO {
    private Long orderId;
    private Long status;
}
