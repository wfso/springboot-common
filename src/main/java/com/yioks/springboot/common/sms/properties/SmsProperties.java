package com.yioks.springboot.common.sms.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sms")
@Getter
@Setter
public class SmsProperties {
  private String type = "submail";
}
