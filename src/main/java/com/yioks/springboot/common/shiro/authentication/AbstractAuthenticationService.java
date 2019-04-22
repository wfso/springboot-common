package com.yioks.springboot.common.shiro.authentication;

import com.google.common.eventbus.EventBus;
import com.yioks.springboot.common.shiro.event.LoginEvent;
import com.yioks.springboot.common.shiro.model.IUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAuthenticationService implements IAuthenticationService {

  @Autowired(required = false)
  private EventBus eventBus;


  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName) {
    IUser user = verifyToken(token);
    if (eventBus != null) {
      eventBus.post(new LoginEvent(user));
    }
    return new SimpleAuthenticationInfo(user, token.getCredentials(), realmName);
  }

  protected abstract IUser verifyToken(AuthenticationToken token) throws AuthenticationException;

}
