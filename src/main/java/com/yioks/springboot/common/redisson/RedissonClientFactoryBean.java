package com.yioks.springboot.common.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

public class RedissonClientFactoryBean implements FactoryBean<RedissonClient> {

  @Autowired(required = false)
  private RedisProperties redisProperties;


  @Override
  public RedissonClient getObject() throws Exception {

    if(redisProperties==null){
      redisProperties = new RedisProperties();
    }
    Config config = new Config();

    //sentinel
    if (redisProperties.getSentinel() != null) {
      SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
      sentinelServersConfig.setMasterName(redisProperties.getSentinel().getMaster());
      redisProperties.getSentinel().getNodes();
      String[] strings = new String[0];
      sentinelServersConfig.addSentinelAddress(redisProperties.getSentinel().getNodes().toArray(strings));
      sentinelServersConfig.setDatabase(redisProperties.getDatabase());
      if (redisProperties.getPassword() != null) {
        sentinelServersConfig.setPassword(redisProperties.getPassword());
      }
    } else { //single server
      SingleServerConfig singleServerConfig = config.useSingleServer();
      singleServerConfig.setAddress(redisProperties.getHost() + ":" + redisProperties.getPort());
      singleServerConfig.setDatabase(redisProperties.getDatabase());
      if (redisProperties.getPassword() != null) {
        singleServerConfig.setPassword(redisProperties.getPassword());
      }
    }

    return Redisson.create(config);
  }

  @Override
  public Class<?> getObjectType() {
    return RedissonClient.class;
  }
}
