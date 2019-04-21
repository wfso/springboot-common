package com.yioks.springboot.common.shiro.model;

public class UserIdentificationPrincipal implements ShiroPrincipal<Long> {

  private Long userIdentification;

  public UserIdentificationPrincipal(Long userIdentification) {
    this.userIdentification = userIdentification;
  }

  @Override
  public Long getPrincipal() {
    return userIdentification;
  }
}
