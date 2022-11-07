package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Sans
 */
@Data
public class SearchGoodsListRequest implements Serializable {

    private String query;
    private int cid = -1;
    private int pageNum = 1;
    private int pageSize = 20;
    private String type;

}
