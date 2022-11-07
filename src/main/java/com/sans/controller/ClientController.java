package com.sans.controller;


import com.sans.exception.BusinessException;
import com.sans.model.dto.ClientEditRequest;
import com.sans.model.dto.ClientSpecialDTO;
import com.sans.model.entity.Client;
import com.sans.model.enums.StateCode;
import com.sans.service.ClientService;
import com.sans.utils.BaseResult;
import com.sans.utils.LogMsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/client")
public class ClientController {

    @Resource
    private ClientService clientService;

    @GetMapping("/indexlist")
    public BaseResult findAllOrderByName() {
        List<ClientSpecialDTO> allOrderByName = clientService.findAllOrderByName();
        if (allOrderByName == null || allOrderByName.size() == 0)
            throw new BusinessException(StateCode.SYSTEM_ERROR);
        return BaseResult.ok().putData("data",allOrderByName);
    }

    // region CRUD

    @PostMapping("/add")
    public BaseResult add(@RequestBody ClientEditRequest clientEditRequest) {
        if (clientEditRequest == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        if (StringUtils.isAnyBlank(clientEditRequest.getClientName(),
                clientEditRequest.getClientAddress(), clientEditRequest.getClientPhone1())) {
            throw new BusinessException(StateCode.PARAMS_ERROR , "参数有误, 必填 姓名/地址/手机号");
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientEditRequest, client);
        boolean save = clientService.save(client);
        if (!save) {
            throw new BusinessException(StateCode.OPERATION_ERROR);
        }
        return BaseResult.ok().putData("client", client);
    }

    @PostMapping("/edit")
    public BaseResult edit(@RequestBody ClientEditRequest clientEditRequest) {
        if (clientEditRequest == null || clientEditRequest.getId() == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientEditRequest, client);
        client = clientService.setNull(client);
        boolean b = clientService.updateById(client);
        LogMsgUtils.logOutput("[ 客户名称: "+client.getClientName()+"]");
        return BaseResult.ok().putData("edit",b);
    }

    @GetMapping("/del/{id}")
    public BaseResult del(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        boolean b = clientService.removeById(id);
        LogMsgUtils.logOutput("[ 客户id: "+ id +"]");
        return BaseResult.ok().putData("del", b);
    }

    @GetMapping("/find/{id}")
    public BaseResult find(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Client client = clientService.getById(id);
        return BaseResult.ok().putData("client", client);
    }

    // endregion
}

