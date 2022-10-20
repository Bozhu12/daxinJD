package com.sans.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.exception.BusinessException;
import com.sans.model.dto.GoodsEditRequest;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Goods;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author Sans
* @description 针对表【goods】的数据库操作Service
* @createDate 2022-10-08 11:53:23
*/
public interface GoodsService extends IService<Goods> {

    /**
     * 搜索
     * @param search 参数对象
     * @return 分页结果
     */
    Page<Goods> search(SearchGoodsListRequest search);

    /**
     * 编辑商品
     * @param goods 实体
     * @param edit 是否操作
     * @return 响应可行
     */
    boolean edit(Goods goods, boolean edit);

    /**
     * 匹配整合数据
     * @param goodsId 操作商品id
     * @param goodsEditRequest 请求数据
     * @return 编辑操作对象
     * @throws BusinessException 类型转换异常
     */
    Goods selectUpdateProperty(String goodsId, GoodsEditRequest goodsEditRequest)throws BusinessException;

    /**
     * 根据商品id找商品
     * @param id 商品id
     * @return 商品对象
     */
    Goods findBySku(long id);
}
