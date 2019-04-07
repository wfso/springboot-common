package com.yioks.springboot.common.shiro.model;

public class UserIdentificationPrincipal implements ShiroPrincipal {

  private Object userIdentification;

  public UserIdentificationPrincipal(Object userIdentification) {
    this.userIdentification = userIdentification;
  }

  @Override
  public Object getPrincipal() {
    return userIdentification;
  }
}
