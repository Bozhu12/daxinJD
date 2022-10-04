package com.sans.daxinjd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.daxinjd.model.entity.Goods;
import com.sans.daxinjd.model.dto.SearchRequest;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sans
 */
public interface IGoodsService extends IService<Goods> {
    Page<Goods> search(SearchRequest search);
}
