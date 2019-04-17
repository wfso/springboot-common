package com.yioks.springboot.common.storage.provider;

import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.storage.IStorage;
import com.yioks.springboot.common.storage.IStorageProvider;
import com.yioks.springboot.common.storage.properties.LocalStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LocalStorageProvider implements IStorageProvider {

  @Autowired(required = false)
  private IConfigurationService configurationService;

  @Autowired
  private LocalStorageProperties storageProperties;

  private IStorage storage;

  @Override
  public IStorage getStorage() {
    if (storage == null) {
      String filePath = null, prefixUrl = null;
      if (configurationService != null) {
        filePath = configurationService.getConfig("storage.local.filePath");
        prefixUrl = configurationService.getConfig("storage.local.prefixUrl");
      }
      if (StringUtils.isEmpty(filePath)) {
        filePath = storageProperties.getFilePath();
      }

      if (StringUtils.isEmpty(prefixUrl)) {
        prefixUrl = storageProperties.getPrefixUrl();
      }
      storage = new LocalStorage(filePath, prefixUrl);
    }
    return storage;
  }

  @Override
  public void clear() {
    storage = null;
  }

  @Override
  public String getType() {
    return "local";
  }

  @Slf4j
  private static class LocalStorage implements IStorage {

    private final String filePath;
    private final String prefixUrl;

    public LocalStorage(String filePath, String prefixUrl) {
      this.filePath = filePath;
      this.prefixUrl = prefixUrl;
    }

    @Override
    public String putFile(String fileName, InputStream inputStream) {
      String name = filePath + fileName;
      File file = new File(name);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      try {
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return prefixUrl + fileName;
      } catch (Exception e) {
        e.printStackTrace();
        log.warn(e.getMessage(), e);
      }
      return null;
    }
  }
}
