package com.yioks.springboot.common.base.model;

public interface IModel {

  long getCreatedAt();

  void setCreatedAt(long createdAt);

  long getUpdatedAt();

  void setUpdatedAt(long updatedAt);

  long getId();

  void setId(long id);

  String getUuid();

  void setUuid(String uuid);
}
