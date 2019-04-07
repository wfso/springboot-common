package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class DefaultAuthorizationService implements IAuthorizationService {

  @Autowired(required = false)
  protected IUserService userService;

  @Autowired(required = false)
  protected IRoleService roleService;

  @Autowired(required = false)
  protected IPermissionService permissionService;

  @Override
  public boolean supports(ShiroPrincipal shiroPrincipal) {
    return shiroPrincipal instanceof UserIdentificationPrincipal;
  }

  @Override
  public AuthorizationInfo getAuthorizationInfo(ShiroPrincipal shiroPrincipal) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

    if (userService == null) {
      return info;
    }

    IUser user = userService.getByIdentification(shiroPrincipal.getPrincipal());
    if (user == null) {
      return info;
    }

    Collection<? extends IRole> roles = userService.getRolesByUser(user);
    Collection<? extends IPermission> userPermissions = userService.getUserPermission(user);
    if (userPermissions != null) {
      for (IPermission permission : userPermissions) {
        if (permissionService != null && permissionService.isAvailable(permission)) {
          info.addStringPermission(permission.getCode());
        }
      }
    }
    if (roles != null) {
      for (IRole role : roles) {
        if (roleService != null && roleService.isAvailable(role)) {
          info.addRole(role.getCode());
          Collection<? extends IPermission> permissions = roleService.getPermissionsByRole(role);
          if (permissions != null) {
            for (IPermission permission : permissions) {
              if (permissionService != null && permissionService.isAvailable(permission)) {
                info.addStringPermission(permission.getCode());
              }
            }
          }
        }
      }
    }
    return info;
  }
}
