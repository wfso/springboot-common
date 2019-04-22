package com.yioks.springboot.common.shiro.authorization;

import com.yioks.springboot.common.model.IUser;
import org.apache.shiro.authz.AuthorizationInfo;

public interface IAuthorizationService {

  boolean supports(IUser user);

  AuthorizationInfo getAuthorizationInfo(IUser user);
}
