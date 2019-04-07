package com.yioks.springboot.common.sms.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sms.aliyun")
@Getter
@Setter
public class AliyunSmsProperties {

  private String accessKeyId;

  private String accessKeySecret;

  private String signName;

  private String domain = "dysmsapi.aliyuncs.com";

  private String version = "2017-05-25";

  private String action = "SendSms";
}
