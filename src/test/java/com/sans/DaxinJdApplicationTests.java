package com.sans;

import com.sans.mapper.ClientMapper;
import com.sans.mapper.OrderMapper;
import com.sans.model.dto.ClientSpecialDTO;
import com.sans.model.entity.Client;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;
import com.sans.model.entity.Order;
import com.sans.service.ClientService;
import com.sans.utils.SortUtils;
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

    @Resource
    private ClientMapper clientMapper;

    @Resource
    private ClientService clientService;


    @Test
    void clientTest() {
        List<ClientSpecialDTO> allOrderByName = clientService.findAllOrderByName();
        System.out.println("=================");
        for (ClientSpecialDTO clientSpecialDTO : allOrderByName) {
            System.out.println(clientSpecialDTO);
        }

    }

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

    @Test
    void mainTest() {
        String spells = new SortUtils().getSpells("北京");
        System.out.println(spells);
    }

}
