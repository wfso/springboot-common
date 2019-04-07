package com.yioks.springboot.common.sms;

public interface ISmsResult {
  String getId();

  boolean isSuccess();

  String toJSON();
}
