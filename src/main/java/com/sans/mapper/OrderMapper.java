package com.sans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sans.model.dto.OrderInfoDTO;
import com.sans.model.dto.OrderUnitDTO;
import com.sans.model.entity.Client;
import com.sans.model.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Sans
* @description 针对表【order】的数据库操作Mapper
* @createDate 2022-10-08 11:53:23
* @Entity com.sans.model.entity.Order
*/
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 获取订单首个商品
     * @return
     */
    @Select("SELECT" +
            " id," +
            " (" +
            " SELECT" +
            "  client_name " +
            " FROM" +
            "  `client` " +
            " WHERE" +
            " id = ( SELECT order_client_id FROM `order` WHERE id = #{id} )) 'clientName'," +
            " (" +
            " SELECT" +
            "  user_name " +
            " FROM" +
            "  `users` " +
            " WHERE" +
            " id = ( SELECT order_user_id FROM `order` WHERE id = #{id} )) 'userName'," +
            " order_fact_price 'price'," +
            " order_create_time 'createTime'," +
            " (" +
            " SELECT" +
            "  goods_title " +
            " FROM" +
            "  goods " +
            " WHERE" +
            " id = ( SELECT orderinfo_goods_id FROM `orderinfo` WHERE orderinfo_order_id = ( SELECT id FROM `order` WHERE id = #{id} ) LIMIT 0, 1 )) 'title'," +
            " ( SELECT SUM( orderinfo_count ) FROM `orderinfo` WHERE orderinfo_order_id = #{id} ) 'goodsCount' " +
            "FROM" +
            " `order` " +
            "WHERE" +
            " id = #{id}")
    OrderUnitDTO orderInfoById(@Param("id")long id);

    @Select("SELECT " +
            " o.id 'id'," +
            " o.order_remark 'remark'," +
            " o.order_status 'status'," +
            " o.order_create_time 'createTime'," +
            " o.order_fact_price 'price'," +
            " (select user_name FROM `users` WHERE id = (select order_user_id FROM `order` WHERE id = #{id})) 'userName'," +
            " c.client_name 'clientName'," +
            " c.client_address 'clientAddress'," +
            " c.client_phone1 'clientPhone1'," +
            " c.client_phone2 'clientPhone2'" +
            "FROM `order` o,`client` c WHERE" +
            " o.id = #{id} AND" +
            " c.id = o.order_client_id")
    OrderInfoDTO orderinfoMsg(@Param("id")long id);
}




