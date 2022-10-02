package com.sans.daxinjd;

import com.sans.daxinjd.entity.Goods;
import com.sans.daxinjd.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DaxinJdApplicationTests {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {

        List<Goods> goods = goodsMapper.selectList(null);
        goods.forEach(System.out::println);

    }

}
