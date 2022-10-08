package com.sans.controller;


import com.sans.model.dto.SearchRequest;
import com.sans.service.GoodsService;
import com.sans.utils.BaseResult;
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
    private GoodsService goodsService;

    @PostMapping("/search")
    public BaseResult search(@RequestBody SearchRequest search) {
        return BaseResult.ok().putData("data", goodsService.search(search));
    }

}

