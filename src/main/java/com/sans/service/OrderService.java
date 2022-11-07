package com.sans.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.model.dto.OrderAddRequest;
import com.sans.model.dto.OrderAllInfoDTO;
import com.sans.model.dto.OrderInfoDTO;
import com.sans.model.dto.OrderUnitDTO;
import com.sans.model.entity.Order;

import java.util.List;

/**
 * @author Sans
 * @description 针对表【order】的数据库操作Service
 * @createDate 2022-10-08 11:53:23
 */
public interface OrderService extends IService<Order> {

    /**
     * 验证 添加点单请求
     *  <p>- 商品是否启用状态</p>
     *  <p>- 用户/顾客 是否存在</p>
     *  <p>- 购物车实付价总价 是否低于 所有商品最低价的总价</p>
     * @param request 请求数据
     * @return 返回封装数据
     */
    OrderAllInfoDTO verificationOrderAddRequest(OrderAddRequest request);

    /**
     * 创建订单
     */
    Order createOrder(OrderAllInfoDTO order);

    /**
     * 订单分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OrderUnitDTO> orderList(int pageNum, int pageSize);

    /**
     * 订单详细页
     * @param id 按id查找
     * @return
     */
    OrderInfoDTO orderInfoMsg(long id);

    /**
     * 获取测回的数据
     * @return
     */
    List<Order> orderRetreat();

    /**
     * 订单状态转换
     * @param orderId
     * @return
     */
    Order orderStatusResetById(long orderId);
}
