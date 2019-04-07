package com.yioks.springboot.common.storage;

import java.io.InputStream;

public interface IStorage {
  String putFile(String fileName, InputStream inputStream);

  default String putFile(String fileName, InputStream inputStream, String type) {
    return putFile(fileName, inputStream);
  }
}
