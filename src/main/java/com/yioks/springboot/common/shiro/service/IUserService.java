package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.exception.UserAuthenticationException;
import com.yioks.springboot.common.shiro.model.*;
import com.yioks.springboot.common.shiro.token.UserAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.Collection;

public interface IUserService<T extends IUser> extends IAuthenticationService {
  T getByIdentification(Object id);

  T getByExtendedIdentification(String extendedIdentification);

  default boolean isAvailable(T t) throws AuthenticationException {
    return t != null && t.getIdentification() != null;
  }

  default Collection<? extends IRole> getRolesByUser(IUser user) {
    return user.getRoles();
  }

  default Collection<? extends IPermission> getUserPermission(IUser user) {
    return user.getPermissions();
  }

  default Collection<? extends IRole> getRolesByUserIdentification(Object id) {
    return getRolesByUser(getByIdentification(id));
  }

  default T getLoginUser() {
    return getByIdentification(getLoginUserIdentification());
  }

  default void login(UserAuthenticationToken token) {
    SecurityUtils.getSubject().login(token);
  }

  default Object getLoginUserIdentification() {
    return ((ShiroPrincipal)SecurityUtils.getSubject().getPrincipal()).getPrincipal();
  }

  default boolean validateCredentials(String userCredentials, String tokenCredentials) {
    return StringUtils.isNotEmpty(userCredentials)
      && StringUtils.isNotEmpty(tokenCredentials)
      && userCredentials.equalsIgnoreCase(tokenCredentials);
  }

  @Override
  default boolean supports(AuthenticationToken token) {
    return token instanceof UserAuthenticationToken;
  }

  @Override
  default AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName) {
    UserAuthenticationToken userToken = (UserAuthenticationToken) token;
    if (StringUtils.isEmpty(userToken.getUsername())) {
      throw new UserAuthenticationException("UAE-000001");
    }
    T user = getByExtendedIdentification(userToken.getUsername());
    if (user == null) {
      throw new UserAuthenticationException("UAE-000002");
    }

    if (StringUtils.isEmpty(userToken.getStringCredentials())) {
      throw new UserAuthenticationException("UAE-000003");
    }

    if (StringUtils.isEmpty(user.getCredentials())) {
      throw new UserAuthenticationException("UAE-000004");
    }

    if (!validateCredentials(user.getCredentials(), userToken.getStringCredentials())) {
      throw new UserAuthenticationException("UAE-000005");
    }

    if (!isAvailable(user)) {
      throw new UserAuthenticationException("UAE-000006");
    }

    userToken.setUserId(new UserIdentificationPrincipal(user.getIdentification()));

    return new SimpleAuthenticationInfo(
      userToken.getPrincipal(),
      userToken.getCredentials(),
      realmName
    );
  }
}
