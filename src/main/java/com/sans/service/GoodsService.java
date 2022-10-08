package com.sans.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.model.dto.SearchRequest;
import com.sans.model.entity.Goods;

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
    Page<Goods> search(SearchRequest search);
}
