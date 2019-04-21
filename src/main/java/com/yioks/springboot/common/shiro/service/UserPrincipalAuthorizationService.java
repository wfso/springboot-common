package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.*;

public class UserPrincipalAuthorizationService extends AbstractAuthorizationService {

  @Override
  public boolean supports(ShiroPrincipal shiroPrincipal) {
    return shiroPrincipal instanceof UserPrincipal;
  }

  @Override
  protected IUser getUser(ShiroPrincipal shiroPrincipal) {
    return ((UserPrincipal) shiroPrincipal).getPrincipal();
  }
}
