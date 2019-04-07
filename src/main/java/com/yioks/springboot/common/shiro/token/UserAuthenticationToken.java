package com.yioks.springboot.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserAuthenticationToken extends UsernamePasswordToken {

  private Object userId;

  public Object getUserId() {
    return userId;
  }

  public void setUserId(Object userId) {
    this.userId = userId;
  }

  public UserAuthenticationToken() {
  }

  public UserAuthenticationToken(String username, char[] password) {
    super(username, password);
  }

  public UserAuthenticationToken(String username, String password) {
    super(username, password);
  }

  public UserAuthenticationToken(String username, char[] password, String host) {
    super(username, password, host);
  }

  public UserAuthenticationToken(String username, String password, String host) {
    super(username, password, host);
  }

  public UserAuthenticationToken(String username, char[] password, boolean rememberMe) {
    super(username, password, rememberMe);
  }

  public UserAuthenticationToken(String username, String password, boolean rememberMe) {
    super(username, password, rememberMe);
  }

  public UserAuthenticationToken(String username, char[] password, boolean rememberMe, String host) {
    super(username, password, rememberMe, host);
  }

  public UserAuthenticationToken(String username, String password, boolean rememberMe, String host) {
    super(username, password, rememberMe, host);
  }

  @Override
  public Object getPrincipal() {
    return getUserId();
  }


  public String getStringCredentials() {
    return String.valueOf((char[]) getCredentials());
  }
}
