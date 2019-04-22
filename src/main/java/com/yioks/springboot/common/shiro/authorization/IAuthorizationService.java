package com.yioks.springboot.common.shiro.authorization;

import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import org.apache.shiro.authz.AuthorizationInfo;

public interface IAuthorizationService {

  boolean supports(ShiroPrincipal shiroPrincipal);

  AuthorizationInfo getAuthorizationInfo(ShiroPrincipal shiroPrincipal);

  IUser getUser(ShiroPrincipal shiroPrincipal);
}
