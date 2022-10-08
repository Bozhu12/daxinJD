package com.sans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.model.dto.SearchRequest;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;

import com.sans.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Page<Goods> search(SearchRequest search) {
        Page<Goods> page = new Page<>(search.getPageNum(), search.getPageSize());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.like(ObjectUtils.isEmpty(search.getQuery().trim())|| Objects.equals(search.getQuery(), "")
                , "goods_title", search.getQuery());
        qw.eq(ObjectUtils.isEmpty(search.getCid()), "goods_class_id", search.getCid());
        goodsMapper.selectPage(page, qw);
        return page;
    }
}
