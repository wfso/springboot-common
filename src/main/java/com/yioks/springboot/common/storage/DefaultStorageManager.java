package com.yioks.springboot.common.storage;

import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.storage.properties.StorageProperties;
import com.yioks.springboot.common.storage.provider.AliyunOSSProvider;
import com.yioks.springboot.common.storage.provider.LocalStorageProvider;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.io.InputStream;


public class DefaultStorageManager implements IStorageManager {

  @Autowired
  protected IConfigurationService configurationService;

  @Autowired
  protected StorageProperties storageProperties;

  @Autowired(required = false)
  protected RedissonClient redissonClient;

  protected IStorageProvider provider;

  @PostConstruct
  private void postConstruct() {
    if (redissonClient != null) {
      RTopic rTopic = redissonClient.getTopic("storage.subscribe");
      rTopic.addListener(Integer.class, (s, o) -> clear());
    }
  }

  @Override
  public IStorageProvider getProvider() {
    if (provider != null) {
      return provider;
    }

    String storageType = configurationService.getConfig("storage.type");

    if (StringUtils.isEmpty(storageType)) {
      storageType = storageProperties.getType();
    }

    if (storageType.equalsIgnoreCase("aliyun")) {
      String endpoint = configurationService.getConfig("storage.aliyun.endpoint");
      if (StringUtils.isEmpty(endpoint)) {
        endpoint = storageProperties.getAliyunEndpoint();
        Assert.isTrue(!StringUtils.isEmpty(endpoint), "请配置阿里云OSS存储");
      }

      String outside = configurationService.getConfig("storage.aliyun.outside");
      if (StringUtils.isEmpty(outside)) {
        outside = storageProperties.getAliyunOutside();
        Assert.isTrue(!StringUtils.isEmpty(outside), "请配置阿里云OSS存储");
      }

      String accessKeyId = configurationService.getConfig("storage.aliyun.accessKeyId");
      if (StringUtils.isEmpty(accessKeyId)) {
        accessKeyId = storageProperties.getAliyunAccessKeyId();
        Assert.isTrue(!StringUtils.isEmpty(accessKeyId), "请配置阿里云OSS存储");
      }

      String accessKeySecret = configurationService.getConfig("storage.aliyun.accessKeySecret");
      if (StringUtils.isEmpty(accessKeySecret)) {
        accessKeySecret = storageProperties.getAliyunAccessKeySecret();
        Assert.isTrue(!StringUtils.isEmpty(accessKeySecret), "请配置阿里云OSS存储");
      }

      String bucket = configurationService.getConfig("storage.aliyun.bucket");
      if (StringUtils.isEmpty(bucket)) {
        bucket = storageProperties.getAliyunBucket();
        Assert.isTrue(!StringUtils.isEmpty(bucket), "请配置阿里云OSS存储");
      }

      provider = new AliyunOSSProvider(accessKeyId, accessKeySecret, outside, endpoint, bucket);

    } else if (storageType.equalsIgnoreCase("local")) {

      String currentFilePath = configurationService.getConfig("storage.local.filePath");
      if (StringUtils.isEmpty(currentFilePath)) {
        currentFilePath = storageProperties.getLocalFilePath();
      }
      String currentPrefixUrl = configurationService.getConfig("storage.local.prefixUrl");
      if (StringUtils.isEmpty(currentPrefixUrl)) {
        currentPrefixUrl = storageProperties.getLocalPrefixUrl();
      }
      provider = new LocalStorageProvider(currentFilePath, currentPrefixUrl);
    }

    return provider;

  }

  @Override
  public void setProvider(IStorageProvider provider) {
    this.provider = provider;
  }

  public String putFile(String bucket, String fileName, InputStream inputStream) {
    if (getProvider() != null) {
      return getProvider().putFile(bucket, fileName, inputStream);
    }
    return null;
  }

  @Override
  public String getType() {
    return "storage..multi-provider";
  }

  public void clear() {
    this.provider = null;
  }

}
