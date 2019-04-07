package com.yioks.springboot.common.shiro.factoryBeans;

import com.yioks.springboot.common.shiro.session.properties.ShiroSessionProperties;
import com.yioks.springboot.common.shiro.session.dao.RedissonSessionDAO;
import com.yioks.springboot.common.shiro.session.manager.TokenSessionManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class TokenSessionManagerFactoryBean implements FactoryBean<TokenSessionManager> {

  @Autowired(required = false)
  private RedissonClient redissonClient;

  @Autowired(required = false)
  private ShiroSessionProperties sessionProperties;

  @Override
  public TokenSessionManager getObject() throws Exception {
    if (sessionProperties == null) {
      sessionProperties = new ShiroSessionProperties();
    }
    TokenSessionManager sessionManager = new TokenSessionManager();
    sessionManager.setTokenIdentification(sessionProperties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(sessionProperties.getTimeout());

    /**
     * 如果 RedissonClient 实例存在，测使用 redis 进行 Session 存储 以实现分布式服务的Session 同步
     */
    if (redissonClient != null) {
      RMapCache<Serializable, Session> rMap = redissonClient.getMapCache(sessionProperties.getRedissonStoreName());
      EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
      sessionManager.setSessionDAO(sessionDAO);
    }

    return sessionManager;
  }

  @Override
  public Class<?> getObjectType() {
    return TokenSessionManager.class;
  }
}
