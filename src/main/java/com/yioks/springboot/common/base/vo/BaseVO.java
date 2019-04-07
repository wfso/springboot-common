package com.yioks.springboot.common.base.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * VO的父类
 */
public class BaseVO implements Serializable {

    @ApiModelProperty("创建时间戳")
    private long createdAt;

    @ApiModelProperty("修改时间戳")
    private long updatedAt;

    @ApiModelProperty("ID-唯一标识")
    private long id;

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
