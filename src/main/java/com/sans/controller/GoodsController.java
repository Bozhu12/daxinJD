package com.sans.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.sans.exception.BusinessException;
import com.sans.model.dto.GoodsEditRequest;
import com.sans.model.dto.GoodsFindRequest;
import com.sans.model.dto.SearchGoodsListRequest;
import com.sans.model.entity.Goods;
import com.sans.model.enums.StateCode;
import com.sans.service.GoodsService;
import com.sans.utils.BaseResult;
import org.apache.catalina.connector.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        // 参数校验
        if (search.getPageNum() <= 0 || search.getPageSize() <= 4) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "参数异常!分页参数异常");
        }
        // 业务操作
        Page<Goods> searchPage = goodsService.search(search);
        // 数据不存在
        if (searchPage.getRecords().size() == 0) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "已经没有数据啦!");
        }
        return BaseResult.ok().putData("data", searchPage);
    }

    @PostMapping("/edit/{goodsId}")
    public BaseResult edit(@PathVariable("goodsId") String goodsId,
                           @RequestBody GoodsEditRequest goodsEditRequest) {
        // 校验参数
        if (StringUtils.isBlank(goodsId) || !StringUtils.isNumeric(goodsId)) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Goods goods = goodsService.selectUpdateProperty(goodsId, goodsEditRequest);
        Goods edit = goodsService.edit(goods, true);
        if (edit == null) throw new BusinessException(StateCode.OPERATION_ERROR);
        Goods data = goodsService.findById(Long.parseLong(goodsId));
        if (data == null) throw new BusinessException(StateCode.OPERATION_ERROR);
        return BaseResult.ok().putData("data", data);
    }

    @GetMapping("/find/{sku}")
    public BaseResult findBySku(@PathVariable("sku") long sku) {
        if (sku <= 0) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Goods goods = goodsService.findBySku(sku);
        if (goods == null) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR);
        }
        return BaseResult.ok().putData("data", goods);
    }

    @PostMapping("/find/sku")
    public BaseResult findBySkuList(@RequestBody GoodsFindRequest goodsFindRequest) {
        List<Goods> list = goodsService.findBySkus(goodsFindRequest.getSkus());
        return BaseResult.ok().putData("list",list);
    }

    @GetMapping(value = "/qrcode/sku/{sku}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] createQrcode(@PathVariable("sku") String goodsSku) {
        if (!StringUtils.isNumeric(goodsSku) || StringUtils.isBlank(goodsSku)) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        try {
            return QrcodeUtils.createQrcode(goodsSku, 800, null);
        } catch (Exception e) {
            throw new BusinessException(StateCode.SYSTEM_ERROR, "生成错误,请重新操作!");
        }
    }
}

