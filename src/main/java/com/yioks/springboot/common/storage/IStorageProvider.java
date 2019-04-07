package com.yioks.springboot.common.storage;

public interface IStorageProvider {
  IStorage getStorage();

  void clear();

  String getType();

  default boolean supports(String type) {
    return type != null && type.equalsIgnoreCase(getType());
  }
}
