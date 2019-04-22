package com.yioks.springboot.common.shiro.authentication;

import com.yioks.springboot.common.shiro.model.IUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface IAuthenticationService {

  boolean supports(AuthenticationToken token);

  AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName);

  IUser getLoginUser();
}
