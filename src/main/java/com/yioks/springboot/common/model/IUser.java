package com.yioks.springboot.common.model;

import java.io.Serializable;

public interface IUser<ID> extends Serializable {
  ID getIdentification();

  String getCredentials();

  boolean isAvailable();
}
