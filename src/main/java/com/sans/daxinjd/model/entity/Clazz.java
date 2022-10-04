package com.sans.daxinjd.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sans
 */
public class Clazz implements Serializable {


    private Long id;

    @TableField("class_name")
    private String className;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Clazz{" +
        "id=" + id +
        ", className=" + className +
        "}";
    }
}
