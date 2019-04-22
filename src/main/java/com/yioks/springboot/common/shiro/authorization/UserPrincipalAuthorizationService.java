package com.yioks.springboot.common.shiro.authorization;

import com.yioks.springboot.common.shiro.model.*;

public class UserPrincipalAuthorizationService extends AbstractAuthorizationService {

  @Override
  public boolean supports(ShiroPrincipal shiroPrincipal) {
    return shiroPrincipal instanceof UserPrincipal;
  }

  @Override
  public IUser getUser(ShiroPrincipal shiroPrincipal) {
    return ((UserPrincipal) shiroPrincipal).getPrincipal();
  }
}
