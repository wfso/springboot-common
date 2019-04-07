package com.yioks.springboot.common.shiro.factoryBeans;

import com.yioks.springboot.common.shiro.session.dao.RedissonSessionDAO;
import com.yioks.springboot.common.shiro.session.manager.TokenSessionManager;
import com.yioks.springboot.common.shiro.session.properties.ShiroSessionProperties;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CustomizedSecurityManagerFactoryBean implements FactoryBean<SecurityManager> {

  @Autowired(required = false)
  Realm realm;

  @Autowired(required = false)
  private RedissonClient redissonClient;

  @Autowired(required = false)
  private ShiroSessionProperties sessionProperties;

  @Override
  public SecurityManager getObject() throws Exception {

    Assert.notNull(realm, "No Realm was found available");

    // 判断 ShiroSessionProperties 是否已经注入，如果没有注入则实例化一个
    if (sessionProperties == null) {
      sessionProperties = new ShiroSessionProperties();
    }
    // 实例化 TokenSessionManager
    TokenSessionManager sessionManager = new TokenSessionManager();
    sessionManager.setTokenIdentification(sessionProperties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(sessionProperties.getTimeout());

    // 如果 RedissonClient 实例存在，测使用 redis 进行 Session 存储 以实现分布式服务的Session 同步
    if (redissonClient != null) {
      RMapCache rMap = redissonClient.getMapCache(sessionProperties.getRedissonStoreName());
      EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
      sessionManager.setSessionDAO(sessionDAO);
    }


    // 实例化 DefaultWebSecurityManager
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // 如果 Reaml 不为空，则设置
    if (realm != null) {
      securityManager.setRealm(realm);
    }

    securityManager.setSessionManager(sessionManager);
    return securityManager;
  }

  @Override
  public Class<?> getObjectType() {
    return SecurityManager.class;
  }
}
