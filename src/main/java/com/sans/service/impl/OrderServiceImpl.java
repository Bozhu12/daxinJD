package com.sans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.sans.exception.BusinessException;
import com.sans.mapper.*;
import com.sans.model.dto.OrderAddRequest;
import com.sans.model.dto.OrderAllInfoDTO;
import com.sans.model.entity.Client;
import com.sans.model.entity.Goods;
import com.sans.model.entity.Order;
import com.sans.model.entity.Orderinfo;
import com.sans.model.enums.StateCode;
import com.sans.service.OrderService;
import com.sans.service.OrderinfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ClientMapper clientMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OrderinfoService orderinfoService;

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
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "客户不存在 , 请重新登录!");
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
            totalPrice = goodsUnit.getGoodsPrice() * goodsUnit.getGoodsCount();
            totalPriceExpected = goods.getGoodsPrice() * goodsUnit.getGoodsCount();

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderAllInfoDTO orderAll) {
        List<Orderinfo> orderInfoList = orderAll.getOrderinfoList();
        Order order = new Order();
        BeanUtils.copyProperties(orderAll , order);
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
        return order;
    }
}
