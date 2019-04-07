package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import org.apache.shiro.authz.AuthorizationInfo;

public interface IAuthorizationService {

  boolean supports(ShiroPrincipal shiroPrincipal);

  AuthorizationInfo getAuthorizationInfo(ShiroPrincipal shiroPrincipal);
}
