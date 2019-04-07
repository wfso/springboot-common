package com.yioks.springboot.common.config.smsConfig;

import com.yioks.springboot.common.sms.DefaultSmsSender;
import com.yioks.springboot.common.sms.ISmsSender;
import com.yioks.springboot.common.sms.properties.AliyunSmsProperties;
import com.yioks.springboot.common.sms.properties.SmsProperties;
import com.yioks.springboot.common.sms.properties.SubmailSmsProperties;
import com.yioks.springboot.common.sms.provider.AliyunSmsProvider;
import com.yioks.springboot.common.sms.provider.SubmailSmsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultSmsConfig {

  @Bean
  public SmsProperties smsProperties() {
    return new SmsProperties();
  }

  @Bean
  public AliyunSmsProperties aliyunSmsProperties() {
    return new AliyunSmsProperties();
  }

  @Bean
  public SubmailSmsProperties submailSmsProperties() {
    return new SubmailSmsProperties();
  }

  @Bean
  public AliyunSmsProvider aliyunSmsProvider() {
    return new AliyunSmsProvider();
  }

  @Bean
  public SubmailSmsProvider submailSmsProvider() {
    return new SubmailSmsProvider();
  }

  @Bean
  public ISmsSender smsSender() {
    return new DefaultSmsSender();
  }
}
