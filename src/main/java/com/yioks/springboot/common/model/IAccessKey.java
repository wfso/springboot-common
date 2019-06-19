package com.yioks.springboot.common.model;

import java.io.Serializable;

public interface IAccessKey<UserID> extends Serializable {

  UserID getUserIdentification();

  String getAccessKeyId();

  String getAccessKeySecret();

  boolean isAvailable();
}
