package com.sans.daxinjd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sans.daxinjd.entity.Goods;
import com.sans.daxinjd.service.IGoodsService;
import com.sans.daxinjd.utils.ReqSearch;
import com.sans.daxinjd.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private IGoodsService iGoodsService;

    @PostMapping("/search")
    public Result search(@RequestBody ReqSearch search) {
        return Result.ok().putData("data", iGoodsService.search(search));
    }

}

