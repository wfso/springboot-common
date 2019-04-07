package com.yioks.springboot.common.sms;

public interface ISmsProvider {

  ISmsSender getSender();

  void clear();

  String getType();

  default boolean supports(String type) {
    return type != null && type.equalsIgnoreCase(getType());
  }
}
