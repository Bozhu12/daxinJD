package com.sans.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sans
 */
@Data
public class GoodsFindRequest implements Serializable {

    private List<String> skus;

}
