package com.yioks.springboot.common.shiro.model;

public class UserPrincipal implements ShiroPrincipal<IUser> {

  private IUser user;

  public UserPrincipal(IUser user) {
    this.user = user;
  }

  @Override
  public IUser getPrincipal() {
    return user;
  }
}
