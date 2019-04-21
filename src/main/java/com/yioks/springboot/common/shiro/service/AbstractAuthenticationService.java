package com.yioks.springboot.common.shiro.service;

import com.google.common.eventbus.EventBus;
import com.yioks.springboot.common.shiro.event.LoginEvent;
import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.session.utils.ISessionUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAuthenticationService implements IAuthenticationService {

  @Autowired(required = false)
  private EventBus eventBus;

  @Autowired(required = false)
  private ISessionUtil sessionUtil;

  @Autowired(required = false)
  private IUserService userService;

  private final String loginUserSessionName = "COMMON-LOGIN-USER-SESSION";

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName) {
    Object object = verifyToken(token);
    if (userService != null && sessionUtil != null) {
      IUser user = userService.getByIdentification((long) token.getPrincipal());
      sessionUtil.setSession(loginUserSessionName, user);
      if(eventBus!=null){
        eventBus.post(new LoginEvent(user));
      }
    }
    return new SimpleAuthenticationInfo(object, token.getCredentials(), realmName);
  }

  protected abstract Object verifyToken(AuthenticationToken token) throws AuthenticationException;

  public IUser getLoginUser() {
    return (IUser) sessionUtil.getSession(loginUserSessionName);
  }
}
