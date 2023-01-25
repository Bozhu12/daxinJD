package com.sans.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sans.model.dto.*;
import com.sans.model.entity.Order;
import com.sans.service.OrderService;
import com.sans.utils.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        return BaseResult.ok().putData("order", order);
    }

    @PostMapping("/list")
    public BaseResult orderList(@RequestBody OrderPageListRequest res) {
        List<OrderUnitDTO> orderList = orderService.orderList(res.getPageNum(), res.getPageSize(),false);
        return BaseResult.ok().putData("list", orderList);
    }

    @GetMapping("/info/{id}")
    public BaseResult orderInfoById(@PathVariable("id") long id) {
        OrderInfoDTO orderInfoDTO = orderService.orderInfoMsg(id);
        return BaseResult.ok().putData("data", orderInfoDTO);
    }

    @GetMapping("/count")
    public BaseResult orderCount() {
        long count = orderService.count(new QueryWrapper<Order>().ne("order_status",0));
        return BaseResult.ok().putData("count", count);
    }

    @GetMapping("/withdrawal/count")
    public BaseResult orderWithdrawalCount() {
        long count = orderService.count(new QueryWrapper<Order>().eq("order_status", 0));
        return BaseResult.ok().putData("count", count);
    }

    @PostMapping("/withdrawal/list")
    public BaseResult orderWithdrawal(@RequestBody OrderPageListRequest res) {
        List<OrderUnitDTO> orderList = orderService.orderList(res.getPageNum(), res.getPageSize(),true);
        return BaseResult.ok().putData("list", orderList);
    }

    @GetMapping("/withdrawal/edit/{id}")
    public BaseResult orderEditStatus(@PathVariable("id") long orderId) {
        Order order = orderService.orderStatusResetById(orderId);
        return BaseResult.ok().putData("data", order);
    }


}

