package com.sans.controller;


import com.sans.model.dto.OrderAddRequest;
import com.sans.model.dto.OrderAllInfoDTO;
import com.sans.model.entity.Order;
import com.sans.service.OrderService;
import com.sans.utils.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sans
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public BaseResult createOrder(@RequestBody OrderAddRequest request) {
        // 验证参数 (异常会自动抛出)
        OrderAllInfoDTO orderAllInfoDTO = orderService.verificationOrderAddRequest(request);
        // 创建订单
        Order order = orderService.createOrder(orderAllInfoDTO);


        return BaseResult.ok();
    }

}

