package com.yioks.springboot.common.shiro.session.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("shiro.session")
public class ShiroSessionProperties {

  private String tokenIdentification = "IBESTCODE-COMMON-SHIRO-TOKEN";

  private long timeout = 1800000;

  private String cacheNamePrefix = "IBESTCODE-COMMON-SHIRO-SESSION-CACHE-";

  private String redissonStoreName = "IBESTCODE-COMMON-SHIRO-SESSION-REDISSON-STORE";

  private String loginTokenName = "IBESTCODE-COMMON-SHIRO-SESSION-LOGIN-TOKEN";

  public String getTokenIdentification() {
    return tokenIdentification;
  }

  public void setTokenIdentification(String tokenIdentification) {
    this.tokenIdentification = tokenIdentification;
  }

  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  public String getCacheNamePrefix() {
    return cacheNamePrefix;
  }

  public void setCacheNamePrefix(String cacheNamePrefix) {
    this.cacheNamePrefix = cacheNamePrefix;
  }

  public String getRedissonStoreName() {
    return redissonStoreName;
  }

  public void setRedissonStoreName(String redissonStoreName) {
    this.redissonStoreName = redissonStoreName;
  }

  public String getLoginTokenName() {
    return loginTokenName;
  }

  public void setLoginTokenName(String loginTokenName) {
    this.loginTokenName = loginTokenName;
  }
}
