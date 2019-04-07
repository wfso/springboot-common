package com.yioks.springboot.common.storage;

public interface IStorageManager extends IStorageProvider {

  IStorageProvider getProvider();

  void setProvider(IStorageProvider provider);
}
