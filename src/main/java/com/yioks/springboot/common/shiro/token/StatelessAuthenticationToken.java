package com.yioks.springboot.common.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;
import java.util.TreeMap;

public class StatelessAuthenticationToken implements AuthenticationToken {

  private Object userId;

  private String password;

  private Map<String, String> params = new TreeMap<>();

  public Object getUserId() {
    return userId;
  }

  public void setUserId(Object userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = null;
    params.entrySet().forEach(entry -> {
      addParam(entry.getKey(), entry.getValue());
    });
  }

  public void addParam(String key, String value) {
    if (params == null) {
      params = new TreeMap<>();
    }
    params.put(key, value);
  }

  @Override
  public Object getPrincipal() {
    return getUserId();
  }

  @Override
  public Object getCredentials() {
    return getPassword();
  }
}
