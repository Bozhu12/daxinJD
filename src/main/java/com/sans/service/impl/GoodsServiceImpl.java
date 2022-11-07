package com.sans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.exception.BusinessException;
import com.sans.model.dto.GoodsEditRequest;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Goods;
import com.sans.mapper.GoodsMapper;

import com.sans.model.enums.StateCode;
import com.sans.service.GoodsService;
import com.sans.utils.LogMsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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
    public Page<Goods> search(SearchGoodsListRequest search) {
        Page<Goods> page = new Page<>(search.getPageNum(), search.getPageSize());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        String query = search.getQuery();
        String type = search.getType();
        // 检查数值 , (Yes 搜索 SKU ; No 查询 关键值)
        if (StringUtils.isNumeric(query)) {
            qw.eq("goods_sku", search.getQuery());
        } else {
            // 用户是否填写内容
            boolean revisedType = !StringUtils.isBlank(type);
            boolean revisedQuery = !StringUtils.isBlank(query);
            qw.eq(search.getCid() == -1, "goods_class_id", search.getCid());
            qw.like(revisedType, "goods_class_name",type);
            qw.like(revisedQuery, "goods_title", search.getQuery());
            qw.or(revisedQuery);
            qw.like(revisedQuery, "goods_name",query);
        }
        goodsMapper.selectPage(page, qw);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods edit(Goods goods, boolean edit) {
        try {
            if (goodsMapper.updateById(goods) <= 0) throw new BusinessException(StateCode.SYSTEM_ERROR);
            LogMsgUtils.logOutput("[ SKU: "+goods.getGoodsSku()+"]");
            return goods;
        } catch (Exception e) {
            throw new BusinessException(StateCode.SYSTEM_ERROR);
        }
    }

    @Override
    public Goods selectUpdateProperty(String goodsId, GoodsEditRequest goodsEditRequest) throws BusinessException {
        // 确保数据修改
        boolean allow = false;
        Goods newGoods = new Goods();
        // 1. 根据id获取库对象
        Goods oldGoods = goodsMapper.selectById(goodsId);
        // 2. 比较数据
        if (!StringUtils.equals(oldGoods.getGoodsTitle(), goodsEditRequest.getGoodsTitle())) {
            newGoods.setGoodsTitle(goodsEditRequest.getGoodsTitle());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsName(), goodsEditRequest.getGoodsName())) {
            newGoods.setGoodsName(goodsEditRequest.getGoodsName());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsModel(), goodsEditRequest.getGoodsModel())) {
            newGoods.setGoodsModel(goodsEditRequest.getGoodsModel());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsBrand(), goodsEditRequest.getGoodsBrand())) {
            newGoods.setGoodsBrand(goodsEditRequest.getGoodsBrand());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsRemarks(), goodsEditRequest.getGoodsRemarks())) {
            newGoods.setGoodsRemarks(goodsEditRequest.getGoodsRemarks());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsBigLogo(), goodsEditRequest.getGoodsBigLogo())) {
            newGoods.setGoodsBigLogo(goodsEditRequest.getGoodsBigLogo());
            allow = true;
        }
        if (!StringUtils.equals(oldGoods.getGoodsSmallLogo(), goodsEditRequest.getGoodsSmallLogo())) {
            newGoods.setGoodsSmallLogo(goodsEditRequest.getGoodsSmallLogo());
            allow = true;
        }
        if (oldGoods.getGoodsClassId() == null) {
            newGoods.setGoodsClassId(goodsEditRequest.getGoodsClassId());
            allow = true;
        } else if (goodsEditRequest.getGoodsClassId() != oldGoods.getGoodsClassId()) {
            newGoods.setGoodsClassId(goodsEditRequest.getGoodsClassId());
            allow = true;
        }
        if (oldGoods.getGoodsSmallPrice() == null) {
            newGoods.setGoodsSmallPrice(goodsEditRequest.getGoodsSmallPrice());
            allow = true;
        } else if (goodsEditRequest.getGoodsSmallPrice() != oldGoods.getGoodsSmallPrice()) {
            newGoods.setGoodsSmallPrice(goodsEditRequest.getGoodsSmallPrice());
            allow = true;
        }
        if (oldGoods.getGoodsStatus() == null) {
            newGoods.setGoodsStatus(goodsEditRequest.getGoodsStatus());
            allow = true;
        } else if (goodsEditRequest.getGoodsStatus() != oldGoods.getGoodsStatus()) {
            newGoods.setGoodsStatus(goodsEditRequest.getGoodsStatus());
            allow = true;
        }
        if (oldGoods.getGoodsPrice() == null) {
            newGoods.setGoodsPrice(goodsEditRequest.getGoodsPrice());
            allow = true;
        } else if (goodsEditRequest.getGoodsPrice() != oldGoods.getGoodsPrice()) {
            newGoods.setGoodsPrice(goodsEditRequest.getGoodsPrice());
            allow = true;
        }
        newGoods.setId(Long.valueOf(goodsId));
        if (!allow) {
            throw new BusinessException(StateCode.SUCCESS, "无数据修改!");
        }
        return newGoods;
    }

    @Override
    public Goods findById(long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public Goods findBySku(long sku) {
        return goodsMapper.selectOne(new QueryWrapper<Goods>().eq("goods_sku", sku));
    }

    @Override
    public List<Goods> findBySkus(List<String> skus) {
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.in(skus.size() > 1 , "goods_sku",skus);
        return goodsMapper.selectList(qw);
    }

}
