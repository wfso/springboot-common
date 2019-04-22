package com.yioks.springboot.common.shiro.authorization;

import com.yioks.springboot.common.shiro.model.*;

public class UserIdentificationPrincipalAuthorizationService extends AbstractAuthorizationService {
  @Override
  public boolean supports(ShiroPrincipal shiroPrincipal) {
    return shiroPrincipal instanceof UserIdentificationPrincipal;
  }

  @Override
  public IUser getUser(ShiroPrincipal shiroPrincipal) {
    return userService.getByIdentification(((UserIdentificationPrincipal) shiroPrincipal).getPrincipal());
  }
}
