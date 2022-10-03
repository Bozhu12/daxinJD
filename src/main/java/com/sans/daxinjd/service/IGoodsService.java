package com.sans.daxinjd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.daxinjd.entity.Goods;
import com.sans.daxinjd.utils.ReqSearch;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sans
 */
public interface IGoodsService extends IService<Goods> {
    Page<Goods> search(ReqSearch search);
}
