package com.sans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.model.dto.ClientSpecialDTO;
import com.sans.model.entity.Client;
import com.sans.mapper.ClientMapper;
import com.sans.service.ClientService;
import com.sans.utils.SortUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 * 
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Resource
    public ClientMapper clientMapper;

    @Override
    public Client setNull(Client client) {
        if (StringUtils.isBlank(client.getClientName())) {
            client.setClientName(null);
        }
        if (StringUtils.isBlank(client.getClientPhone1())) {
            client.setClientPhone1(null);
        }
        if (StringUtils.isBlank(client.getClientPhone2())) {
            client.setClientPhone2(null);
        }
        if (StringUtils.isBlank(client.getClientAddress())) {
            client.setClientAddress(null);
        }
        if (StringUtils.isBlank(client.getClientRemarks())) {
            client.setClientRemarks(null);
        }
        return client;
    }

    @Override
    public List<ClientSpecialDTO> findAllOrderByName() {
        List<Client> allOrderByName = clientMapper.findAllOrderByName();
        List<ClientSpecialDTO> list = new ArrayList<>();
        for (Client client : allOrderByName) {
            ClientSpecialDTO dto = new ClientSpecialDTO();
            BeanUtils.copyProperties(client, dto);
            dto.setSerialNumber(SortUtils.getFirstSpells(client.getClientName()));
            list.add(dto);
        }
        return list;
    }
}
