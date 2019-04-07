package com.yioks.springboot.common.config.storageConfig;


import com.yioks.springboot.common.storage.DefaultStorageManager;
import com.yioks.springboot.common.storage.properties.StorageProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultStorageConfig {

  @Bean
  StorageProperties storageProperties() {
    return new StorageProperties();
  }

  @Bean
  DefaultStorageManager storageManager() {
    return new DefaultStorageManager();
  }
}
