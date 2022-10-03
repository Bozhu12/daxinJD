package com.sans.daxinjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sans
 * 
 */
public class Client implements Serializable {


    private Long id;

    @TableField("client_name")
    private String clientName;

    @TableField("client_phone1")
    private String clientPhone1;

    @TableField("client_phone2")
    private String clientPhone2;

    @TableField("client_address")
    private String clientAddress;

    @TableField("client_remarks")
    private String clientRemarks;

    @TableField("client_create_time")
    private LocalDateTime clientCreateTime;

    @TableField("client_update_time")
    private LocalDateTime clientUpdateTime;

    private Integer delete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone1() {
        return clientPhone1;
    }

    public void setClientPhone1(String clientPhone1) {
        this.clientPhone1 = clientPhone1;
    }

    public String getClientPhone2() {
        return clientPhone2;
    }

    public void setClientPhone2(String clientPhone2) {
        this.clientPhone2 = clientPhone2;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientRemarks() {
        return clientRemarks;
    }

    public void setClientRemarks(String clientRemarks) {
        this.clientRemarks = clientRemarks;
    }

    public LocalDateTime getClientCreateTime() {
        return clientCreateTime;
    }

    public void setClientCreateTime(LocalDateTime clientCreateTime) {
        this.clientCreateTime = clientCreateTime;
    }

    public LocalDateTime getClientUpdateTime() {
        return clientUpdateTime;
    }

    public void setClientUpdateTime(LocalDateTime clientUpdateTime) {
        this.clientUpdateTime = clientUpdateTime;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Client{" +
        "id=" + id +
        ", clientName=" + clientName +
        ", clientPhone1=" + clientPhone1 +
        ", clientPhone2=" + clientPhone2 +
        ", clientAddress=" + clientAddress +
        ", clientRemarks=" + clientRemarks +
        ", clientCreateTime=" + clientCreateTime +
        ", clientUpdateTime=" + clientUpdateTime +
        ", delete=" + delete +
        "}";
    }
}
