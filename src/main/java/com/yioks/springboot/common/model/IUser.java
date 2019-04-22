package com.yioks.springboot.common.model;

import javax.persistence.Transient;
import java.io.Serializable;

public interface IUser extends Serializable {
  long getIdentification();

  String getCredentials();

  @Transient
  default boolean isAvailable() {
    return getIdentification() > 0;
  }
}
