package com.yioks.springboot.common.model;

import java.io.Serializable;

public interface IPermission<ID> extends Serializable {
  ID getId();

  void setId(ID id);

  String getCode();

  void setCode(String code);

  String getName();

  void setName(String name);

  String getIntro();

  void setIntro(String intro);

  boolean isAvailable();
}
