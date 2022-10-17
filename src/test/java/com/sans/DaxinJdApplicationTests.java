package com.sans;

import com.sans.mapper.OrderMapper;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;
import com.sans.model.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DaxinJdApplicationTests {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    void contextLoads() {
        List<Goods> goods = goodsMapper.selectList(null);
        goods.forEach(System.out::println);
    }


    @Test
    void orderTest() {
        List<Order> orders = orderMapper.selectList(null);
        orders.forEach(System.out::println);
    }

}
