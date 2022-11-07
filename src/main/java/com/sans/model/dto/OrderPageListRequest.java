package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sans
 */
@Data
public class OrderPageListRequest implements Serializable {

    private int pageNum;
    private int pageSize;

}
