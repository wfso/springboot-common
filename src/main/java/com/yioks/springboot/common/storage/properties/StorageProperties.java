package com.yioks.springboot.common.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("storage")
public class StorageProperties {

  private String type = "local";

  private String localFilePath = "/uploads/";

  private String localPrefixUrl = "/uploads/";

  private String aliyunAccessKeyId;

  private String aliyunAccessKeySecret;

  private String aliyunEndpoint;

  private String aliyunOutside;

  private String aliyunBucket;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocalFilePath() {
    return localFilePath;
  }

  public void setLocalFilePath(String localFilePath) {
    this.localFilePath = localFilePath;
  }

  public String getLocalPrefixUrl() {
    return localPrefixUrl;
  }

  public void setLocalPrefixUrl(String localPrefixUrl) {
    this.localPrefixUrl = localPrefixUrl;
  }

  public String getAliyunAccessKeyId() {
    return aliyunAccessKeyId;
  }

  public void setAliyunAccessKeyId(String aliyunAccessKeyId) {
    this.aliyunAccessKeyId = aliyunAccessKeyId;
  }

  public String getAliyunAccessKeySecret() {
    return aliyunAccessKeySecret;
  }

  public void setAliyunAccessKeySecret(String aliyunAccessKeySecret) {
    this.aliyunAccessKeySecret = aliyunAccessKeySecret;
  }

  public String getAliyunEndpoint() {
    return aliyunEndpoint;
  }

  public void setAliyunEndpoint(String aliyunEndpoint) {
    this.aliyunEndpoint = aliyunEndpoint;
  }

  public String getAliyunOutside() {
    return aliyunOutside;
  }

  public void setAliyunOutside(String aliyunOutside) {
    this.aliyunOutside = aliyunOutside;
  }

  public String getAliyunBucket() {
    return aliyunBucket;
  }

  public void setAliyunBucket(String aliyunBucket) {
    this.aliyunBucket = aliyunBucket;
  }
}
