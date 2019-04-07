package com.yioks.springboot.common.shiro.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAuthenticationService implements IAuthenticationService {

  @Autowired(required = false)
  protected IUserService userService;

  @Autowired(required = false)
  protected IAccessKeyService accessKeyService;

  @Autowired(required = false)
  protected IRoleService roleService;

  @Autowired(required = false)
  protected IPermissionService permissionService;

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName) {
    verifyToken(token);
    return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), realmName);
  }

  protected abstract void verifyToken(AuthenticationToken token) throws AuthenticationException;
}
