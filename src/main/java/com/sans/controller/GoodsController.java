package com.sans.controller;

import com.sans.model.dto.GoodsEditRequest;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.service.GoodsService;
import com.sans.utils.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sans
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/search")
    public BaseResult search(@RequestBody SearchGoodsListRequest search) {
        return BaseResult.ok().putData("data", goodsService.search(search));
    }

    // TODO 商品修改业务
    @PostMapping("/edit")
    public BaseResult edit(@RequestBody GoodsEditRequest goodsEditRequest) {


        return null;
    }
}

