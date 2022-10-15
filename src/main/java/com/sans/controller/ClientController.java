package com.sans.controller;


import com.sans.exception.BusinessException;
import com.sans.model.dto.ClientDTO;
import com.sans.model.entity.Client;
import com.sans.model.enums.StateCode;
import com.sans.service.ClientService;
import com.sans.utils.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;

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

    // region CRUD
    @PostMapping("/add")
    public BaseResult add(@RequestBody ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        if (StringUtils.isAnyBlank(clientDTO.getClientName(),
                clientDTO.getClientAddress(), clientDTO.getClientPhone1())) {
            throw new BusinessException(StateCode.PARAMS_ERROR , "参数有误, 必填 姓名/地址/手机号");
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        boolean save = clientService.save(client);
        if (!save) {
            throw new BusinessException(StateCode.OPERATION_ERROR);
        }
        return BaseResult.ok().putData("client", client);
    }

    @PostMapping("/edit")
    public BaseResult edit(@RequestBody ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.getId() == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        client = clientService.setNull(client);
        boolean b = clientService.updateById(client);
        return BaseResult.ok().putData("edit",b);
    }

    @GetMapping("/del/{id}")
    public BaseResult del(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        boolean b = clientService.removeById(id);
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

