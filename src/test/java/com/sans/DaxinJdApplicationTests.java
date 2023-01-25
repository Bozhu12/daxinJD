package com.sans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sans.mapper.ClientMapper;
import com.sans.mapper.OrderMapper;
import com.sans.mapper.UsersMapper;
import com.sans.model.dto.ClientSpecialDTO;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;
import com.sans.model.entity.Order;
import com.sans.model.entity.Users;
import com.sans.service.ClientService;
import com.sans.service.GoodsService;
import com.sans.service.OrderService;
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
    private OrderService orderService;

    @Resource
    private ClientMapper clientMapper;

    @Resource
    private ClientService clientService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UsersMapper usersMapper;

    @Test
    void userMapperTest() {
        List<Users> users = usersMapper.selectList(null);
        for (Users user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    void clientTest() throws JsonProcessingException {
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
        //OrderUnitDTO orders = orderMapper.orderInfoById(14);
        //System.out.println("orders = " + orders);
        //OrderInfoDTO orderInfoDTO = orderMapper.orderinfoMsg(14);
        //System.out.println("orderInfoDTO = " + orderInfoDTO);
        //orders.forEach(System.out::println);

        Order order = orderService.orderStatusResetById(14);
        System.out.println("order = " + order);

    }

    @Test
    void orderTest2() {
        //List<Order> orders = orderService.orderRetreat();
        //orders.forEach(System.out::println);
    }

    @Test
    void mainTest() {
        String spells = new SortUtils().getSpells("北京");
        System.out.println(spells);
    }

    @Resource
    GoodsService goodsService;

    @Test
    void goodsTest() {
        SearchGoodsListRequest searchGoodsListRequest = new SearchGoodsListRequest();
        searchGoodsListRequest.setType("电话机");
        searchGoodsListRequest.setQuery("1122222");
        //Page<Goods> byType = goodsService.findByType(searchGoodsListRequest);
        //for (Goods record : byType.getRecords()) {
        //    System.out.println("record = " + record);
        //}
    }

}
