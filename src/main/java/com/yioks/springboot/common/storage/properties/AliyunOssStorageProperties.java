package com.yioks.springboot.common.storage.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("storage.aliyunoss")
@Getter
@Setter
public class AliyunOssStorageProperties {

  private String accessKeyId;

  private String accessKeySecret;

  private String endpoint;

  private String outside;

  private String bucket;

}
