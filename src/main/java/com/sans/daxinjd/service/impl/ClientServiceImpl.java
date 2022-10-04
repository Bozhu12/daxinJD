package com.sans.daxinjd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.daxinjd.model.entity.Client;
import com.sans.daxinjd.mapper.ClientMapper;
import com.sans.daxinjd.service.IClientService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 * 
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
