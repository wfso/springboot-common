package com.yioks.springboot.common.storage.provider;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.yioks.springboot.common.storage.IStorageProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

public class AliyunOSSProvider implements IStorageProvider {
  private final String accessKeyId;
  private final String accessKeySecret;

  private final String endpoint;
  private final String bucket;
  private final String outside;

  private final OSSClient ossClient;

  public AliyunOSSProvider(String accessKeyId, String accessKeySecret, String outside, String endpoint, String bucket) {
    this.accessKeyId = accessKeyId;
    this.accessKeySecret = accessKeySecret;
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
  public String putFile(String bucket, String fileName, InputStream inputStream) {
    fileName = bucket + fileName;
    ossClient.putObject(this.bucket, fileName, inputStream);
    return "https://" + this.bucket + "." + this.outside + "/" + fileName;
  }

  @Override
  public String getType() {
    return "storage.aliyun";
  }
}
