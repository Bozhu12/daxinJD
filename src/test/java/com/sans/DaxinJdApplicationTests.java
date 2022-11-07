package com.sans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sans.mapper.ClientMapper;
import com.sans.mapper.OrderMapper;
import com.sans.model.dto.OrderInfoDTO;
import com.sans.model.dto.OrderUnitDTO;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Client;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;
import com.sans.model.entity.Order;
import com.sans.service.ClientService;
import com.sans.service.GoodsService;
import com.sans.service.OrderService;
import com.sans.utils.JwtUtils;
import com.sans.utils.SortUtils;
import io.jsonwebtoken.Claims;
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

    @Test
    void clientTest() throws JsonProcessingException {
        //List<ClientSpecialDTO> allOrderByName = clientService.findAllOrderByName();
        //System.out.println("=================");
        //for (ClientSpecialDTO clientSpecialDTO : allOrderByName) {
        //    System.out.println(clientSpecialDTO);
        //}

        Client client = clientMapper.selectById(4);
        //String userJson = JSONUtils.toJSONString(obj);
        String userJson = objectMapper.writeValueAsString(client);
        System.out.println("userJson(JSON) = " + userJson);
        String token = JwtUtils.generateToken(userJson);
        System.out.println("token = " + token);
        System.out.println("===========");
        Claims claimsByToken = JwtUtils.getClaimsByToken(token);
        System.out.println("claimsByToken = " + claimsByToken);
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
        List<Order> orders = orderService.orderRetreat();
        orders.forEach(System.out::println);
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
