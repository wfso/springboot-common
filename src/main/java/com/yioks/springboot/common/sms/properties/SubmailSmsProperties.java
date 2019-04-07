package com.yioks.springboot.common.sms.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sms.submail")
@Getter
@Setter
public class SubmailSmsProperties {

  private String appId;

  private String appKey;

  private String smsUrl = "https://api.mysubmail.com/message/xsend.json";

  private String timestampUrl = "https://api.mysubmail.com/service/timestamp.json";
}
