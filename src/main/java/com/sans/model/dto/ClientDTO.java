package com.sans.model.dto;

import lombok.Data;

/**
 * @author Sans
 */
@Data
public class ClientDTO {

    private Long id;
    private String clientName;
    private String clientPhone1;
    private String clientPhone2;
    private String clientAddress;
    private String clientRemarks;

}
