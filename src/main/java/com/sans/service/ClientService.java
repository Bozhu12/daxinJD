package com.sans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.model.entity.Client;

/**
* @author Sans
* @description 针对表【client】的数据库操作Service
* @createDate 2022-10-08 11:53:23
*/
public interface ClientService extends IService<Client> {

    Client setNull(Client client);
}
