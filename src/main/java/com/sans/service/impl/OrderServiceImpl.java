package com.sans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sans.exception.BusinessException;
import com.sans.mapper.*;
import com.sans.model.dto.OrderAddRequest;
import com.sans.model.dto.OrderAllInfoDTO;
import com.sans.model.dto.OrderInfoDTO;
import com.sans.model.dto.OrderUnitDTO;
import com.sans.model.entity.*;
import com.sans.model.enums.StateCode;
import com.sans.service.OrderService;
import com.sans.service.OrderinfoService;
import com.sans.utils.LogMsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 *
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ClientMapper clientMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OrderinfoService orderinfoService;
    @Resource
    private OrderinfoMapper orderinfoMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public OrderAllInfoDTO verificationOrderAddRequest(OrderAddRequest request) {

        OrderAllInfoDTO order = new OrderAllInfoDTO();
        // 存储基本信息
        order.setOrderUserId(request.getUserId());
        order.setOrderClientId(request.getClientId());
        order.setOrderRemark(request.getOrderRemark());
        List<Orderinfo> orderInfoList = new ArrayList<>();

        // 用户/顾客 是否存在
        if (clientMapper.selectById(request.getClientId()) == null) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "客户不存在 , 请选择客户!");
        }
        if (usersMapper.selectById(request.getUserId()) == null) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "用户不存在 , 请添加用户!");
        }
        // 计算总价
        double totalPrice = 0.0;
        double totalPriceExpected = 0.0;
        for (OrderAddRequest.GoodsUnit goodsUnit : request.getGoodsList()) {
            // 商品是否启用状态
            Goods goods = goodsMapper.selectById(goodsUnit.getGoodsId());
            if (goods == null) {
                throw new BusinessException(StateCode.NOT_FOUND_ERROR, "商品已停止出售 [" + goodsUnit.getGoodsId() + "]");
            }
            // 商品数量必须大于1
            if (goodsUnit.getGoodsCount() <= 0) {
                throw new BusinessException(StateCode.PARAMS_ERROR, "商品数量不能少于0");
            }
            // 单个商品不能低于最低价
            double small = goods.getGoodsSmallPrice() == null ? 0.0 : goods.getGoodsSmallPrice();
            if (small > goodsUnit.getGoodsPrice()) {
                throw new BusinessException(StateCode.PARAMS_ERROR, "不能低于最低单价 [" + small + "]");
            }

            // 计算总价
            totalPrice += goodsUnit.getGoodsPrice() * goodsUnit.getGoodsCount();
            totalPriceExpected += goods.getGoodsPrice() * goodsUnit.getGoodsCount();

            // 封装数据
            Orderinfo orderinfo = new Orderinfo();
            orderinfo.setOrderinfoGoodsId(goods.getId());
            orderinfo.setOrderinfoGoodsSku(goods.getGoodsSku());
            orderinfo.setOrderinfoCount(goodsUnit.getGoodsCount());
            orderinfo.setOrderinfoGoodsPrice(goodsUnit.getGoodsPrice());
            orderInfoList.add(orderinfo);
        }
        if (totalPrice != request.getOrderPrice()) {
            throw new BusinessException(StateCode.OPERATION_ERROR, "总价价格异常!");
        }
        // 商品列表 / 实付总价 / 预计付总价
        order.setOrderinfoList(orderInfoList);
        order.setOrderFactPrice(totalPrice);
        order.setOrderExpectPrice(totalPriceExpected);
        return order;
    }

    /**
     * 订单提交
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderAllInfoDTO orderAll) {
        // 订单详细集合
        List<Orderinfo> orderInfoList = orderAll.getOrderinfoList();
        Order order = new Order();
        // 0未完成,1配送中,2完成
        order.setOrderStatus(1);
        BeanUtils.copyProperties(orderAll, order);
        boolean orderSave = this.save(order);
        if (!orderSave) {
            throw new BusinessException(StateCode.OPERATION_ERROR);
        }
        long orderId = order.getId();
        for (Orderinfo orderinfo : orderInfoList) {
            orderinfo.setOrderinfoOrderId(orderId);
        }
        boolean orderInfoSave = orderinfoService.saveBatch(orderInfoList);
        if (!orderInfoSave) {
            throw new BusinessException(StateCode.OPERATION_ERROR);
        }
        Client client = clientMapper.selectById(order.getOrderClientId());
        Users user = usersMapper.selectById(order.getOrderUserId());

        LogMsgUtils.logOutput(
                "[客户名称: " + client.getClientName() +
                        ", 店员名称: " + user.getUserName() +
                        ", 订单支付: " + order.getOrderFactPrice() +
                        ", 订单备注: " + order.getOrderRemark() + "]"
        );
        return order;
    }

    @Override
    public List<OrderUnitDTO> orderList(int pageNum, int pageSize, boolean isDel) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> qw = new QueryWrapper<>();
        // 0未完成,1配送中,2完成
        if (isDel) {
            qw.eq("order_status", 0);
        } else {
            qw.ne("order_status", 0);
        }
        Page<Order> orderPage = this.page(page, qw);

        // 集合提取数据
        List<OrderUnitDTO> list = new ArrayList<>();
        for (Order order : orderPage.getRecords()) {
            OrderUnitDTO orderUnitDTO = orderMapper.orderInfoById(order.getId());
            list.add(orderUnitDTO);
        }
        return list;
    }

    @Override
    public OrderInfoDTO orderInfoMsg(long id) {
        OrderInfoDTO orderInfoDTO = orderMapper.orderinfoMsg(id);
        List<Orderinfo> orderInfoList = orderinfoMapper.selectList(new QueryWrapper<Orderinfo>().eq("orderinfo_order_id", orderInfoDTO.getId()));
        if (orderInfoList.isEmpty())
            throw new BusinessException(StateCode.SYSTEM_ERROR, "订单可能不存在[" + orderInfoDTO.getId() + "]");

        List<Goods> goodsList = new ArrayList<>();
        for (Orderinfo orderinfo : orderInfoList) {
            Goods goods = goodsMapper.selectById(orderinfo.getOrderinfoGoodsId());
            if (goods == null)
                throw new BusinessException(StateCode.NOT_FOUND_ERROR, "商品可能已经不存在了[" + orderinfo.getOrderinfoGoodsId() + "]");
            goodsList.add(goods);
        }

        orderInfoDTO.setGoodsList(goodsList);
        orderInfoDTO.setOrderInfoList(orderInfoList);
        return orderInfoDTO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order orderStatusResetById(long orderId) {
        Order order = orderMapper.selectById(orderId);
        List<Orderinfo> orderInfoList = orderinfoMapper.selectList(new QueryWrapper<Orderinfo>().eq("orderinfo_order_id", orderId));
        // 反转操作
        int status = order.getOrderStatus() == 1 ? 0 : 1;

        order.setOrderStatus(status);
        boolean b = this.updateById(order);
        if (!b) throw new BusinessException(StateCode.SYSTEM_ERROR);
        for (Orderinfo orderinfo : orderInfoList) {
            orderinfo.setOrderinfoStatus(status);
            int i = orderinfoMapper.updateById(orderinfo);
            if (i <= 0) throw new BusinessException(StateCode.SYSTEM_ERROR);
        }
        return order;
    }
}
