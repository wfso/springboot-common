package com.yioks.springboot.common.base.model;

public interface IModel {

  Long getCreatedAt();

  void setCreatedAt(Long createdAt);

  Long getUpdatedAt();

  void setUpdatedAt(Long updatedAt);

  Long getId();

  void setId(Long id);
}
