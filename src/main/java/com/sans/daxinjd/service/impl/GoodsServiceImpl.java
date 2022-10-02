package com.sans.daxinjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.daxinjd.entity.Goods;
import com.sans.daxinjd.mapper.GoodsMapper;
import com.sans.daxinjd.service.IGoodsService;
import com.sans.daxinjd.utils.ReqSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Page<Goods> search(ReqSearch search) {
        Page<Goods> page = new Page<>(search.getPageNum(), search.getPageSize());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.like(ObjectUtils.isEmpty(search.getQuery().trim())|| Objects.equals(search.getQuery(), "")
                , "goods_title", search.getQuery());
        qw.eq(ObjectUtils.isEmpty(search.getCid()), "goods_class_id", search.getCid());
        goodsMapper.selectPage(page, qw);
        return page;
    }
}
