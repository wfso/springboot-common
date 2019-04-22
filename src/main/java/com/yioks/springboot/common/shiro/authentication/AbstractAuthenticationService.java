package com.yioks.springboot.common.shiro.authentication;

import com.google.common.eventbus.EventBus;
import com.yioks.springboot.common.shiro.event.LoginEvent;
import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import com.yioks.springboot.common.shiro.service.ILoginUserService;
import com.yioks.springboot.common.shiro.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAuthenticationService implements IAuthenticationService {

  @Autowired(required = false)
  private EventBus eventBus;


  @Autowired(required = false)
  private IUserService userService;

  @Autowired(required = false)
  private ILoginUserService loginUserService;

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName) {
    ShiroPrincipal principal = verifyToken(token);
    if (userService != null && eventBus != null) {
      IUser user = userService.getByIdentification((long) token.getPrincipal());
      eventBus.post(new LoginEvent(user));
    }
    return new SimpleAuthenticationInfo(principal, token.getCredentials(), realmName);
  }

  protected abstract ShiroPrincipal verifyToken(AuthenticationToken token) throws AuthenticationException;

  public IUser getLoginUser() {
    return loginUserService != null ? loginUserService.getLoginUser() : null;
  }
}
