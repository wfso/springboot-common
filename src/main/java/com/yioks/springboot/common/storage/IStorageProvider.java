package com.yioks.springboot.common.storage;

import java.io.InputStream;

public interface IStorageProvider {
  String putFile(String bucket, String fileName, InputStream inputStream);

  String getType();
}
