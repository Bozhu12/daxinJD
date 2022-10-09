package com.sans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.exception.BusinessException;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;

import com.sans.model.enums.StateCode;
import com.sans.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public Page<Goods> search(SearchGoodsListRequest search) {
        //参数校验
        if (StringUtils.isBlank(search.getQuery())) {
            throw new BusinessException(StateCode.PARAMS_ERROR , "参数为空");
        }
        Page<Goods> page = new Page<>(search.getPageNum(), search.getPageSize());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        // 检查数值 , (Yes 搜索 SKU ; No 查询 关键值)
        if (StringUtils.isNumeric(search.getQuery())) {
             qw.eq(true , "goods_sku" ,search.getQuery());
        }else{
            qw.like(true, "goods_title", search.getQuery());
            qw.eq(search.getCid() == -1 , "goods_class_id", search.getCid());
        }
        goodsMapper.selectPage(page, qw);
        // 数据不存在
        if (page.getRecords().size() == 0) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR);
        }
        return page;
    }

    @Override
    public boolean edit(Goods goods, boolean edit) {

        return false;
    }


}
