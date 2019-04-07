package com.yioks.springboot.common.storage.provider;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.storage.IStorage;
import com.yioks.springboot.common.storage.IStorageProvider;
import com.yioks.springboot.common.storage.properties.AliyunOssStorageProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.InputStream;

public class AliyunOSSProvider implements IStorageProvider {

  @Autowired
  private IConfigurationService configurationService;

  @Autowired
  private AliyunOssStorageProperties storageProperties;

  private IStorage storage;

  @Override
  public IStorage getStorage() {
    if (storage == null) {
      String endpoint = configurationService.getConfig("storage.aliyun.endpoint");
      if (StringUtils.isEmpty(endpoint)) {
        endpoint = storageProperties.getEndpoint();
        Assert.isTrue(!StringUtils.isEmpty(endpoint), "请配置阿里云OSS存储");
      }

      String outside = configurationService.getConfig("storage.aliyun.outside");
      if (StringUtils.isEmpty(outside)) {
        outside = storageProperties.getOutside();
        Assert.isTrue(!StringUtils.isEmpty(outside), "请配置阿里云OSS存储");
      }

      String accessKeyId = configurationService.getConfig("storage.aliyun.accessKeyId");
      if (StringUtils.isEmpty(accessKeyId)) {
        accessKeyId = storageProperties.getAccessKeyId();
        Assert.isTrue(!StringUtils.isEmpty(accessKeyId), "请配置阿里云OSS存储");
      }

      String accessKeySecret = configurationService.getConfig("storage.aliyun.accessKeySecret");
      if (StringUtils.isEmpty(accessKeySecret)) {
        accessKeySecret = storageProperties.getAccessKeySecret();
        Assert.isTrue(!StringUtils.isEmpty(accessKeySecret), "请配置阿里云OSS存储");
      }

      String bucket = configurationService.getConfig("storage.aliyun.bucket");
      if (StringUtils.isEmpty(bucket)) {
        bucket = storageProperties.getBucket();
        Assert.isTrue(!StringUtils.isEmpty(bucket), "请配置阿里云OSS存储");
      }
      storage = new AliyunOssStorage(accessKeyId, accessKeySecret, outside, endpoint, bucket);
    }

    return storage;
  }

  @Override
  public void clear() {
    storage = null;
  }

  @Override
  public String getType() {
    return "aliyun";
  }

  private static class AliyunOssStorage implements IStorage {
    private final String endpoint;
    private final String bucket;
    private final String outside;

    private final OSSClient ossClient;

    public AliyunOssStorage(String accessKeyId, String accessKeySecret, String outside, String endpoint, String bucket) {
      this.bucket = bucket;
      this.outside = outside;
      if (StringUtils.isEmpty(endpoint)) {
        this.endpoint = outside;
      } else {
        this.endpoint = endpoint;
      }
      ossClient = new OSSClient(this.endpoint, new DefaultCredentialProvider(accessKeyId, accessKeySecret), new ClientConfiguration());
    }

    @Override
    public String putFile(String fileName, InputStream inputStream) {
      fileName = bucket + fileName;
      ossClient.putObject(this.bucket, fileName, inputStream);
      return "https://" + this.bucket + "." + this.outside + "/" + fileName;
    }
  }
}
