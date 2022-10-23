package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户 排序分类
 * @author Sans
 */
@Data
public class ClientSpecialDTO implements Serializable {

    private Long id;
    private String clientName;
    private String serialNumber;

}
