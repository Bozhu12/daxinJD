package com.sans;

import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DaxinJdApplicationTests {

    @Resource
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
        List<Goods> goods = goodsMapper.selectList(null);
        goods.forEach(System.out::println);

    }

}
