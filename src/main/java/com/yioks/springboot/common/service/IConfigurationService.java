package com.yioks.springboot.common.service;

import java.math.BigDecimal;

public interface IConfigurationService {
  String getConfig(String key);

  void setConfig(String key, String value);

  BigDecimal getDecimalConfig(String key);

  long getLongConfig(String key, long defaultValue);

  int getIntConfig(String key, int defaultValue);

  boolean getBooleanConfigure(String key, boolean defaultValue);
}
