package com.yioks.springboot.common.sms;

import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.sms.properties.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DefaultSmsSender implements ISmsSender, BeanPostProcessor {

  @Autowired
  private SmsProperties smsProperties;

  @Autowired(required = false)
  private IConfigurationService configurationService;

  @Autowired(required = false)
  private RedissonClient redissonClient;

  public DefaultSmsSender() {
    providers = new ArrayList<>();
  }

  private List<ISmsProvider> providers;

  private String type;

  @PostConstruct
  public void postConstruct() {
    this.type = smsProperties.getType();
    if (redissonClient != null) {
      RTopic rTopic = redissonClient.getTopic("sms.subscribe");
      rTopic.addListener(Integer.class, (s, m) -> clear());
    }
  }

  public ISmsResult sendSms(String phone, String template, Map<String, String> vars, String type) {
    boolean flag = true;
    if (configurationService != null) {
      flag = configurationService.getBooleanConfigure("sms.enable", true);
    }
    if (flag) {
      for (ISmsProvider provider : providers) {
        if (provider.supports(type)) {
          return provider.getSender().sendSms(phone, template, vars);
        }
      }
      log.warn("No available provider was found by type [{}]", type);
    }
    return null;
  }

  public ISmsResult sendSms(String phone, String template, Map<String, String> vars) {
    return sendSms(phone, template, vars, type);
  }

  private void clear() {
    type = null;
    if (configurationService != null) {
      type = configurationService.getConfig("sms.type");
    }
    if (StringUtils.isEmpty(type)) {
      type = smsProperties.getType();
    }
    for (ISmsProvider provider : providers) {
      provider.clear();
    }
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof ISmsProvider) {
      providers.add((ISmsProvider) bean);
    }
    return bean;
  }
}
