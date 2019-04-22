package com.yioks.springboot.common.model;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import java.io.Serializable;

public interface IRole extends Serializable {
  long getId();

  void setId(long id);

  String getCode();

  void setCode(String code);

  String getName();

  void setName(String name);

  String getIntro();

  void setIntro(String intro);

  @Transient
  default boolean isAvailable() {
    return getId() > 0 && StringUtils.isNotBlank(getCode());
  }
}
