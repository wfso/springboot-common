package com.yioks.springboot.common.config.storageConfig;


import com.yioks.springboot.common.storage.DefaultStorage;
import com.yioks.springboot.common.storage.IStorage;
import com.yioks.springboot.common.storage.properties.AliyunOssStorageProperties;
import com.yioks.springboot.common.storage.properties.LocalStorageProperties;
import com.yioks.springboot.common.storage.properties.StorageProperties;
import com.yioks.springboot.common.storage.provider.AliyunOSSProvider;
import com.yioks.springboot.common.storage.provider.LocalStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultStorageConfig {

  @Bean
  StorageProperties storageProperties() {
    return new StorageProperties();
  }

  @Bean
  AliyunOssStorageProperties aliyunOssStorageProperties() {
    return new AliyunOssStorageProperties();
  }

  @Bean
  LocalStorageProperties localStorageProperties() {
    return new LocalStorageProperties();
  }

  @Bean
  AliyunOSSProvider aliyunOSSProvider() {
    return new AliyunOSSProvider();
  }

  @Bean
  LocalStorageProvider localStorageProvider() {
    return new LocalStorageProvider();
  }

  @Bean
  IStorage defaultStorage() {
    return new DefaultStorage();
  }
}
