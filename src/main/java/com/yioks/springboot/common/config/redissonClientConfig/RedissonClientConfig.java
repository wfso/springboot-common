package com.yioks.springboot.common.config.redissonClientConfig;

import com.yioks.springboot.common.redisson.RedissonClientFactoryBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientConfig {

  @Bean
  public RedisProperties redisProperties(){
    return new RedisProperties();
  }

  @Bean
  public RedissonClientFactoryBean redissonClientFactoryBean() {
    return new RedissonClientFactoryBean();
  }
}
