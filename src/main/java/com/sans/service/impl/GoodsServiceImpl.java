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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Goods> search(SearchGoodsListRequest search) {
        Page<Goods> page = new Page<>(search.getPageNum(), search.getPageSize());
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        // 检查数值 , (Yes 搜索 SKU ; No 查询 关键值)
        if (StringUtils.isNumeric(search.getQuery())) {
            qw.eq(true, "goods_sku", search.getQuery());
        } else {
            qw.like(true, "goods_title", search.getQuery());
            qw.eq(search.getCid() == -1, "goods_class_id", search.getCid());
        }
        goodsMapper.selectPage(page, qw);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Goods goods, boolean edit) {
        try {
            return goodsMapper.updateById(goods) >= 0;
        } catch (Exception e) {
            throw new BusinessException(StateCode.SYSTEM_ERROR);
        }
    }

    @Override
    public Goods selectUpdateProperty(String goodsId, GoodsEditRequest goodsEditRequest) throws BusinessException {
        Goods newGoods = new Goods();
        // 1. 根据id获取库对象
        Goods oldGoods = goodsMapper.selectById(goodsId);
        // 2. 比较数据
        if (!StringUtils.equals(oldGoods.getGoodsTitle(), goodsEditRequest.getGoodsTitle())) {
            newGoods.setGoodsTitle(goodsEditRequest.getGoodsTitle());
        }

        if (!StringUtils.equals(oldGoods.getGoodsName(), goodsEditRequest.getGoodsName())) {
            newGoods.setGoodsName(goodsEditRequest.getGoodsName());
        }

        if (!StringUtils.equals(oldGoods.getGoodsModel(), goodsEditRequest.getGoodsModel())) {
            newGoods.setGoodsModel(goodsEditRequest.getGoodsModel());
        }

        if (!StringUtils.equals(oldGoods.getGoodsSku(), goodsEditRequest.getGoodsSku())) {
            newGoods.setGoodsSku(goodsEditRequest.getGoodsSku());
        }

        if (!StringUtils.equals(oldGoods.getGoodsBrand(), goodsEditRequest.getGoodsBrand())) {
            newGoods.setGoodsBrand(goodsEditRequest.getGoodsBrand());
        }

        if (!StringUtils.equals(oldGoods.getGoodsRemarks(), goodsEditRequest.getGoodsRemarks())) {
            newGoods.setGoodsRemarks(goodsEditRequest.getGoodsRemarks());
        }

        if (!StringUtils.equals(oldGoods.getGoodsBigLogo(), goodsEditRequest.getGoodsBigLogo())) {
            newGoods.setGoodsBigLogo(goodsEditRequest.getGoodsBigLogo());
        }

        if (!StringUtils.equals(oldGoods.getGoodsSmallLogo(), goodsEditRequest.getGoodsSmallLogo())) {
            newGoods.setGoodsSmallLogo(goodsEditRequest.getGoodsSmallLogo());
        }

        try {
            if (goodsEditRequest.getGoodsClassId() != null) {
                Long aLong = Long.valueOf(goodsEditRequest.getGoodsClassId());
                if (Objects.equals(oldGoods.getGoodsClassId(), aLong)) {
                    newGoods.setGoodsClassId(aLong);
                }
            }
            if (goodsEditRequest.getGoodsSmallPrice() != null) {
                Double aDouble = Double.valueOf(goodsEditRequest.getGoodsSmallPrice());
                if (Objects.equals(oldGoods.getGoodsSmallPrice(), aDouble)) {
                    newGoods.setGoodsSmallPrice(aDouble);
                }
            }
            if (goodsEditRequest.getGoodsStatus() != null) {
                Integer integer = Integer.getInteger(goodsEditRequest.getGoodsStatus());
                if (Objects.equals(oldGoods.getGoodsStatus(), integer)) {
                    newGoods.setGoodsStatus(integer);
                }
            }
            if (goodsEditRequest.getGoodsPrice() != null) {
                Double aDouble1 = Double.valueOf(goodsEditRequest.getGoodsPrice());
                if (Objects.equals(oldGoods.getGoodsPrice(), aDouble1)) {
                    newGoods.setGoodsPrice(aDouble1);
                }
            }
            newGoods.setId(Long.valueOf(goodsId));
        } catch (NumberFormatException e) {
            throw new BusinessException(StateCode.PARAMS_ERROR,"数值填写异常,请填写正确数据!");
        }
        return newGoods;
    }

    @Override
    public Goods findById(long id) {
        return goodsMapper.selectById(id);
    }


}
