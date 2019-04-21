package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.IPermission;
import com.yioks.springboot.common.shiro.model.IRole;
import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class AbstractAuthorizationService implements IAuthorizationService {

  @Autowired(required = false)
  protected IUserService userService;

  @Autowired(required = false)
  protected IRoleService roleService;

  @Autowired(required = false)
  protected IPermissionService permissionService;

  @Override
  public AuthorizationInfo getAuthorizationInfo(ShiroPrincipal shiroPrincipal) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    if (userService == null) {
      return info;
    }
    IUser user = getUser(shiroPrincipal);
    if (user == null) {
      return info;
    }
    fillAuthorizationInfo(user, info);
    return info;
  }

  protected abstract IUser getUser(ShiroPrincipal shiroPrincipal);

  private void fillAuthorizationInfo(IUser user, SimpleAuthorizationInfo info) {
    Collection<? extends IRole> roles = userService.getRolesByUser(user);
    Collection<? extends IPermission> userPermissions = userService.getUserPermission(user);
    if (userPermissions != null) {
      for (IPermission permission : userPermissions) {
        if (permissionService != null && permission.isAvailable()) {
          info.addStringPermission(permission.getCode());
        }
      }
    }
    if (roles != null) {
      for (IRole role : roles) {
        if (roleService != null && role.isAvailable()) {
          info.addRole(role.getCode());
          Collection<? extends IPermission> permissions = roleService.getPermissionsByRole(role);
          if (permissions != null) {
            for (IPermission permission : permissions) {
              if (permissionService != null && permission.isAvailable()) {
                info.addStringPermission(permission.getCode());
              }
            }
          }
        }
      }
    }
  }
}
