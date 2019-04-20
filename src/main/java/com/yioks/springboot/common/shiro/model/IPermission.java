package com.yioks.springboot.common.shiro.model;

public interface IPermission {
  long getId();

  void setId(long id);

  String getCode();

  void setCode(String code);

  String getName();

  void setName(String name);

  String getIntro();

  void setIntro(String intro);
}
