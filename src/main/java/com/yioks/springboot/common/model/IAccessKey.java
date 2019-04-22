package com.yioks.springboot.common.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import java.io.Serializable;

public interface IAccessKey extends Serializable {

  long getUserIdentification();

  String getAccessKeyId();

  String getAccessKeySecret();

  @Transient
  default boolean isAvailable() {
    return getUserIdentification() > 0
      && StringUtils.isNotBlank(getAccessKeyId())
      && StringUtils.isNotBlank(getAccessKeySecret());
  }
}
