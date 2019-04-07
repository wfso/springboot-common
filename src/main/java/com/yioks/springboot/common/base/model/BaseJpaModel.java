package com.yioks.springboot.common.base.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseJpaModel implements IModel, Serializable {
  @Column(updatable = false, nullable = false)
  @ApiModelProperty(value = "创建时间戳", example = "0")
  private long createdAt = 0L;
  @Column(updatable = false, nullable = false)
  @ApiModelProperty(value = "更新时间戳", example = "0")
  private long updatedAt = 0L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "ID-唯一标识", example = "0")
  private long id = 0L;

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
