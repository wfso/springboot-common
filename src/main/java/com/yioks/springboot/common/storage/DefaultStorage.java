package com.yioks.springboot.common.storage;

import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.storage.properties.StorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DefaultStorage implements IStorage, BeanPostProcessor {

  @Autowired(required = false)
  private IConfigurationService configurationService;

  @Autowired
  private StorageProperties storageProperties;

  @Autowired(required = false)
  private RedissonClient redissonClient;

  public DefaultStorage() {
    providers = new ArrayList<>();
  }

  private List<IStorageProvider> providers;

  private String type;

  @PostConstruct
  private void postConstruct() {
    this.type = storageProperties.getType();
    if (redissonClient != null) {
      RTopic rTopic = redissonClient.getTopic("storage.subscribe");
      rTopic.addListener(Integer.class, (s, o) -> clear());
    }
  }

  private void clear() {
    type = null;
    if (configurationService != null) {
      type = configurationService.getConfig("storage.type");
    }
    if (StringUtils.isEmpty(type)) {
      type = storageProperties.getType();
    }
    for (IStorageProvider provider : providers) {
      provider.clear();
    }
  }

  @Override
  public String putFile(String fileName, InputStream inputStream) {
    return putFile(fileName, inputStream, type);
  }

  @Override
  public String putFile(String fileName, InputStream inputStream, String type) {
    for (IStorageProvider provider : providers) {
      if (provider.supports(type)) {
        return provider.getStorage().putFile(fileName, inputStream);
      }
    }
    log.warn("No available provider was found by type [{}]", type);
    return "";
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof IStorageProvider) {
      providers.add((IStorageProvider) bean);
    }
    return bean;
  }
}
