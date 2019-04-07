package com.yioks.springboot.common.storage.provider;

import com.yioks.springboot.common.storage.IStorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LocalStorageProvider implements IStorageProvider {
  private String filePath;
  private String prefixUrl;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public LocalStorageProvider(String filePath, String prefixUrl) {
    this.filePath = filePath;
    this.prefixUrl = prefixUrl;
  }

  @Override
  public String putFile(String bucket, String fileName, InputStream inputStream) {
    String name = filePath + bucket + fileName;
    File file = new File(name);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    try {
      Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      return prefixUrl + bucket + fileName;
    } catch (Exception e) {
      e.printStackTrace();
      logger.warn(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public String getType() {
    return "storage.local";
  }
}
